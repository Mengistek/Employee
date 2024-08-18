package com.employee.employee.Repository;

import com.employee.employee.model.Employee;
import com.employee.employee.model.RetirementPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface RetirementPlanRepository extends JpaRepository<RetirementPlan, Long> {
    @Query("SELECT rp FROM RetirementPlan rp WHERE rp.retirementDate BETWEEN :start AND :end")
    List<RetirementPlan> findByRetirementDateBetween(@Param("start") LocalDate startDate,@Param("end") LocalDate endDate);
    List<RetirementPlan> findByEmployee(Employee employee);
}
