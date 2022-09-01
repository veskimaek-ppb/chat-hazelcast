package com.example.chat.domain;

import com.example.chat.Constants;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.io.Serializable;

@Data
@KeySpace("chatmessage")
public class ChatMessage implements Comparable<ChatMessage>, Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    @Id
    private Long time;

    public int compareTo(ChatMessage that) {
        return this.time.compareTo(that.getTime());
    }
}
