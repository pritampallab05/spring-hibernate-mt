package com.pritam.springmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pritam.springmt.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findByName(String name);

	void deleteByName(String name);

}
