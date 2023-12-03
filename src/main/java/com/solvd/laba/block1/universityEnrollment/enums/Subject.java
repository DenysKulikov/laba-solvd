package com.solvd.laba.block1.universityEnrollment.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Subject {
    MATH(4000),
    PHYSIC(3900),
    PROGRAMMING(4000),
    UKRAINIAN_LANGUAGE(3800);
    private final double subjectCost;
    private final Logger logger = LogManager.getLogger(Subject.class);

    {
        logger.trace("Initializing enum " + this.name());
    }

    Subject(double subjectCost) {
        this.subjectCost = subjectCost;
    }

    public double getCost() {
        return subjectCost;
    }
}
