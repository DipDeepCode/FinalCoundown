package ru.sf.ibapi.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.sf.ibapi.services.OperationCodes;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "operation_log")
public class OperationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_code", nullable = false)
    private OperationCodes operationCode;

    @Column(name = "operation_timestamp", nullable = false)
    private ZonedDateTime operation_timestamp;
}