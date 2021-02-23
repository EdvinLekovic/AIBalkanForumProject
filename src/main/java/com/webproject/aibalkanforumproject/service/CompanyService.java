package com.webproject.aibalkanforumproject.service;


import com.webproject.aibalkanforumproject.model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    List<Company> findByName(String name);
}
