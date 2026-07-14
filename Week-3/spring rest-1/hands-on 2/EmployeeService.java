package com.yourpackage.service;

import com.yourpackage.dao.EmployeeDao;
import com.yourpackage.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public ArrayList<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }
}