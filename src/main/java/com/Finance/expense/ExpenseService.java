package com.Finance.expense;

import com.Finance.category.Category;
import com.Finance.category.CategoryRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExpenseService {
    public ExpenseRepository expenseRepository;
    public CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {

        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }


    public void addExpense(String category, BigDecimal amount, String comment) {
        Category categoryFromDb = categoryRepository.findByName(category);
        Expense expense = new Expense(categoryFromDb, comment, amount);
        expenseRepository.insert(expense);
    }

    public void deleteExpense(Long id) {

        Expense expense = expenseRepository.findById(id);
        if (expense != null) {
            expenseRepository.delete(expense);
        }

    }

    public Set<ExpenseDto> getExpenses() {

        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .map(e -> new ExpenseDto(e.getId(), e.getAmount().toString(), e.getComment(), e.getDate()))
                .collect(Collectors.toSet());
    }
}
