package com.jobapp.firstjobapp.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long compId);      //no need to create imp of this : spring jpa automatically create the functionality by breaking down the method name :
//        i.e, findBy CompanyId -> That's soo cool ==> find all records by companyId proviced ==> automatically a query will be called behind
}
