package com.jobapp.firstjobapp.Review;


import com.jobapp.firstjobapp.company.Company;
import com.jobapp.firstjobapp.company.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")       //i.e : /companies/compId/reviews ==> /companies/3/reviews
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {       //parameter name should be same as the url in request mapping()
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
     }

     @PostMapping("/reviews")
    public ResponseEntity<String> addReview (@RequestBody Review review, @PathVariable Long companyId) {
         boolean reviewAdded = reviewService.addReview(companyId, review);
         if (reviewAdded)
             return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
         return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
     }

     //get a specific review of a specific company : review id + compId
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview (@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReview(companyId, reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    //update a review by review id for a specific comp id :
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview (@PathVariable Long companyId, @PathVariable Long reviewId, Review review) {
        boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);
        if (isReviewUpdated) return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("Review Not updated", HttpStatus.NOT_FOUND);
    }

    //delete a review by review id for a given comp id :
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview (@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if (isDeleted) return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>("Review not found ", HttpStatus.NOT_FOUND);
    }
}