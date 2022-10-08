package com.Finance.summary;

import com.Finance.category.CategoryRepository;
import com.Finance.expense.Expense;
import com.Finance.expense.ExpenseRepository;
import com.Finance.income.Income;
import com.Finance.income.IncomeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SummaryService {

    public ExpenseRepository expenseRepository;
    public IncomeRepository incomeRepository;

    public SummaryService(ExpenseRepository expenseRepository, IncomeRepository incomeRepository) {
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }

    public BigDecimal getBalance() {

        List<Income> incomes = incomeRepository.findAll();
        List<Expense> expenses = expenseRepository.findAll();
        BigDecimal sumOfIncome = incomes.stream().map(i -> i.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumOfExpense = expenses.stream().map(i -> i.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        return sumOfIncome.subtract(sumOfExpense);

    }

    public List<SummaryDto> getSummaryGroupByCategory() {

        List<Object[]> sumGroupBy = expenseRepository.findExpenseGroupByCategory();
        List<SummaryDto> summaryDtos = new ArrayList<>();
        for (Object[] objects : sumGroupBy) {

            String categoryName = (String) objects[0];
            BigDecimal totalCategoryAmount = (BigDecimal) objects[1];
            SummaryDto summaryDto = new SummaryDto(categoryName, totalCategoryAmount.toString());

            summaryDtos.add(summaryDto);

        }
        return summaryDtos;

    }
}
