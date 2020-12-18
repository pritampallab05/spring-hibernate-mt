package com.pritam.springmt.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pritam.springmt.entity.Company;
import com.pritam.springmt.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public void save(Company company) {
		companyRepository.save(company);
	}

	public List<Company> getAll() throws SQLException {
		return companyRepository.findAll();
	}

}
