package com.pritam.springmt.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pritam.springmt.config.TenantContext;
import com.pritam.springmt.entity.Company;
import com.pritam.springmt.service.CompanyService;

@Controller
public class TenantController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("/save-data")
	@ResponseBody
	public ResponseEntity<List<Company>> SaveData(@RequestParam(name = "tenant", required = true) String tenant,
			@RequestParam(name = "name", required = true) String name) {
		// The tenant is fetched from the rest call
		// In reality it will be fetched from the JWT token
		TenantContext.setCurrentTenant(tenant);
		Company company = new Company();
		company.setName(name);
		companyService.save(company);
		List<Company> companyList = null;
		try {
			companyList = companyService.getAll();
			TenantContext.clear();
			return new ResponseEntity<>(companyList, HttpStatus.OK);
		} catch (SQLException e) {
			TenantContext.clear();
			return new ResponseEntity<>(companyList, HttpStatus.GATEWAY_TIMEOUT);
		}

	}
}
