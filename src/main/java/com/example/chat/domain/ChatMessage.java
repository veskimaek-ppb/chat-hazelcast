package com.example.chat.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
@KeySpace
public class ChatMessage implements Comparable<ChatMessage>, Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    @Id
    @javax.persistence.Id
    private Long time;

    public int compareTo(ChatMessage that) {
        return this.time.compareTo(that.getTime());
    }
}
