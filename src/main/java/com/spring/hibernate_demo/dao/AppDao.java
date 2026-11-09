package com.spring.hibernate_demo.dao;

import java.util.List;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;

public interface AppDao {

    void save(Instructor instructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    void deleteInstructorDetail(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByJoinFetch(int theId);
    void update(Instructor in);
}
