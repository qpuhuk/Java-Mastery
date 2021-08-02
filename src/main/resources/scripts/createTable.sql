DROP TABLE IF EXISTS employees;
CREATE TABLE employees
(
    employee_id   bigserial PRIMARY KEY,
    first_name    varchar(20) NOT NULL,
    last_name     varchar(20) NOT NULL,
    department_id INT         NOT NULL,
    job_title     varchar(40) NOT NULL,
    gender        varchar(6)  NOT NULL,
    date_of_birth timestamp   NOT NULL
);
