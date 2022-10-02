package com.Finance.income;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IncomeService {

    public IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public void addIncome(BigDecimal amount, String comment) {
        Income income = new Income(comment, amount);
        incomeRepository.insert(income);
    }

    public void deleteIncome(Long id) {

        Income income = incomeRepository.findById(id);
        if (income != null) {
            incomeRepository.delete(income);
        }


    }

    public Set<IncomeDto> getIncomes() {

        List<Income> incomes = incomeRepository.findAll();
        return incomes.stream()
                .map(i -> new IncomeDto(i.getId(),i.getAmount().toString(), i.getComment(), i.getDate()))
                .collect(Collectors.toSet());

    }
}
