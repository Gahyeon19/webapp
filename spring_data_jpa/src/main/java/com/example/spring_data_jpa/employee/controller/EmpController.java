package com.example.spring_data_jpa.employee.controller;

import com.example.spring_data_jpa.employee.domain.Employee;
import com.example.spring_data_jpa.employee.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emps")
@RequiredArgsConstructor
public class EmpController {
    private final EmpService empService;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return empService.saveEmp(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return empService.getAllEmp();
    }

    @GetMapping("/{empId}")
    public Optional<Employee> getEmployeeById(@PathVariable("empId") String empId) {
        return empService.getOneEmp(empId);
    }

    @GetMapping("/name/{empName}")
    public List<Employee> getAllEmployeesByName(@PathVariable("empName") String empName) {
        return empService.getAllEmpWithEmpName(empName);
    }

    @PutMapping("/{empId}")
    public Employee updateEmployeeSalary(@RequestBody Employee employee) {
        return empService.updateEmp(employee);
    }

    @DeleteMapping("/{empId}")
    public void deleteEmployee(@PathVariable("empId") String empId) {
        empService.deleteEmp(empId);
    }

}
