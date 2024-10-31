package com.example.spring_data_jpa.employee.service;

import com.example.spring_data_jpa.employee.domain.Employee;
import com.example.spring_data_jpa.employee.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional  // tx.begin() + tx.commit() + tx.rollback()
public class EmpService {
    private final EmpRepository empRepository;

    @Transactional(readOnly = true)
    public Optional<Employee> getOneEmp(String empId) {
        return empRepository.findById(empId);
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmp() {
        return empRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmpWithEmpName(String empName) {
        return empRepository.findByEmpNameContaining(empName);
    }

    public Employee saveEmp(Employee emp) {
        Employee saveEmp = empRepository.save(emp);
        return saveEmp;
    }

    public Employee updateEmp(Employee emp) {   //salary 변경
        Employee updateEmp = empRepository.findById(emp.getEmpId()).get();
        updateEmp.setSalary(emp.getSalary());
        return empRepository.save(updateEmp);
    }

    public void deleteEmp(String empId) {
        empRepository.deleteById(empId);
    }
}
