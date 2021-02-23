package com.webproject.aibalkanforumproject.model.exceptions;

public class InvalidCompanyNameException extends RuntimeException {
    public InvalidCompanyNameException(String companyId) {
        super(String.format("Company with that name not found",companyId));
    }
}
