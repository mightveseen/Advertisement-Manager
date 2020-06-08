package com.senlainc.javacourses.petushokvaliantsin.model.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

import javax.persistence.CascadeType;
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
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "chat_name")
    private String name;
    @Column(name = "chat_last_message")
    private String lastMessage;
    @Column(name = "chat_update_date")
    private LocalDateTime updateDateTime;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_chats", joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_user"))
    private Set<User> users;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private Set<Message> messages;

    public Chat() {
    }

    public Chat(String name, String lastMessage, LocalDateTime updateDateTime) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.updateDateTime = updateDateTime;
    }

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

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return id.equals(chat.id) &&
                name.equals(chat.name) &&
                lastMessage.equals(chat.lastMessage) &&
                users.equals(chat.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastMessage, users);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", updateDateTime=" + updateDateTime +
                ", users=" + users +
                '}';
    }
}
