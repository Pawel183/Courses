package com.course.accounts.repository;

import com.course.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
