package com.luv2code.spring_boot_library.dao;

import com.luv2code.spring_boot_library.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    //find by user email and bookid, null if theres none
    Checkout findByUserEmailAndBookId(String userEmail, Long bookId);

    //how many checkouts the current user is checking out
    //returns all books being checkout within that email
    List<Checkout> findBooksByUserEmail(String userEmail);
}
