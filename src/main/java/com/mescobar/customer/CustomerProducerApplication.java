package com.mescobar.customer;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.Column;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mescobar.customer.entity.Customer;
import com.mescobar.customer.repository.CustomerRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

@SpringBootApplication
public class CustomerProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerProducerApplication.class, args);
	}
	
	@Bean
	public ApplicationRunner run(CustomerRepository customerRepository) throws Exception {
		
		return (ApplicationArguments args) -> {
			

			/*
			 * List<Customer> customers = Arrays.asList( new Customer(Instant.now(), "male",
			 * "434322"), new Plant("sour cherry", "prunus cerasus", "rosaceae"), new
			 * Plant("asian pear", "pyrus pyrifolia", "rosaceae") );
			 */
			
			//customerRepository.saveAll(customers);
			
			customerRepository.saveAll(generateCustomerList());
			
		};

	}
	
	private List<Customer> generateCustomerList() {
		
        return IntStream.range(0, 1000)
                .mapToObj(i -> 
                		Customer.builder()
                		.createdAt(LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 10)))) )
                		.gender(randomString("WM"))
                		.phoneNumber(RandomStringUtils.randomNumeric(15))
                		.build()
                
                		)
                .collect(Collectors.toList());
    }
	
	 private static String randomString(String fromCharacters) {
	        Random random = new Random();
	        return String.valueOf(fromCharacters.charAt(random.nextInt(fromCharacters.length())));
	    }

}
