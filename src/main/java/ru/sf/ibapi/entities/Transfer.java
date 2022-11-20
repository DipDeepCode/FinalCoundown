package ru.sf.ibapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "recipient_operation_id", nullable = false)
    private Operation recipientOperation;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "sender_operation_id", nullable = false)
    private Operation senderOperation;
}