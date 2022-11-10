package ru.sf.ibapi.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "balance")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "balance", nullable = false)
    private Long balance;
}