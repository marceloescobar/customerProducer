package com.mescobar.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.mescobar.customer.dto.CustomerResponse;
import com.mescobar.customer.entity.Customer;
import com.mescobar.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;

	public Page<CustomerResponse> getAllCustomers(Integer pageNumber, Integer pageSize) {
		LOGGER.info("Getting all customers from a pageNumber {} with a pageSize {}", pageNumber, pageSize);

		PageRequest page = PageRequest.of(pageNumber, pageSize);
		Page<Customer> customerEntityPage = customerRepository.findAll(page);

		List<CustomerResponse> customerResponses = customerEntityPage.getContent().stream()
				.map(CustomerResponse::valueOf).collect(Collectors.toList());

		Page<CustomerResponse> customerResponsePage = PageableExecutionUtils.getPage(customerResponses, page,
				customerEntityPage::getTotalElements);

		return customerResponsePage;
	}
}
