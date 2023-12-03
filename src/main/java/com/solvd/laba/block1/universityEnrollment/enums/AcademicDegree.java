package com.solvd.laba.block1.universityEnrollment.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum AcademicDegree {
    DOCTOR(5000),
    DOCENT(2000),
    POSTGRADUATE_STUDENT(1000);
    final double salary;
    private final Logger logger = LogManager.getLogger(AcademicDegree.class);

    {
        logger.trace("Initializing enum " + this.name());
    }

    AcademicDegree(int salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}
