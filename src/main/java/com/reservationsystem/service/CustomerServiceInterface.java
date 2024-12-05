package com.reservationsystem.service;

import com.reservationsystem.dto.Customer;

import java.util.List;

public interface CustomerServiceInterface {
    int create(Customer customer);
    List<Customer> findAll();
    Customer getCustomer(int id);
    void update(Customer updateCustomer);
    boolean checkId(int id);
    boolean delete(int id);
}
