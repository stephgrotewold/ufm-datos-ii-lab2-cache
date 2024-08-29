
package com.ufm.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MaxSalaryController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/max_salary_department")
    public Map<String, Object> getMaxSalaryDepartment(@RequestParam("year") int year) {
        String query = "SELECT d.dept_name, MAX(s.salary) as max_salary " +
                       "FROM salaries s " +
                       "JOIN dept_emp de ON s.emp_no = de.emp_no " +
                       "JOIN departments d ON de.dept_no = d.dept_no " +
                       "WHERE YEAR(s.from_date) <= ? AND YEAR(s.to_date) >= ? " +
                       "GROUP BY d.dept_name " +
                       "ORDER BY max_salary DESC " +
                       "LIMIT 1";

        return jdbcTemplate.queryForMap(query, year, year);
    }
}
