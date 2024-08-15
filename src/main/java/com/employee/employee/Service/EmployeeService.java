package com.employee.employee.Service;

import com.employee.employee.Exception.ResourceNotFoundException;
import com.employee.employee.Repository.EmployeeRepository;
import com.employee.employee.Repository.RetirementPlanRepository;
import com.employee.employee.model.Employee;
import com.employee.employee.model.RetirementPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
public class EmployeeService implements EmpServiceImp {
    @Autowired
    private EmployeeRepository employeeRepository;

    private  RetirementPlanRepository retirementPlanRepository;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not Found By Id: "+ employeeId));
    }

    @Override
    public List<RetirementPlan> getMonthlyUpcomingRetirees() {
        LocalDate now = LocalDate.now();
        LocalDate startOfNextMonth = now.withDayOfMonth(1).plusMonths(1);
        LocalDate endOfNextMonth = now.withDayOfMonth(1).plusMonths(2).minusDays(1);

        return retirementPlanRepository.findByRetirementDateBetween(startOfNextMonth,endOfNextMonth);
    }

    @Override
    public void addRetirementPlan(Employee employee, RetirementPlan retirementPlan) {
        retirementPlan.setEmployee(employee);
        retirementPlanRepository.save(retirementPlan);
    }
}
