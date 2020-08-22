package com.senlainc.javacourses.petushokvaliantsin.model.chat;

import com.senlainc.javacourses.petushokvaliantsin.graph.DefaultGraph;
import com.senlainc.javacourses.petushokvaliantsin.graph.GraphName;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chats")
@DefaultGraph(name = GraphName.CHAT_DEFAULT)
@NamedEntityGraph(name = GraphName.CHAT_DEFAULT, attributeNodes = @NamedAttributeNode("users"))
public class Chat {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_message")
    private String lastMessage;
    @Column(name = "update_date")
    private LocalDateTime updateDateTime;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_chats", joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private Set<Message> messages;

    public Chat(String name, String lastMessage, LocalDateTime updateDateTime, Set<User> users) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.updateDateTime = updateDateTime;
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return getId().equals(chat.getId()) &&
                getName().equals(chat.getName()) &&
                getLastMessage().equals(chat.getLastMessage()) &&
                getUpdateDateTime().equals(chat.getUpdateDateTime()) &&
                getUsers().equals(chat.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastMessage(), getUpdateDateTime(), getUsers());
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
