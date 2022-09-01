package com.example.chat.controller;

import com.example.chat.domain.ChatMessage;
import com.example.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ChatMessageService chatMessageService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<URL> index() throws MalformedURLException {

        List<URL> urls = new ArrayList<URL>();

        urls.add(new URL("http://localhost:8080/load"));
        urls.add(new URL("http://localhost:8080/all"));

        return urls;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<ChatMessage> all() {
        return this.chatMessageService.findChatMessagesAfterTimeSorted(System.currentTimeMillis());
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public int load() {

        return this.chatMessageService.load();
    }
}
