package com.Finance;

import com.Finance.category.CategoryRepository;
import com.Finance.category.CategoryService;
import com.Finance.config.ConnectionManager;
import com.Finance.expense.*;
import com.Finance.income.IncomeDto;
import com.Finance.income.IncomeRepository;
import com.Finance.income.IncomeService;
import com.Finance.summary.SummaryService;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final CategoryRepository categoryRepository = new CategoryRepository();
    private static final CategoryService categoryService = new CategoryService(categoryRepository);
    private static final ExpenseRepository expenseRepository = new ExpenseRepository();
    private static final ExpenseService expenseService = new ExpenseService(expenseRepository, categoryRepository);
    // FIXME private static final Expense expenseSe = new Expense();
    private static final IncomeRepository incomeRepository = new IncomeRepository();
    private static final IncomeService incomeService = new IncomeService(incomeRepository);
    // TODO private static final Income income = new Income();
    private static final SummaryService summaryService = new SummaryService(expenseRepository, incomeRepository);

    public static void main(String[] args) {
        EntityManager en = ConnectionManager.getEntityManager();
        en.close();
        Scanner in = new Scanner(System.in);


        while (true) {

            System.out.println("Type the operation to execution: ");

            System.out.println("0 - Exit");
            System.out.println("1 - Add expense"); // +
            System.out.println("2 - Add income"); // +
            System.out.println("3 - Delete expense"); // +
            System.out.println("4 - Delete income"); // +
            System.out.println("5 - Display all expenses and incomes"); // +
            System.out.println("6 - Display all expenses"); // +
            System.out.println("7 - Display all incomes"); // +
            System.out.println("8 - Display balance");
            System.out.println("9 - Display all expenses grouping by category");
            System.out.println("10 - Display all expenses between dates");
            System.out.println("11 - Add new category"); // +
            System.out.println("12 - Delete category"); // +

            int result = in.nextInt();
            in.nextLine();

            switch (result) {

                case 0 -> {
                    System.exit(0);
                }

                case 1 -> {
                    System.out.println("Expense amount: ");
                    BigDecimal totalCost = new BigDecimal(String.valueOf(in.nextBigDecimal())).setScale(2, RoundingMode.CEILING);
                    in.nextLine();
                    System.out.println("Type comment (optionally): ");
                    String comment = in.nextLine();
                    System.out.println("Write category: ");
                    String category = in.nextLine();
                    expenseService.addExpense(category, totalCost, comment);
                }

                case 2 -> {

                    System.out.println("Enter amount: ");
                    BigDecimal totalCost = new BigDecimal(String.valueOf(in.nextBigDecimal())).setScale(2, RoundingMode.CEILING);
                    in.nextLine();
                    System.out.println("Type comment (optionally): ");
                    String comment = in.nextLine();
                    incomeService.addIncome(totalCost, comment);

                }

                case 3 -> {
                    System.out.println("Expense which you want to delete (id): ");
                    Long deleteExpenseId = in.nextLong();
                    expenseService.deleteExpense(deleteExpenseId);
                }

                case 4 -> {

                    System.out.println("Income which you want to delete (id): ");
                    Long deleteIncomeId = in.nextLong();
                    incomeService.deleteIncome(deleteIncomeId);
                }

                case 5 -> {
                    Set<IncomeDto> incomes = incomeService.getIncomes();
                    Set<ExpenseDto> expenses = expenseService.getExpenses();
                    System.out.println(incomes);
                    System.out.println(expenses);
                }

                case 6 -> {
                    Set<ExpenseDto> expenses = expenseService.getExpenses();
                    System.out.println(expenses);

                }

                case 7 -> {
                    Set<IncomeDto> incomes = incomeService.getIncomes();
                    System.out.println(incomes);
                }

                case 8 -> {
                    BigDecimal balance = summaryService.getBalance();
                    System.out.println("Balance: " + balance);

                }

                case 9 -> {
                    System.out.println(summaryService.getSummaryGroupByCategory());
                }

                case 10 -> {


                }

                case 11 -> {

                    System.out.println("Type category name: ");
                    String categoryName = in.nextLine();
                    categoryService.addCategory(categoryName);


                }

                case 12 -> {

                    System.out.println("Delete category name: ");
                    String categoryName = in.nextLine();
                    categoryService.deleteCategory(categoryName);
                }


            }
        }

    }
}




