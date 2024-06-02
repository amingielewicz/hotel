package com.reservationsystem.service;

import com.reservationsystem.dto.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class EmployeeService implements EmployeeServiceInterface{
    private List<Employee> employeeList;

    public int create(Employee employee) {
        if (employeeList != null) {
            employeeList.add(employee);
        } else {
            employeeList = new ArrayList<>();
            employeeList.add(employee);
        }
        int id = employee.getId();
        Employee.counterId++;
        return employee.getId();
    }


    public List<Employee> findAll() {
        return employeeList;
    }


    public Employee getEmployee(int id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public void update(Employee updateEmployee) {
        employeeList.forEach(employee -> {
                    if (employee.getId() == updateEmployee.getId()) {
                        employee.setName(updateEmployee.getName());
                        employee.setSurname(updateEmployee.getSurname());
                        employee.setRole(updateEmployee.getRole());
                        employee.setPersonalSkill(updateEmployee.getPersonalSkill());
                    }
                }
        );
    }

    public boolean checkId(int id) {
        AtomicBoolean isId = new AtomicBoolean(false);
        employeeList.forEach(employee -> {
            if (employee.getId() == id) {
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
            employeeList.remove(index);
            idIsOnList = true;
            System.out.println("Usunieto pracownika o id: " + id);
        }else {
            System.err.println("Brak pracownika o podanym id: " + id);
            idIsOnList = false;
        }
        return idIsOnList;
    }

    private int getIndexListById(int id) {
        int index = 0;
        if (employeeList != null) {
            for (Employee employee : employeeList) {
                if (employee.getId() == id){
                    index = employeeList.indexOf(employee);
                    return index;
                }
            }
        }else {
            System.err.println("Brak pracowników na liscie");
            return id;
        }
        return id;
    }


}
