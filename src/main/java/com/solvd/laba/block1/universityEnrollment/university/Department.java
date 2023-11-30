package com.solvd.laba.block1.universityEnrollment.university;

import com.solvd.laba.block1.universityEnrollment.enums.Specialization;
import com.solvd.laba.block1.universityEnrollment.interfaces.IProvide;

import java.util.Objects;

public class Department {
    private String departmentName;
    private CustomLinkedList<Specialization> specializations = new CustomLinkedList<>();

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public CustomLinkedList<Specialization> getSpecializations() {
        return specializations;
    }

    public void addSpecialization(Specialization specialization) {
        specializations.add(specialization);
    }

    public Specialization provideSpecialization(IProvide iProvide) {
        return iProvide.provide();
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
