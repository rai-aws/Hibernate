package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Implements AppDao using JPA EntityManager.
 */
@Repository
public class AppDaoImplement implements AppDao {

    private final EntityManager em;

    /** Initializes the EntityManager. */
    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    /** Saves an Instructor and its details. */
    @Override
    @Transactional
    public void save(Instructor instructor) {
        em.persist(instructor);
    }

    /** Finds an Instructor by ID. */
    @Override
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    /** Deletes an Instructor and its details by ID. */
    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor ins = em.find(Instructor.class, theId);
        em.remove(ins);
    }

    /** Deletes InstructorDetails without deleting the Instructor. */
    @Override
    @Transactional
    public void deleteInstructorDetail(int theId) {
        InstructorDetail deets = em.find(InstructorDetail.class, theId);

        deets.getInstructor().setInstructorDeets(null);
        em.remove(deets);
    }

    /** Finds all Courses belonging to an Instructor. */
    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = em.createQuery(
                "FROM Course WHERE instructor.id = :data", Course.class);

        query.setParameter("data", theId);

        return query.getResultList();
    }

    /** Finds an Instructor with its Courses and Details. */
    @Override
    public Instructor findInstructorByJoinFetch(int theId) {
        TypedQuery<Instructor> query = em.createQuery(
                "SELECT i FROM Instructor i "
                + "JOIN FETCH i.courses "
                + "JOIN FETCH i.instructorDeets "
                + "WHERE i.id = :data", Instructor.class);

        query.setParameter("data", theId);

        return query.getSingleResult();
    }

    /** Updates an Instructor. */
    @Override
    @Transactional
    public void update(Instructor in) {
        em.merge(in);
    }

    /** Finds a Course by ID. */
    @Override
    public Course findCourseById(int theId) {
        return em.find(Course.class, theId);
    }

    /** Updates a Course. */
    @Override
    @Transactional
    public void update(Course course) {
        em.merge(course);
    }

    /** Deletes an Instructor without deleting its Courses. */
    @Override
    @Transactional
    public void deleteInstructorOnlyById(int theId) {
        Instructor ins = em.find(Instructor.class, theId);

        List<Course> courses = ins.getCourses();
        for (Course c : courses) {
            c.setInstructor(null);
        }

        em.remove(ins);
    }

    /** Deletes a Course by ID. */
    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course course = em.find(Course.class, theId);
        em.remove(course);
    }

    /** Saves a Course. */
    @Override
    @Transactional
    public void save(Course theCourse) {
        em.persist(theCourse);
    }

    /** Finds a Course with its Reviews. */
    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c "
                + "JOIN FETCH c.review "
                + "WHERE c.id = :data", Course.class);

        query.setParameter("data", theId);

        return query.getSingleResult();
    }

    /** Finds a Course with its enrolled Students. */
    @Override
    public Course findCourseAndStudentByCourseId(int theId) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c "
                + "JOIN FETCH c.students "
                + "WHERE c.id = :data", Course.class);

        query.setParameter("data", theId);

        return query.getSingleResult();
    }

    /** Finds a Student with their registered Courses. */
    @Override
    public Student findStudentAndCourseByStudentId(int theId) {
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s "
                + "JOIN FETCH s.courses "
                + "WHERE s.id = :data", Student.class);

        query.setParameter("data", theId);

        return query.getSingleResult();
    }

    /** Updates a Student. */
    @Override
    @Transactional
    public void update(Student student) {
        em.merge(student);
    }

    /** Deletes a Student by ID. */
    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        Student student = em.find(Student.class, theId);

        if (student != null) {
            List<Course> courses = student.getCourses();
            for (Course c : courses) {
                c.getStudents().remove(student);
            }
            em.remove(student);
        }
    }
}