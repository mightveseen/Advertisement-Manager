package com.senlainc.javacourses.petushokvaliantsin.model.chat;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Data
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
