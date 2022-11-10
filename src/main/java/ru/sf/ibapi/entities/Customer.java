package ru.sf.ibapi.entities;

import lombok.*;

import javax.persistence.*;

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

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, optional = false, orphanRemoval = true)
    @JoinColumn(name = "balance_id", nullable = false)
    private Balance balance;
}
