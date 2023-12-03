package com.solvd.laba.block1.universityEnrollment.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CourseDifficulty {
    ADVANCED(95),
    MEDIUM(85),
    ORDINARY(60);
    private double admissionGradePointAverage;
    private final Logger logger = LogManager.getLogger(CourseDifficulty.class);

    {
        logger.trace("Initializing enum " + this.name());
    }

    CourseDifficulty(double admissionGradePointAverage) {
        this.admissionGradePointAverage = admissionGradePointAverage;
    }

    public double getAdmissionGradePointAverage() {
        return admissionGradePointAverage;
    }
}
