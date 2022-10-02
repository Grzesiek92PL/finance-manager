package com.Finance.income;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeDto {

    private long id;
    private String amount;
    private String comment;
    private LocalDate date;

    public IncomeDto(long id, String amount, String comment, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.comment = comment;
        this.date = date;

    }

    @Override
    public String toString() {
        return "IncomeDto{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
