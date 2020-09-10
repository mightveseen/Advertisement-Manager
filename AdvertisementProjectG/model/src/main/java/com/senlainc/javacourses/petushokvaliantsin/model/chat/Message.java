package com.senlainc.javacourses.petushokvaliantsin.model.chat;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "messages")
@NamedEntityGraph(name = GraphProperty.MESSAGE_DEFAULT, attributeNodes = {
        @NamedAttributeNode(value = "user"), @NamedAttributeNode(value = "chat")
})
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;
    @Column(name = "text")
    private String text;
    @Column(name = "date")
    private LocalDateTime dateTime;

    public Message(User user, Chat chat, String text, LocalDateTime dateTime) {
        this.user = user;
        this.chat = chat;
        this.text = text;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return getId().equals(message.getId()) &&
                getUser().equals(message.getUser()) &&
                getChat().equals(message.getChat()) &&
                getText().equals(message.getText()) &&
                getDateTime().equals(message.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getChat(), getText(), getDateTime());
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user=" + user +
                ", chat=" + chat +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
