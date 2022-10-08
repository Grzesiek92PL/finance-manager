package com.Finance.expense;

import com.Finance.config.ConnectionManager;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ExpenseRepository {

    public void insert(Expense expense) {

        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(expense);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Expense expense) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(expense);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Expense findById(Long id) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        Expense expense = entityManager.find(Expense.class, id);
        entityManager.close();
        return expense;


    }

    public List<Expense> findAll() {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        List<Expense> expenses = entityManager.createQuery("Select e from Expense e").getResultList();
        entityManager.close();
        return expenses;
    }

    public List<Object[]> findExpenseGroupByCategory() {
        EntityManager entityManager = ConnectionManager.getEntityManager();

        String sql = "select c.name, sum(e.amount) from expense e " +
                "join category c on c.id = e.category_id " +
                "group by c.name " +
                "order by sum(e.amount) desc; ";
        List resultList = entityManager.createNativeQuery(sql).getResultList();
        entityManager.close();
        return resultList;
    }


}




