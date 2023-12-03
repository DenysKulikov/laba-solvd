package com.solvd.laba.block1.universityEnrollment.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Country {
    UKRAINE(300),
    UNITED_STATES(1000),
    GERMANY(210);
    private final int educationalInstitutionNumber;
    private final Logger logger = LogManager.getLogger(Country.class);

    {
        logger.trace("Initializing enum " + this.name());
    }

    Country(int educationalInstitutionNumber) {
        this.educationalInstitutionNumber = educationalInstitutionNumber;
    }

    public int getEducationalInstitutionNumber() {
        return educationalInstitutionNumber;
    }
}
