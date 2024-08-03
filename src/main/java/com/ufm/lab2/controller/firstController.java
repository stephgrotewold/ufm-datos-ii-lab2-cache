package com.ufm.lab2.controller;

import com.ufm.lab2.model.Employee;
import com.ufm.lab2.service.EmployeeService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class firstController {

    private EmployeeService employeeService;

    @GetMapping("/hello")
    public String get(){
        log.info("call");
        return "world";
    }

    @GetMapping("/employee/{empNo}")
    public Employee getEmployee(@PathVariable Integer empNo){
        log.info("get employee number {}",empNo);
        try {
            var response = employeeService.getEmployee(empNo);
            return response;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new Employee();
        }

    }

    @GetMapping("/employee/{salary}/{title}")
    public List<Employee> getEmployeeBySalaryAndTitle(@PathVariable Double salary,
                                                      @PathVariable String title){
        log.info("get employee salary and title {} {}",salary, title);
        try {
            var response = employeeService.getEmployeeBySalaryAndTitle(salary, title);
            return response;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return List.of();
        }
    }
}
