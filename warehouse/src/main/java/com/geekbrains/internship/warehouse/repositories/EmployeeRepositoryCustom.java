package com.geekbrains.internship.warehouse.repositories;

import java.util.Date;

public interface EmployeeRepositoryCustom {

    public long getMaxEmpId();

    public long updateEmployee(String empNo, String fullName, Date hireDate);

}
