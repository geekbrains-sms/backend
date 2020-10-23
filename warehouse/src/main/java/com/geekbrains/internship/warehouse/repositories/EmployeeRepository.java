package com.geekbrains.internship.warehouse.repositories;

import java.util.Date;
import java.util.List;

import com.geekbrains.internship.warehouse.entities.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

// This is an Interface.
// No need Annotation here
public interface EmployeeRepository extends MongoRepository<Employee, Long> { // Long: Type of Employee ID.

    Employee findByEmpNo(String empNo);

    List<Employee> findByFullNameLike(String fullName);

    List<Employee> findByHireDateGreaterThan(Date hireDate);

    // Supports native JSON query string
    @Query("{fullName:'?0'}")
    List<Employee> findCustomByFullName(String fullName);

}
