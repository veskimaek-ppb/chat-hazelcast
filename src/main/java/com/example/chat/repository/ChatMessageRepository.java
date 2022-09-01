package com.example.chat.repository;

import com.example.chat.domain.ChatMessage;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

import java.util.List;

public interface ChatMessageRepository extends KeyValueRepository<ChatMessage, String> {

    List<ChatMessage> findByTimeGreaterThanOrderByTimeAsc(Long time);
}
