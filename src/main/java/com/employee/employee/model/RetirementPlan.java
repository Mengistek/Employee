package com.employee.employee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetirementPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(nullable = false,unique = true)
    private String referenceNumber;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate enrollmentDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate retirementDate;

    private double monthlyContribution;

    @ManyToOne
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;

}
