package com.senlainc.javacourses.petushokvaliantsin.dto.chat;

import java.io.Serializable;

public class ChatDto implements Serializable {

    private Long id;
    private String name;
    private String lastMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
