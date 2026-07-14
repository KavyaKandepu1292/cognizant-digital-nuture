package com.yourpackage.service;

import com.yourpackage.dao.DepartmentDao;
import com.yourpackage.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public ArrayList<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }
}