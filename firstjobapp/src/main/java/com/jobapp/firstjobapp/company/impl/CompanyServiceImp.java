package com.jobapp.firstjobapp.company.impl;

import com.jobapp.firstjobapp.company.Company;
import com.jobapp.firstjobapp.company.CompanyRepository;
import com.jobapp.firstjobapp.company.CompanyService;
import com.jobapp.firstjobapp.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImp implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImp (CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            companyToUpdate.setJobs(company.getJobs());
            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;    }
}
