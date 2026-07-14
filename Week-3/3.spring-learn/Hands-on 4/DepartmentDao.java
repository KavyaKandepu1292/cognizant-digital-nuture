package com.yourpackage.dao;

import com.yourpackage.model.Department;
import java.util.ArrayList;

public class DepartmentDao {

    private static ArrayList<Department> DEPARTMENT_LIST;

    public DepartmentDao(ArrayList<Department> departmentList) {
        DEPARTMENT_LIST = departmentList;
    }

    public ArrayList<Department> getAllDepartments() {
        return DEPARTMENT_LIST;
    }
}