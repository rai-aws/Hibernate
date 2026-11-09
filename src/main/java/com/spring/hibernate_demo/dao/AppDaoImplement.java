package com.spring.hibernate_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDaoImplement implements AppDao {

    private EntityManager em;

    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        em.persist(instructor);
    }

    @Override 
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    @Override 
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor ins = em.find(Instructor.class, theId);
        em.remove(ins);
    }

    @Override 
    @Transactional 
    public void deleteInstructorDetail(int theId){
        InstructorDetail deets = em.find(InstructorDetail.class, theId);

        deets.getInstructor().setInstructorDeets(null);
        em.remove(deets);
    }

    @Override 
    public List<Course> findCoursesByInstructorId(int theId){
        TypedQuery<Course> query = em.createQuery("From Course  WHERE instructor.id=:data", Course.class);

        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();
        return courses;
    }

    public Instructor findInstructorByJoinFetch(int theId){
        TypedQuery<Instructor> query = em.createQuery(
                        "SELECT i from Instructor i "
                        +"JOIN FETCH i.courses " 
                        + "JOIN FETCH i.instructorDeets "
                        +"WHERE i.id=:data", Instructor.class);

        query.setParameter("data", theId);
        
        Instructor ins = query.getSingleResult();
        return ins;
    }

    @Override 
    @Transactional 
    public void update(Instructor in){
        em.merge(in);
    }
}
