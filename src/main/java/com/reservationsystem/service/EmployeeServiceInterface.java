package com.reservationsystem.service;

import com.reservationsystem.dto.Employee;

import java.util.List;

public interface EmployeeServiceInterface {
    int create(Employee employee);
    List<Employee> findAll();
    Employee getEmployee(int id);
    void update(Employee updateEmployee);
    boolean checkId(int id);
    boolean delete(int id);
}
