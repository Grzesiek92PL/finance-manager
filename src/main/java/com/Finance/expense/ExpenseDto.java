package com.Finance.expense;

import java.time.LocalDate;

public class ExpenseDto {

    private long id;

    private String amount;

    private String comment;

    private LocalDate date;

    public ExpenseDto(long id, String amount, String comment, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.comment = comment;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ExpenseDto{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}


