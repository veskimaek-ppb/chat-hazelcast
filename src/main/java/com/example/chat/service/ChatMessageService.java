package com.example.chat.service;

import com.example.chat.domain.ChatMessage;
import com.example.chat.repository.ChatJPARepository;
import com.example.chat.repository.hz.ChatMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Random;

@Service
@Slf4j
public class ChatMessageService {

    @Resource
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    protected ChatJPARepository chatJPARepository;

    Random random = new Random();

    public Iterable<ChatMessage> findChatMessagesAfterTimeSorted(final long fromTime) {
        return this.chatMessageRepository.findByTimeGreaterThanOrderByTimeAsc(fromTime);
    }

    public int load() {
        int count = 0;

        // Atomic number, Symbol, Name, Group [optional], Period, Atomic Weight
        for (int i = 0; i < 100; i++) {
            ChatMessage chatMessage = new ChatMessage();

            long time = System.currentTimeMillis();
            if (random.nextBoolean()) {
                time += random.nextInt(600 * 1000); // up to 10 minutes in future
            } else {
                time -= random.nextInt(600 * 1000); // up to 10 minutes in past
            }
            chatMessage.setTime(time);
            Instant instant = Instant.ofEpochMilli(time);
            chatMessage.setMessage("Message at " + instant.toString());

            log.trace("load(): {}", chatMessage);
            this.chatMessageRepository.save(chatMessage);
            this.chatJPARepository.save(chatMessage);
            count++;
        }

        return count;
    }
}
