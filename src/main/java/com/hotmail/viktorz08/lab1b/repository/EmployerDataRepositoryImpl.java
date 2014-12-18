package com.hotmail.viktorz08.lab1b.repository;

import com.hotmail.viktorz08.lab1b.repository.entities.Employers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Victor on 11/22/2014.
 */
@Repository
@Transactional
public class EmployerDataRepositoryImpl implements EmployerDataRepository {
    protected EntityManager entityManager;

    @Override
    public List<Employers> getEmployersData() {
        Query query = entityManager.createQuery("SELECT e from Employers as e", Employers.class);
        return query.getResultList();
    }

    @Override
    public void addEmployerData(Employers data) {
        entityManager.merge(data);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
