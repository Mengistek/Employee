package com.employee.employee.Controller;

import com.employee.employee.Service.EmployeeService;
import com.employee.employee.model.Employee;
import com.employee.employee.model.RetirementPlan;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@NoArgsConstructor
@RequestMapping("/ipa/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addRetirementPlan(@RequestBody Map<String, Object> payload){
        String firstName = (String) payload.get("firstName");
        String lastName = (String) payload.get("lastName");
        Double yearlySalary = (Double) payload.get("yearlySalary");

        String referenceNumber = (String) payload.get("referenceNumber");
        LocalDate enrollmentDate = LocalDate.parse((String) payload.get("enrollmentDate"));
        LocalDate retirementDate = LocalDate.parse((String) payload.get("retirementDate"));
        Double monthlyContribution = payload.get("monthlyContribution") != null ? (Double) payload.get("monthlyContribution") : null;


        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setYearlySalary(yearlySalary);

        RetirementPlan retirementPlan = new RetirementPlan();
        retirementPlan.setReferenceNumber(referenceNumber);
        retirementPlan.setEnrollmentDate(enrollmentDate);
        retirementPlan.setRetirementDate(retirementDate);
        retirementPlan.setMonthlyContribution(monthlyContribution);

        employeeService.addRetirementPlan(employee,retirementPlan);

        return ResponseEntity.ok("Retirement plan added successfully");
    }

}
