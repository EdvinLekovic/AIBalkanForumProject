package com.webproject.aibalkanforumproject.service.impl;

import com.webproject.aibalkanforumproject.model.Company;
import com.webproject.aibalkanforumproject.repository.CompanyRepository;
import com.webproject.aibalkanforumproject.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public List<Company> findByName(String name) {
        return companyRepository.findAllByNameLike(name);
    }
}
