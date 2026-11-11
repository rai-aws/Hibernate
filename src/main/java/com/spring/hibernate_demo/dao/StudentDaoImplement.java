package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Implements StudentDao using JPA EntityManager.
 */
@Repository
public class StudentDaoImplement implements StudentDao {

    private final EntityManager em;

    /** Initializes the EntityManager. */
    public StudentDaoImplement(EntityManager em) {
        this.em = em;
    }

    /** Saves a Student. */
    @Override
    @Transactional
    public void save(Student student) {
        em.persist(student);
    }

    /** Finds a Student by ID. */
    @Override
    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    /** Finds all Students. */
    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery
                                    ("FROM Student", 
                                    Student.class);
        return query.getResultList();
    }

    /** Finds Students by last name. */
    @Override
    public List<Student> findByLastName(String lastname) {
        TypedQuery<Student> query = em.createQuery
                                    ("FROM Student " 
                                    + "WHERE lastName = :data", 
                                    Student.class);
        query.setParameter("data", lastname);
        return query.getResultList();
    }

    /** Updates a Student. */
    @Override
    @Transactional
    public void update(Student theStudent) {
        em.merge(theStudent);
    }

    /** Deletes a Student by ID. */
    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
        }
    }

    /** Deletes all Students and returns the number of deleted rows. */
    @Override
    @Transactional
    public int deleteAll() {
        return em.createQuery("DELETE FROM Student").executeUpdate();
    }
}