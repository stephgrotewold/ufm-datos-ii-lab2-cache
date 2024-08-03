package com.ufm.lab2.service;

import com.ufm.lab2.model.Employee;
import com.ufm.lab2.model.EmployeeRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Cacheable("employee")
    public Employee getEmployee(Integer empNo){
        try {
            var response = employeeRepository.getEmployeeByEmpNo(empNo);
            return response;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new Employee();
        }
    }

    @Cacheable("employeeBySalary")
    public List<Employee> getEmployeeBySalaryAndTitle(Double salary,
                                                      String title){
        try {
            var response = employeeRepository.getEmployeeBySalaryAndTitle(salary, title);
            return response;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return List.of();
        }
    }

}
