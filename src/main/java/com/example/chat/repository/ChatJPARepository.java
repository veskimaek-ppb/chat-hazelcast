package com.example.chat.repository;

import com.example.chat.domain.ChatMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChatJPARepository extends CrudRepository<ChatMessage, Long> {
    @Query("SELECT cm.time FROM ChatMessage cm")
    Iterable<Long> findAllId();
}
