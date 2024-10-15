package com.jobapp.firstjobapp.company;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController (CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies () {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        companyService.updateCompany(company, id);
        return new ResponseEntity<>("Company updated Successfully", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCompany (@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("New company added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCompany (@PathVariable Long id) {
        boolean deleted = companyService.deleteCompany(id);
        if (deleted)
            return new ResponseEntity<>("Company with id : " + id + " deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>("Company with id : " + id + " Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
