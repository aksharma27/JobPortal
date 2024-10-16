package com.jobapp.firstjobapp.Review.ReviewServiceImpl;

import com.jobapp.firstjobapp.Review.Review;
import com.jobapp.firstjobapp.Review.ReviewRepository;
import com.jobapp.firstjobapp.Review.ReviewService;
import com.jobapp.firstjobapp.company.Company;
import com.jobapp.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private final CompanyService companyService;      //for adding reviews we need this object of company service

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long compId) {
        List<Review> reviews = reviewRepository.findByCompanyId(compId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        //apply filter on review stream : review.getId.euqals
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if (companyService.getCompanyById(companyId) != null) {
            //check if given review exists or not :
                updatedReview.setCompany(companyService.getCompanyById(companyId));
                updatedReview.setId(reviewId);
                reviewRepository.save(updatedReview);
                return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId) != null) {
            List<Review> reviews = reviewRepository.findByCompanyId(companyId);
            Review isReviewPresent = reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
            if (isReviewPresent != null) {
                Company company = isReviewPresent.getCompany();
                company.getReviews().remove(isReviewPresent);
                isReviewPresent.setCompany(null);
                companyService.updateCompany(company, companyId);
                reviewRepository.deleteById(reviewId);
                return true;
            }
        }
        return false;
    }

}
