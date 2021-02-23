package com.webproject.aibalkanforumproject.repository;


import com.webproject.aibalkanforumproject.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,String> {
    List<Company> findAllByNameLike(String name);
}
