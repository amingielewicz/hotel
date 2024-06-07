package com.reservationsystem.service;

import com.reservationsystem.dto.Customer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomerServiceTest {

    private Customer EXPECTED_CUSTOMER_FIRST = new Customer("Tomasz", "Kowalski", "8212150594584");
    private Customer EXPECTED_CUSTOMER_SECOND = new Customer("Krystiano", "Ronaldo", "821198094521");


    @Test
    public void create() {
        //Given
        com.reservationsystem.service.CustomerService customerService = new CustomerService();
        customerService.create(EXPECTED_CUSTOMER_FIRST);
        customerService.create(EXPECTED_CUSTOMER_SECOND);
        //When
        List<Customer> results = customerService.findAll();
        //Expected
        assertEquals(2, results.size());
        assertTrue(results.contains(EXPECTED_CUSTOMER_FIRST));
//        List<String> collect = results.stream().map(Customer::getName).collect(Collectors.toList());
//        assertTrue(collect.contains(expectedCustomerFirst.getName()));
//        assertFalse(collect.contains("Rafał"));
        List<String> actualName = new ArrayList<>();
        for (Customer actual : results) {
            actualName.add(actual.getName());
        }
        assertTrue(actualName.contains(EXPECTED_CUSTOMER_FIRST.getName()));
    }

    @Test
    public void update() {
        //Given
        CustomerService customerService = new CustomerService();
        int id = customerService.create(EXPECTED_CUSTOMER_FIRST);
        customerService.create(EXPECTED_CUSTOMER_SECOND);
        //When
        List<Customer> result = customerService.findAll();
        customerService.update(new Customer(id, "Adam", "Piotrkowicz", "52487545214"));
        //Expected
        assertEquals(2, result.size());
        assertTrue(result.contains(EXPECTED_CUSTOMER_FIRST));
        List<String> actualName = new ArrayList<>();
        for (Customer actual : result) {
            actualName.add(actual.getName());
        }
        List<Integer> actualId = new ArrayList<>();
        for (Customer customer : result) {
            actualId.add(customer.getId());
        }
        assertTrue(actualId.contains(7));
        assertTrue(actualName.contains(EXPECTED_CUSTOMER_FIRST.getName()));
    }

    @Test
    public void delete() {
        //Given
        CustomerService customerService = new CustomerService();
        int id = customerService.create(EXPECTED_CUSTOMER_FIRST);
        int secondId = customerService.create(EXPECTED_CUSTOMER_SECOND);
        //When
        customerService.delete(secondId);
        List<Customer> result = customerService.findAll();
        //Expected
        System.out.println(result.size());
        assertEquals(1, result.size());
    }

    @Test
    public void findAll() {
        //Given
        CustomerService customerService = new CustomerService();
        int id = customerService.create(EXPECTED_CUSTOMER_FIRST);
        int secondId = customerService.create(EXPECTED_CUSTOMER_SECOND);
        //When
        List<Customer> result = customerService.findAll();
        //Expected
        assertEquals(2, result.size());
    }
}
