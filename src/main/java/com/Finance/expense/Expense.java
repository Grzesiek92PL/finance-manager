package com.Finance.expense;

import com.Finance.category.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String comment;

    private BigDecimal amount;
    @Column(name = "expense_add_date")
    private LocalDate date;

    public Expense(Category category, String comment, BigDecimal amount) {
        this.category = category;
        this.comment = comment;
        this.amount = amount;
        this.date = LocalDate.now();
    }
}






