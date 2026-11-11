package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.Student;
import java.util.List;

/**
 * Defines database operations for Instructor, Course, and Student.
 */
public interface AppDao {

    /** Saves an Instructor. */
    void save(Instructor instructor);

    /** Finds an Instructor by ID. */
    Instructor findInstructorById(int theId);

    /** Deletes an Instructor and its details by ID. */
    void deleteInstructorById(int theId);

    /** Deletes InstructorDetails without deleting the Instructor. */
    void deleteInstructorDetail(int theId);

    /** Finds all Courses belonging to an Instructor. */
    List<Course> findCoursesByInstructorId(int theId);

    /** Finds an Instructor with its Courses and Details. */
    Instructor findInstructorByJoinFetch(int theId);

    /** Updates an Instructor. */
    void update(Instructor in);

    /** Finds a Course by ID. */
    Course findCourseById(int theId);

    /** Updates a Course. */
    void update(Course course);

    /** Deletes an Instructor without deleting its Courses. */
    void deleteInstructorOnlyById(int theId);

    /** Deletes a Course by ID. */
    void deleteCourseById(int theId);

    /** Saves a Course. */
    void save(Course theCourse);

    /** Finds a Course with its Reviews. */
    Course findCourseAndReviewsByCourseId(int theId);

    /** Finds a Course with its enrolled Students. */
    Course findCourseAndStudentByCourseId(int theId);

    /** Finds a Student with their registered Courses. */
    Student findStudentAndCourseByStudentId(int theId);

    /** Updates a Student. */
    void update(Student student);

    /** Deletes a Student by ID. */
    void deleteStudentById(int theId);
}