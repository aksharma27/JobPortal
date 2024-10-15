package com.jobapp.firstjobapp.Review.ReviewServiceImpl;

import com.jobapp.firstjobapp.Review.Review;
import com.jobapp.firstjobapp.Review.ReviewRepository;
import com.jobapp.firstjobapp.Review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long compId) {
        List<Review> reviews = reviewRepository.findByCompanyId(compId);
        return reviews;
    }
}
