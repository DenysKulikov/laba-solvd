package com.solvd.laba.block1.universityEnrollment.interfaces;

import com.solvd.laba.block1.universityEnrollment.university.Department;

@FunctionalInterface
public interface Extensible {
    void addDepartment(Department department);
}
