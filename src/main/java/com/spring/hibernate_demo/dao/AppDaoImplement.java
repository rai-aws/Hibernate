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
 * Implementation of the AppDao interface using JPA EntityManager.
 */
@Repository
public class AppDaoImplement implements AppDao {

    private final EntityManager em;

    /** Constructor injection for EntityManager. */
    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    /** Saves an Instructor entity and its details. */
    @Override
    @Transactional
    public void save(Instructor instructor) {
        em.persist(instructor);
    }

    /** Retrieves an Instructor by ID. */
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

    /** Deletes an InstructorDetail entity by ID without deleting the Instructor. */
    @Override
    @Transactional
    public void deleteInstructorDetail(int theId) {
        InstructorDetail deets = em.find(InstructorDetail.class, theId);

        deets.getInstructor().setInstructorDeets(null);
        em.remove(deets);
    }

    /** Retrieves all Course entities with a specific Instructor ID. */
    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = em.createQuery(
                "FROM Course WHERE instructor.id = :data", Course.class);

        query.setParameter("data", theId);

        return query.getResultList();
    }

    /** Retrieves an Instructor and eagerly fetches associated Courses using a JOIN FETCH query. */
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

    /** Updates an existing Instructor record. */
    @Override
    @Transactional
    public void update(Instructor in) {
        em.merge(in);
    }

    /** Retrieves a Course entity by ID. */
    @Override
    public Course findCourseById(int theId) {
        return em.find(Course.class, theId);
    }

    /** Updates an existing Course record. */
    @Override
    @Transactional
    public void update(Course course) {
        em.merge(course);
    }

    /** Deletes an Instructor by ID without deleting associated Course records. */
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

    /** Deletes a Course record by ID. */
    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course course = em.find(Course.class, theId);
        em.remove(course);
    }

    /** Saves a Course entity. */
    @Override
    @Transactional
    public void save(Course theCourse) {
        em.persist(theCourse);
    }

    /** Retrieves a Course along with its associated Review records by Course ID. */
    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c "
                + "JOIN FETCH c.review "
                + "WHERE c.id = :data", Course.class);

        query.setParameter("data", theId);

        return query.getSingleResult();
    }

    /** Retrieves a Course along with its enrolled Student records by Course ID. */
    @Override
    public Course findCourseAndStudentByCourseId(int theId) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c "
                + "JOIN FETCH c.students "
                + "WHERE c.id = :data", Course.class);

        query.setParameter("data", theId);

        return query.getSingleResult();
    }

    /** Retrieves a Student along with registered Course records by Student ID. */
    @Override
    public Student findStudentAndCourseByStudentId(int theId) {
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s "
                + "JOIN FETCH s.courses "
                + "WHERE s.id = :data", Student.class);

        query.setParameter("data", theId);

        return query.getSingleResult();
    }

    /** Updates an existing Student record. */
    @Override
    @Transactional
    public void update(Student student) {
        em.merge(student);
    }

    /** Deletes a Student record by ID. */
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