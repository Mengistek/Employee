package com.employee.employee.Repository;

import com.employee.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("SELECT e FROM Employee e ORDER BY e.lastName ASC")
    List<Employee> findAllByOrderByLastNameAsc();
}
