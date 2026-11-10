package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Student;
import java.util.List;

/**
 * Provides database operations for Student entities.
 */
public interface StudentDao {

    /** Saves a new Student entity to the database. */
    void save(Student student);

    /** Retrieves a Student record by primary key ID. */
    Student findById(Integer id);

    /** Retrieves all Student records from the database. */
    List<Student> findAll();

    /** Retrieves a list of Student records matching the given last name. */
    List<Student> findByLastName(String lastName);

    /** Updates an existing Student record. */
    void update(Student theStudent);

    /** Deletes a Student record by primary key ID. */
    void delete(Integer id);

    /** Deletes all Student records from the database. */
    int deleteAll();
}