package com.example.chat.util;

import com.example.chat.domain.ChatMessage;
import com.example.chat.repository.ChatJPARepository;
import com.hazelcast.map.MapLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class ChatLoader implements ApplicationContextAware,  MapLoader<Long, ChatMessage> {

    @Autowired
    private static ChatJPARepository chatJPARepository;

    @Override
    public ChatMessage load(Long key) {
        log.info("load({})", key);
        Optional<ChatMessage> optional = chatJPARepository.findById(key);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public Map<Long, ChatMessage> loadAll(Collection<Long> keys) {
        Map<Long, ChatMessage> result = new HashMap<>();
        for (Long key : keys) {
            ChatMessage message = this.load(key);
            if (message != null) {
                result.put(key, message);
            }
        }
        return result;
    }

    @Override
    public Iterable<Long> loadAllKeys() {
        Iterable<ChatMessage> all = chatJPARepository.findAll();
        Collection<Long> ret = new HashSet<>();
        for (ChatMessage msg: all) {
            ret.add(msg.getTime());
        }
        return ret;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        chatJPARepository = applicationContext.getBean(ChatJPARepository.class);
    }
}
