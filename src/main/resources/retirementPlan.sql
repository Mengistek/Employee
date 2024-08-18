INSERT INTO Retirement_Plan ( reference_Number, enrollment_Date, retirement_Date, monthly_Contribution, employee_id)
VALUES ( 'EXU189', '2023-01-17', '2023-09-13', 100.00, 1);

INSERT INTO Retirement_Plan ( reference_Number, enrollment_Date, retirement_Date, monthly_Contribution, employee_id)
VALUES ( 'SMI104', '2023-02-20', '2023-09-21', 850.00, 2);

INSERT INTO Retirement_Plan ( reference_Number, enrollment_Date, retirement_Date, monthly_Contribution, employee_id)
VALUES ( 'SM2307', '2020-08-16', '2023-11-04', 1555.50, 3);

INSERT INTO Retirement_Plan (reference_Number, enrollment_Date, retirement_Date, monthly_Contribution, employee_id)
VALUES ( 'SM4115', '2019-12-01', '2023-06-30', 588.00, 4);


INSERT INTO retirement_plan (reference_number, enrollment_date, retirement_date, monthly_contribution, employee_id)
VALUES ('SM1009', '2021-08-16', '2026-09-20', NULL, 1);

CREATE TABLE retirement_plan (
                                 plan_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 reference_number VARCHAR(255) UNIQUE NOT NULL,
                                 enrollment_date DATE NOT NULL,
                                 retirement_date DATE NOT NULL,
                                 monthly_contribution DOUBLE,
                                 employee_id BIGINT,
                                 FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);