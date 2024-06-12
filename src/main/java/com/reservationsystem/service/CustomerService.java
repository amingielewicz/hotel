package com.reservationsystem.service;

import com.reservationsystem.dto.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomerService implements CustomerServiceInterface {
    private List<Customer> customerList;

    public int create(Customer customer) {
        if (customerList != null) {
            customerList.add(customer);
        } else {
            customerList = new ArrayList<>();
            customerList.add(customer);
        }
        Customer.counterId++;
        return customer.getId();
    }

    public List<Customer> findAll() {
        return customerList;
    }

    public Customer getCustomer(int id) {
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public void update(Customer updateCustomer) {
        customerList.forEach(customer -> {
                    if (customer.getId() == updateCustomer.getId()) {
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
            if (customer.getId() == id) {
                isId.set(true);
            }
        });
        if (isId.get()) {
            return true;
        }
        return false;
    }

    public Boolean delete(int id) {
        int index = getIndexListById(id);
        Boolean idIsOnList;
        if (index != id) {
            customerList.remove(index);
            idIsOnList = true;
            System.out.println("Usunieto uzytkownika o id: " + id);
        }else {
            System.err.println("Brak uzytkownika o podanym id: " + id);
            idIsOnList = false;
        }
        return idIsOnList;
    }

    private int getIndexListById(int id) {
        int index = 0;
        if (customerList != null) {
            for (Customer customer : customerList) {
                if (customer.getId() == id){
                    index = customerList.indexOf(customer);
                    return index;
                }
            }
        }else {
            System.err.println("Brak klientow na liscie");
            return id;
        }
        return id;
    }
}


