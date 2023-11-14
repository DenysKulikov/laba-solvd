package com.solvd.laba.block1.universityEnrollment.university;

import com.solvd.laba.block1.universityEnrollment.interfaces.Extensible;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class University implements Extensible {
    private final String universityName;
    private Set<Department> departments = new HashSet<>();
    public static final String COUNTRY;

    static {
        COUNTRY = "Ukraine";
    }

    public University(String universityName) {
        this.universityName = universityName;
    }


    public Set<Department> getDepartments() {
        return departments;
    }

    public int departmentsNumber() {
        return departments.size();
    }

    public String getUniversityName() {
        return universityName;
    }

    @Override
    public void addDepartment(Department department) {
        departments.add(department);
    }

    @Override
    public String toString() {
        return "University{" +
                "universityName='" + universityName + '\'' +
                ", departments=" + departments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(universityName, that.universityName) && Objects.equals(departments, that.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityName, departments);
    }
}
