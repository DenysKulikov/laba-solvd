package com.solvd.laba.block1.universityEnrollment.interfaces;

import com.solvd.laba.block1.universityEnrollment.exceptions.InvalidGPAException;
import com.solvd.laba.block1.universityEnrollment.exceptions.UniversityNotSetException;
import com.solvd.laba.block1.universityEnrollment.persons.Student;

public interface ICountCost {
    double getCost(Student student) throws InvalidGPAException, UniversityNotSetException;
}
