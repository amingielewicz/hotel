package com.reservationsystem.service;

import com.reservationsystem.dto.Customer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerServiceTest {

    private final Customer EXPECTED_CUSTOMER_FIRST = new Customer("Tomasz", "Kowalski", "8212150594584");
    private final Customer EXPECTED_CUSTOMER_SECOND = new Customer("Krystiano", "Ronaldo", "821198094521");

    @Test
    public void create() {
        //Given
        CustomerService customerService = new CustomerService();
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
        assertTrue(actualName.contains(EXPECTED_CUSTOMER_FIRST.getName()));
    }

    @Test
    public void delete() {
        //Given
        CustomerService customerService = new CustomerService();
        int id = customerService.create(EXPECTED_CUSTOMER_FIRST);
        int secondId = customerService.create(EXPECTED_CUSTOMER_SECOND);
        //When
        List<Customer> result = customerService.findAll();
        customerService.delete(secondId);
        //Expected
        assertEquals(1, result.size());
        assertFalse(result.contains(EXPECTED_CUSTOMER_SECOND));
        List<String> actualName = new ArrayList<>();
        for (Customer actual : result) {
            actualName.add(actual.getName());
        }
        assertFalse(actualName.contains(EXPECTED_CUSTOMER_SECOND.getName()));
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
