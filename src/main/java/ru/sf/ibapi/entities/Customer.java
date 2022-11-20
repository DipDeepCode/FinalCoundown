package ru.sf.ibapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @OneToOne(mappedBy = "customer", orphanRemoval = true)
    private Balance balance;

    @Column(name = "created_timestamp", nullable = false)
    private ZonedDateTime createdTimestamp;

    @Column(name = "disabled_timestamp")
    private ZonedDateTime disabledTimestamp;

}