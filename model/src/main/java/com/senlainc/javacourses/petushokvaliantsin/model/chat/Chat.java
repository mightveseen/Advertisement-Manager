package com.senlainc.javacourses.petushokvaliantsin.model.chat;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "chats")
@NamedEntityGraph(name = GraphProperty.CHAT_DEFAULT,
        attributeNodes = @NamedAttributeNode(value = "users", subgraph = "chat-user-subgraph"),
        subgraphs = @NamedSubgraph(name = "chat-user-subgraph",
                attributeNodes = @NamedAttributeNode(value = "userCred")))
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private Set<Message> messages;

    public Chat(String name, String lastMessage, LocalDateTime updateDateTime, Set<User> users) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.updateDateTime = updateDateTime;
        this.users = users;
    }
}
