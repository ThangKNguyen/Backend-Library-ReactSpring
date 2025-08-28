package com.luv2code.spring_boot_library.controller;

import com.luv2code.spring_boot_library.requestmodels.ReviewRequest;
import com.luv2code.spring_boot_library.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    // Custom claim key for user email configured in Auth0
    private static final String EMAIL_CLAIM = "https://thang-lib.com/email";

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/secure")
    public void postReview(@RequestBody ReviewRequest reviewRequest,
                           @AuthenticationPrincipal Jwt jwt) throws Exception {

        // Try custom claim first, then standard "email", then fallback to subject ("sub")
        String userEmail = jwt.getClaimAsString(EMAIL_CLAIM);
        if (userEmail == null) userEmail = jwt.getClaimAsString("email");
        if (userEmail == null) userEmail = jwt.getSubject();

        reviewService.postReview(userEmail, reviewRequest);
    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestParam Long bookId,
                                    @AuthenticationPrincipal Jwt jwt) throws Exception {

        // Try custom claim -> standard email -> subject ("sub")
        String userEmail = jwt.getClaimAsString(EMAIL_CLAIM);
        if (userEmail == null) userEmail = jwt.getClaimAsString("email");
        if (userEmail == null) userEmail = jwt.getSubject();

        if (userEmail == null) {
            throw new Exception("User email is missing");
        }

        return reviewService.userReviewListed(userEmail, bookId);
    }
}

