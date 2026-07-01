package com.kobita.banking.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kobita.banking.account.Account;
import com.kobita.banking.common.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private BigDecimal amount;


    @ManyToOne
    private Account fromAccount;

    @ManyToOne
    private Account toAccount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    void setcreatedAt(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate 
    void setUpdateTime(){
        updatedAt = LocalDateTime.now();
    }

}
