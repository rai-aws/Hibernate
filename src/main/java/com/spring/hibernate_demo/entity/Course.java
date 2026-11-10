package com.spring.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Course record in the database.
 */
@Entity 
@Table(name = "course")
public class Course {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.PERSIST,
        CascadeType.REFRESH
    })
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> review;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.PERSIST,
        CascadeType.REFRESH
    })
    @JoinTable(
        name = "course_student",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    /** No-arg constructor required by JPA. */
    public Course() {
    }

    /** Constructor to initialize a Course with a title. */
    public Course(String title) {
        this.title = title;
    }

    /** Gets the course ID. */
    public int getId() {
        return id;
    }

    /** Sets the course ID. */
    public void setId(int id) {
        this.id = id;
    }

    /** Gets the course title. */
    public String getTitle() {
        return title;
    }

    /** Sets the course title. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Gets the assigned instructor. */
    public Instructor getInstructor() {
        return instructor;
    }

    /** Sets the assigned instructor. */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /** Helper method to add a review to the course. */
    public void addReview(Review rev) {
        if (review == null) {
            review = new ArrayList<>();
        }
        review.add(rev);
    }

    /** Gets the list of course reviews. */
    public List<Review> getReview() {
        return review;
    }

    /** Sets the list of course reviews. */
    public void setReview(List<Review> review) {
        this.review = review;
    }

    /** Helper method to add an enrolled student to the course. */
    public void addStudent(Student stud) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(stud);
    }

    /** Gets the list of enrolled students. */
    public List<Student> getStudents() {
        return students;
    }

    /** Sets the list of enrolled students. */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /** Returns a string representation of the course. */
    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + "]";
    }
}