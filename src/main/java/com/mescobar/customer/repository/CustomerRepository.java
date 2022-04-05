package com.mescobar.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mescobar.customer.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
