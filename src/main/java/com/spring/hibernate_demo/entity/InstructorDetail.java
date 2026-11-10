package com.spring.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entity class representing detailed information associated with an Instructor.
 */
@Entity
@Table(name = "instructorDetail")
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "hobby")
    private String hobby;

    @OneToOne(mappedBy = "instructorDeets", cascade = {
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.PERSIST,
        CascadeType.REFRESH
    })
    private Instructor instructor;

    /** No-arg constructor required by JPA. */
    public InstructorDetail() {
    }

    /** Constructor to initialize InstructorDetail with subject and hobby. */
    public InstructorDetail(String subject, String hobby) {
        this.subject = subject;
        this.hobby = hobby;
    }

    /** Gets the instructor detail ID. */
    public int getId() {
        return id;
    }

    /** Sets the instructor detail ID. */
    public void setId(int id) {
        this.id = id;
    }

    /** Gets the taught subject. */
    public String getSubject() {
        return subject;
    }

    /** Sets the taught subject. */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /** Gets the instructor's hobby. */
    public String getHobby() {
        return hobby;
    }

    /** Sets the instructor's hobby. */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /** Gets the associated instructor entity. */
    public Instructor getInstructor() {
        return instructor;
    }

    /** Sets the associated instructor entity. */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /** Returns a string representation of the instructor detail. */
    @Override
    public String toString() {
        return "InstructorDetail [id=" + id + ", subject=" + subject + ", hobby=" + hobby + "]";
    }
}