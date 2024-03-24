package com.reservationsystem.service;

import com.reservationsystem.Menu;
import com.reservationsystem.dto.Common;
import com.reservationsystem.dto.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomerService {
   private List<Customer> customerList;

    public int create(Customer customer) {
        if (customerList != null) {
            customerList.add(customer);
        } else {
            customerList = new ArrayList<>();
            customerList.add(customer);
        }
        int id = customer.getId();
        Customer.counterId++;
        return customer.getId();
    }
    public List<Customer> findAll() {
//        if (customerList == null) {
//            System.out.println("Brak listy.");
//            Menu.showMenu();
//        }
            return customerList;
        }

    public Customer getCustomer(int id) {
        for(Customer customer : customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public void update(Customer updateCustomer) {
        customerList.forEach(customer -> {
        if(customer.getId() == updateCustomer.getId()) {
            customer.setName(updateCustomer.getName());
            customer.setSurname(updateCustomer.getSurname());
            customer.setPesel(updateCustomer.getPesel());
        }
        }
            );
    }
    public boolean checkId(int id) {
        AtomicBoolean isId = new AtomicBoolean(false);
        customerList.forEach(customer -> {
            if(customer.getId() == id) {
                isId.set(true);
            }
        });
        if (isId.get()){
            return true;
        }
        return false;
    }

    public void delete(int id) {
            customerList.remove(id);
    }
    }
