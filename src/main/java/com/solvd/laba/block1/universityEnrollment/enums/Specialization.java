package com.solvd.laba.block1.universityEnrollment.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Specialization {
    SOFTWARE_ENGINEERING(190),
    APPLIED_MATH(180);
    final int averageExamScore;
    private final Logger logger = LogManager.getLogger(Specialization.class);

    {
        logger.trace("Initializing enum " + this.name());
    }

    Specialization(int averageExamScore) {
        this.averageExamScore = averageExamScore;
    }

    public int getAverageExamScore() {
        return averageExamScore;
    }
}
