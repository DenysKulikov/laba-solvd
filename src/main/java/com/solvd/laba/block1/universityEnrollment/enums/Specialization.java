package com.solvd.laba.block1.universityEnrollment.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Specialization {
    SOFTWARE_ENGINEERING(190, new Subject[]{Subject.MATH, Subject.PROGRAMMING,
            Subject.UKRAINIAN_LANGUAGE}),
    APPLIED_MATH(180, new Subject[]{Subject.MATH, Subject.PHYSIC,
            Subject.UKRAINIAN_LANGUAGE});
    final int averageExamScore;
    final Subject[] subjects;
    private final Logger logger = LogManager.getLogger(Specialization.class);

    {
        logger.trace("Initializing enum " + this.name());
    }

    Specialization(int averageExamScore, Subject[] subjects) {
        this.averageExamScore = averageExamScore;
        this.subjects = subjects;
    }

    public int getAverageExamScore() {
        return averageExamScore;
    }

    public Subject[] getSubjects() {
        return subjects;
    }
}
