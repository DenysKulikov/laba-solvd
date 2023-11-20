package com.solvd.laba.block1.universityEnrollment.persons;

import com.solvd.laba.block1.universityEnrollment.course.Course;
import com.solvd.laba.block1.universityEnrollment.enums.Subject;
import com.solvd.laba.block1.universityEnrollment.exceptions.InvalidAgeException;
import com.solvd.laba.block1.universityEnrollment.interfaces.IReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Professor extends Person implements IReport {
    private static final Logger LOGGER = LogManager.getLogger(Professor.class);
    private String employeeID;
    private Set<Subject> specializations = new HashSet<>();

    public Professor(String name, String surname, String employeeID) {
        super(name, surname);
        this.employeeID = employeeID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Set<Subject> getSubjects() {
        return specializations;
    }

    public void addSubject(Subject specialization) {
        specializations.add(specialization);
    }

    @Override
    public void quitCourse(Course course) {
        courses.remove(course);
    }

    @Override
    public void joinCourse(Course course) {
        courses.add(course);
    }

    @Override
    public String getDescription() {
        return "Professor";
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) throws InvalidAgeException {
        if (age < 0) {
            LOGGER.error("Age cannot be negative");
            throw new InvalidAgeException("Age cannot be negative");
        }
        this.age = age;
    }

    @Override
    public String provideReport(String string) {
        return string;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "employeeID='" + employeeID + '\'' +
                ", specializations=" + specializations +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", courses=" + courses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(employeeID, professor.employeeID) && Objects.equals(specializations, professor.specializations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, specializations);
    }
}
