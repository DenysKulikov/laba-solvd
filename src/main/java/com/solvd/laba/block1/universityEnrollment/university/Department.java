package com.solvd.laba.block1.universityEnrollment.university;

import com.solvd.laba.block1.universityEnrollment.enums.Specialization;

import java.util.List;
import java.util.Objects;

public class Department {
    private String departmentName;
    private List<Specialization> specializations = new CustomLinkedList<>();

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void addSpecialization(Specialization specialization) {
        specializations.add(specialization);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", specializations=" + specializations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentName, that.departmentName) && Objects.equals(specializations, that.specializations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentName, specializations);
    }
}
