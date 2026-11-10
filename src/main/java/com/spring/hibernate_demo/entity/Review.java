package com.spring.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a Review record in the database.
 */
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comments")
    private String comments;

    /** No-arg constructor required by JPA. */
    public Review() {
    }

    /** Constructor to initialize a Review with comments. */
    public Review(String comments) {
        this.comments = comments;
    }

    /** Gets the review ID. */
    public int getId() {
        return id;
    }

    /** Sets the review ID. */
    public void setId(int id) {
        this.id = id;
    }

    /** Gets the review comments. */
    public String getComments() {
        return comments;
    }

    /** Sets the review comments. */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /** Returns a string representation of the review. */
    @Override
    public String toString() {
        return "Review [id=" + id + ", comments=" + comments + "]";
    }
}