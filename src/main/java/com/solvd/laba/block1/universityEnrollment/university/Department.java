package com.solvd.laba.block1.universityEnrollment.university;

import com.solvd.laba.block1.universityEnrollment.enums.Specialization;
import com.solvd.laba.block1.universityEnrollment.interfaces.IProvide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class Department implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Department.class);
    private String departmentName;
    private CustomLinkedList<Specialization> specializations = new CustomLinkedList<>();

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Optional<Set<Specialization>> provideSpecialization(IProvide<CustomLinkedList<Specialization>, Set<Specialization>> iProvide) {
        return Optional.ofNullable(iProvide.provide(specializations));
    }

    public Optional<Specialization> filterSpecializations(Function<CustomLinkedList<Specialization>,
            Specialization> function) {
        return Optional.ofNullable(function.apply(specializations));
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

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            LOGGER.trace(Thread.currentThread().getId() + " Value " + i);
        }
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", specializations=" + specializations.toList() +
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
