package ru.sf.ibapi.entities;

import lombok.Getter;
import lombok.Setter;
import ru.sf.ibapi.operationcodes.OperationCode;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_code", nullable = false)
    private OperationCode operationCode; //TODO в задании сказано что здесь должно быть число

    @Column(name = "operation_timestamp", nullable = false)
    private ZonedDateTime operationTimestamp;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}