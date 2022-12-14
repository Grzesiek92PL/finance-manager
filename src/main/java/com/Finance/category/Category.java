package com.Finance.category;

import com.Finance.expense.Expense;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private String name;
    @OneToMany(mappedBy = "category")
    private List<Expense> expenses;

public  Category(String name) {
    this.name = name;
}

}

