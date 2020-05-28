package com.senlainc.javacourses.petushokvaliantsin.model.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;
    @Column(name = "chat_name")
    private String name;
    @Column(name = "chat_last_message")
    private String lastMessage;
    @OneToMany(mappedBy = "chat")
    private Set<Message> messages;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "chat_users", joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_user"))
    private Set<User> users;

    public Chat() {
    }

    public Chat(String name, String lastMessage) {
        this.name = name;
        this.lastMessage = lastMessage;
    }

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

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return index.equals(chat.index) &&
                name.equals(chat.name) &&
                lastMessage.equals(chat.lastMessage) &&
                Objects.equals(messages, chat.messages) &&
                users.equals(chat.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, name, lastMessage, messages, users);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", messages=" + messages +
                ", users=" + users +
                '}';
    }
}