package com.luv2code.spring_boot_library.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "rating")
    private double rating;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "review_description")
    private String reviewDescription;

    // --- Constructors ---
    public Review() {
    }

    public Review(Long id, String userEmail, LocalDateTime date, double rating, Long bookId, String reviewDescription) {
        this.id = id;
        this.userEmail = userEmail;
        this.date = date;
        this.rating = rating;
        this.bookId = bookId;
        this.reviewDescription = reviewDescription;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    // --- toString ---
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", date=" + date +
                ", rating=" + rating +
                ", bookId=" + bookId +
                ", reviewDescription='" + reviewDescription + '\'' +
                '}';
    }

    // --- equals ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Double.compare(review.rating, rating) == 0 &&
                Objects.equals(id, review.id) &&
                Objects.equals(userEmail, review.userEmail) &&
                Objects.equals(date, review.date) &&
                Objects.equals(bookId, review.bookId) &&
                Objects.equals(reviewDescription, review.reviewDescription);
    }

    // --- hashCode ---
    @Override
    public int hashCode() {
        return Objects.hash(id, userEmail, date, rating, bookId, reviewDescription);
    }
}

