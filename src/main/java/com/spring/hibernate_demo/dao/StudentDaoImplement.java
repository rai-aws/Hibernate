package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the StudentDao interface using JPA EntityManager.
 */
@Repository
public class StudentDaoImplement implements StudentDao {

    private final EntityManager em;

    /** Constructor injection for EntityManager. */
    public StudentDaoImplement(EntityManager em) {
        this.em = em;
    }

    /** Saves a new Student entity to the database. */
    @Override
    @Transactional
    public void save(Student student) {
        em.persist(student);
    }

    /** Retrieves a Student record by primary key ID. */
    @Override
    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    /** Retrieves all Student records from the database. */
    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    /** Retrieves a list of Student records matching the given last name. */
    @Override
    public List<Student> findByLastName(String lastname) {
        TypedQuery<Student> query = em.createQuery("FROM Student WHERE lastName = :data", Student.class);
        query.setParameter("data", lastname);
        return query.getResultList();
    }

    /** Updates an existing Student record. */
    @Override
    @Transactional
    public void update(Student theStudent) {
        em.merge(theStudent);
    }

    /** Deletes a Student record by primary key ID. */
    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
        }
    }

    /** Deletes all Student records from the database and returns the count of deleted rows. */
    @Override
    @Transactional
    public int deleteAll() {
        return em.createQuery("DELETE FROM Student").executeUpdate();
    }
}