package com.Finance.category;


import com.Finance.config.ConnectionManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class CategoryRepository {


    public void insert(Category category) {

        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Category category) {

        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(category);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public Category findByName(String name) {

        EntityManager entityManager = ConnectionManager.getEntityManager();
        Category category = null;
        try {
            category = entityManager.createQuery("select c from Category c where c.name = :param", Category.class).setParameter("param", name)
                    .getSingleResult();


        } catch (NoResultException e) {

            System.out.println(e.getMessage());
        } finally {

            entityManager.close();
        }
        return category;

    }
}
