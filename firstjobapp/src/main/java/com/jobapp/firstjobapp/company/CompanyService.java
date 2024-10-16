package com.jobapp.firstjobapp.company;

import com.jobapp.firstjobapp.Review.Review;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany (Company company, Long id);

    void createCompany (Company company);

    boolean deleteCompany (Long id);

    Company getCompanyById (Long id);

//    void addReview(Long companyId, Review review);
}
