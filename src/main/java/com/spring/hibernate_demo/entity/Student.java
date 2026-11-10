package com.spring.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Student record in the database.
 */
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToMany(
        mappedBy = "students",
        fetch = FetchType.LAZY,
        cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
        }
    )
    private List<Course> courses;

    /** No-arg constructor required by JPA. */
    public Student() {
    }

    /** Constructor to initialize a Student with first name, last name, and email. */
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /** Gets the student ID. */
    public int getId() {
        return id;
    }

    /** Sets the student ID. */
    public void setId(int id) {
        this.id = id;
    }

    /** Gets the student's first name. */
    public String getFirstName() {
        return firstName;
    }

    /** Sets the student's first name. */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** Gets the student's last name. */
    public String getLastName() {
        return lastName;
    }

    /** Sets the student's last name. */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** Gets the student's email address. */
    public String getEmail() {
        return email;
    }

    /** Sets the student's email address. */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Helper method to add a course and establish bidirectional mapping. */
    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.addStudent(this);
    }

    /** Gets the list of enrolled courses. */
    public List<Course> getCourses() {
        return courses;
    }

    /** Sets the list of enrolled courses. */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /** Returns a string representation of the student. */
    @Override
    public String toString() {
        return "Student [id=" + id
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", email=" + email + "]";
    }
}