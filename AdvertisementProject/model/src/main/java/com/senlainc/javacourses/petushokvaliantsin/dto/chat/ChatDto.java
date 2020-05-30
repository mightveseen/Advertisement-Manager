package com.senlainc.javacourses.petushokvaliantsin.dto.chat;

import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;

import java.io.Serializable;
import java.util.Set;

public class ChatDto implements Serializable {

    private Long index;
    private String name;
    private String lastMessage;
    private Set<MessageDto> messages;
    private Set<UserDto> users;

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
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

    public Set<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageDto> messages) {
        this.messages = messages;
    }

    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }
}
