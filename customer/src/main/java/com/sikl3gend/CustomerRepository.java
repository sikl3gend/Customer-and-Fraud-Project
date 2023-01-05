package com.sikl3gend;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository  extends JpaRepository<Customer, Integer> {
}
