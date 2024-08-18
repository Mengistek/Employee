package com.employee.employee.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "retierment_plan")
public class RetirementPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    @NotNull
    @Column(nullable = false,unique = true)
    private String referenceNumber;
    @NotNull
    @Column(nullable = false)
    private LocalDate enrollmentDate;
    @NotNull
    @Column(nullable = false)
    private LocalDate retirementDate;

    private Double monthlyContribution;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;

}
