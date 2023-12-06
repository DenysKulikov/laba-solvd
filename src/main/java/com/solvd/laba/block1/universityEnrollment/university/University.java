package com.solvd.laba.block1.universityEnrollment.university;

import com.solvd.laba.block1.universityEnrollment.enums.Country;
import com.solvd.laba.block1.universityEnrollment.interfaces.Extensible;
import com.solvd.laba.block1.universityEnrollment.interfaces.ISummarize;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public final class University extends Thread implements Extensible {
    private static final Logger LOGGER = LogManager.getLogger(University.class);
    private final String universityName;
    private Set<Department> departments = new HashSet<>();
    public static final Country COUNTRY;

    static {
        COUNTRY = Country.UKRAINE;
    }

    public University(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public void addDepartment(Department department) {
        departments.add(department);
    }

    public int summarize(ISummarize<University> iSummarize) {
        return iSummarize.summarize(this);
    }

    public Optional<Set<Department>> filterSpecializations(Function<Set<Department>, Set<Department>> function) {
        return Optional.ofNullable(function.apply(departments));
    }

    public boolean checkDepartmentPresence(Predicate<Optional<Set<Department>>> predicate) {
        return predicate.test(Optional.ofNullable(departments));
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
    public void run() {
        for (int i = 0; i < 5; i++) {
            LOGGER.trace(Thread.currentThread().getId() + " Value " + i);
        }
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
