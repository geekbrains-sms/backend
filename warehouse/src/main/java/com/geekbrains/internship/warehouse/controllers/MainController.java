package com.geekbrains.internship.warehouse.controllers;

import com.geekbrains.internship.warehouse.entities.Employee;
import com.geekbrains.internship.warehouse.repositories.EmployeeRepository;
import com.geekbrains.internship.warehouse.repositories.EmployeeRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    private static final String[] NAMES = { "Tom", "Jerry", "Donald" };

    @Autowired
    private EmployeeRepositoryCustom employeeRepositoryCustom;

    @Autowired
    private EmployeeRepository employeeRepository;

    @ResponseBody
    @RequestMapping("/")
    public String home() {
        String html = "";
        html += "<ul>";
        html += " <li><a href='/testInsert'>Test Insert</a></li>";
        html += " <li><a href='/showAllEmployee'>Show All Employee</a></li>";
        html += " <li><a href='/showFullNameLikeTom'>Show All 'Tom'</a></li>";
        html += " <li><a href='/deleteAllEmployee'>Delete All Employee</a></li>";
        html += "</ul>";
        return html;
    }

    @ResponseBody
    @RequestMapping("/testInsert")
    public String testInsert() {
        Employee employee = new Employee();

        long id = this.employeeRepositoryCustom.getMaxEmpId() + 1;
        int idx = (int) (id % NAMES.length);
        String fullName = NAMES[idx] + " " + id;

        employee.setId(id);
        employee.setEmpNo("E" + id);
        employee.setFullName(fullName);
        employee.setHireDate(new Date());
        this.employeeRepository.insert(employee);

        return "Inserted: " + employee;
    }

    @ResponseBody
    @RequestMapping("/showAllEmployee")
    public String showAllEmployee() {

        List<Employee> employees = this.employeeRepository.findAll();

        String html = "";
        for (Employee emp : employees) {
            html += emp + "<br>";
        }

        return html;
    }

    @ResponseBody
    @RequestMapping("/showFullNameLikeTom")
    public String showFullNameLikeTom() {

        List<Employee> employees = this.employeeRepository.findByFullNameLike("Tom");

        String html = "";
        for (Employee emp : employees) {
            html += emp + "<br>";
        }

        return html;
    }

    @ResponseBody
    @RequestMapping("/deleteAllEmployee")
    public String deleteAllEmployee() {

        this.employeeRepository.deleteAll();
        return "Deleted!";
    }

}