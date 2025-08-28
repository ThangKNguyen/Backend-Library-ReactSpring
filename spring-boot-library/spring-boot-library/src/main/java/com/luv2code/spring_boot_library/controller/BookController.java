package com.luv2code.spring_boot_library.controller;

import com.luv2code.spring_boot_library.entity.Book;
import com.luv2code.spring_boot_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    // Custom claim key for user email that we configured in Auth0
    private static final String EMAIL_CLAIM = "https://thang-lib.com/email";

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestParam Long bookId, @AuthenticationPrincipal Jwt jwt) throws Exception {
        // Try to extract the user’s email from the custom claim first
        String userEmail = jwt.getClaimAsString(EMAIL_CLAIM);

        // If the custom claim doesn’t exist, fall back to the default "email" claim
        if (userEmail == null) userEmail = jwt.getClaimAsString("email");

        // If there’s still no email claim, fall back to the subject ("sub") field
        // The "sub" is a unique identifier for the user provided by the identity provider
        if (userEmail == null) userEmail = jwt.getSubject();
        return bookService.checkoutBook(userEmail, bookId);
    }

    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestParam Long bookId,
                                      @AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getClaimAsString(EMAIL_CLAIM);
        if (userEmail == null) userEmail = jwt.getClaimAsString("email");
        if (userEmail == null) userEmail = jwt.getSubject();
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(@AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getClaimAsString(EMAIL_CLAIM);
        if (userEmail == null) userEmail = jwt.getClaimAsString("email");
        if (userEmail == null) userEmail = jwt.getSubject();
        return bookService.currentLoansCount(userEmail);
    }
}


