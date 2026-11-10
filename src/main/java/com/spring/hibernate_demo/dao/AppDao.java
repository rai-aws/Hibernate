package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.Student;
import java.util.List;

/**
 * Provides database operations for Instructor, Course, and Student entities.
 */
public interface AppDao {

    /** Saves an Instructor entity. */
    void save(Instructor instructor);

    /** Retrieves an Instructor by ID. */
    Instructor findInstructorById(int theId);

    /** Deletes an Instructor and its associated details by ID. */
    void deleteInstructorById(int theId);

    /** Deletes an InstructorDetail entity by ID without deleting the Instructor. */
    void deleteInstructorDetail(int theId);

    /** Retrieves all Course entities associated with a specific Instructor ID. */
    List<Course> findCoursesByInstructorId(int theId);

    /** Retrieves an Instructor and eagerly fetches associated Courses using a JOIN FETCH query. */
    Instructor findInstructorByJoinFetch(int theId);

    /** Updates an existing Instructor record. */
    void update(Instructor in);

    /** Retrieves a Course entity by primary key ID. */
    Course findCourseById(int theId);

    /** Updates an existing Course record. */
    void update(Course course);

    /** Deletes an Instructor by ID without deleting associated Course records. */
    void deleteInstructorOnlyById(int theId);

    /** Deletes a Course record by ID. */
    void deleteCourseById(int theId);

    /** Saves a Course entity. */
    void save(Course theCourse);

    /** Retrieves a Course along with its associated Review records by Course ID. */
    Course findCourseAndReviewsByCourseId(int theId);

    /** Retrieves a Course along with its enrolled Student records by Course ID. */
    Course findCourseAndStudentByCourseId(int theId);

    /** Retrieves a Student along with registered Course records by Student ID. */
    Student findStudentAndCourseByStudentId(int theId);

    /** Updates an existing Student record. */
    void update(Student student);

    /** Deletes a Student record by ID. */
    void deleteStudentById(int theId);
}