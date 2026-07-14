package com.yourpackage.dao;

import com.yourpackage.model.Employee;
import java.util.ArrayList;

public class EmployeeDao {

    private static ArrayList<Employee> EMPLOYEE_LIST;

    public EmployeeDao(ArrayList<Employee> employeeList) {
        EMPLOYEE_LIST = employeeList;
    }

    public ArrayList<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }
}