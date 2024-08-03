package com.ufm.lab2.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee getEmployeeByEmpNo(Integer empNo);

    @Query( value = """
        select emp.* from employees.employees emp
        inner join employees.salaries sal on sal.emp_no = emp.emp_no
        inner join employees.titles ti on ti.emp_no = emp.emp_no
        where sal.salary > :salary and ti.title like %:title%
        group by 1; """, nativeQuery = true)
    List<Employee> getEmployeeBySalaryAndTitle(Double salary, String title);
}
