package com.spring.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing an Instructor record in the database.
 */
@Entity
@Table(name = "instructor")
public class Instructor {

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDeets;

    @OneToMany(
        mappedBy = "instructor",
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
    public Instructor() {
    }

    /** Constructor to initialize an Instructor with first name, last name, and email. */
    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /** Gets the instructor ID. */
    public int getId() {
        return id;
    }

    /** Sets the instructor ID. */
    public void setId(int id) {
        this.id = id;
    }

    /** Gets the instructor's first name. */
    public String getFirstName() {
        return firstName;
    }

    /** Sets the instructor's first name. */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** Gets the instructor's last name. */
    public String getLastName() {
        return lastName;
    }

    /** Sets the instructor's last name. */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** Gets the instructor's email address. */
    public String getEmail() {
        return email;
    }

    /** Sets the instructor's email address. */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Gets the associated instructor details entity. */
    public InstructorDetail getInstructorDeets() {
        return instructorDeets;
    }

    /** Sets the associated instructor details entity. */
    public void setInstructorDeets(InstructorDetail instructorDeets) {
        this.instructorDeets = instructorDeets;
    }

    /** Helper method to add a course and establish bidirectional mapping. */
    public void add(Course tCourse) {
        if (this.courses == null) {
            this.courses = new ArrayList<>();
        }
        this.courses.add(tCourse);
        tCourse.setInstructor(this);
    }

    /** Gets the list of taught courses. */
    public List<Course> getCourses() {
        return courses;
    }

    /** Sets the list of taught courses. */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /** Returns a string representation of the instructor. */
    @Override
    public String toString() {
        return "Instructor [id=" + id
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", email=" + email + "]";
    }
}