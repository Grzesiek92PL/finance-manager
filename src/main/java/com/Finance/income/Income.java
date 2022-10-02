package com.Finance.income;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private BigDecimal amount;

    @Column(name = "income_add_date")
    private LocalDate date;

    public Income(String comment, BigDecimal amount) {
        this.comment = comment;
        this.amount = amount;
        this.date = LocalDate.now();
    }
}
