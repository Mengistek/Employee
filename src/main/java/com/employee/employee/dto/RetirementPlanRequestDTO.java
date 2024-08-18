package com.employee.employee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetirementPlanRequestDTO {
    @NotNull(message = "First Name cannot be null")
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = "Last Name cannot be null ")
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "Yearly salary cannot be null")
    @JsonProperty("yearly_salary")
    private Double yearlySalary;

    @NotNull(message = "Reference number cannot be null")
    @JsonProperty("reference_number")
    private String referenceNumber;

    @JsonProperty("enrollment_date")
    @NotNull(message = "Enrollment date cannot be null")
    private LocalDate enrollmentDate;

    @NotNull(message = "Retirement date cannot be null")
    @JsonProperty("retirement_date")
    private LocalDate retirementDate;

    @NotNull(message = "Monthly Contribution cannot be null")
    @JsonProperty("monthly_contribution")
    private Double monthlyContribution;



}
