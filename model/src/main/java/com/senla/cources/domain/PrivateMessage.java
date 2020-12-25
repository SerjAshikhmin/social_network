package com.senla.cources.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "private_message")
public class PrivateMessage extends Message {

    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    public PrivateMessage(int id, String content, LocalDateTime sendDate, User sender, User receiver) {
        super(id, content, sendDate);
        this.isRead = false;
        this.sender = sender;
        this.receiver = receiver;
    }
}
