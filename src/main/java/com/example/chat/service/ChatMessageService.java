package com.example.chat.service;

import com.example.chat.Constants;
import com.example.chat.domain.ChatMessage;
import com.example.chat.repository.ChatMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Random;

@Service
@Slf4j
public class ChatMessageService {

    @Resource
    private ChatMessageRepository elementRepository;

    Random random = new Random();

    public Iterable<ChatMessage> findChatMessagesAfterTimeSorted(final long fromTime) {
        return this.elementRepository.findByTimeGreaterThanOrderByTimeAsc(fromTime);
    }

    public int load() {
        int count = 0;

        // Atomic number, Symbol, Name, Group [optional], Period, Atomic Weight
        for (int i = 0; i < 100; i++) {
            ChatMessage element = new ChatMessage();

            long time = System.currentTimeMillis();
            if (random.nextBoolean()) {
                time += random.nextInt(600 * 1000); // up to 10 minutes in future
            } else {
                time -= random.nextInt(600 * 1000); // up to 10 minutes in past
            }
            element.setTime(time);
            Instant instant = Instant.ofEpochMilli(time);
            element.setMessage("Message at " + instant.toString());

            log.trace("load(): {}", element);
            this.elementRepository.save(element);
            count++;
        }

        return count;
    }
}
