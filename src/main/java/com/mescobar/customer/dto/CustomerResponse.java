package com.mescobar.customer.dto;

import java.time.LocalDate;

import com.mescobar.customer.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

	private Integer id;

	private LocalDate createdAt;

	private String gender;

	private String phoneNumber;

	public static CustomerResponse valueOf(Customer customer) {
		return builder().id(customer.getId()).createdAt(customer.getCreatedAt()).gender(customer.getGender())
				.phoneNumber(customer.getPhoneNumber()).build();
	}
}
