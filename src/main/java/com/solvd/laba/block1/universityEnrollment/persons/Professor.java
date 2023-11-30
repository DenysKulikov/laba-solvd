package com.solvd.laba.block1.universityEnrollment.persons;

import com.solvd.laba.block1.universityEnrollment.course.Course;
import com.solvd.laba.block1.universityEnrollment.enums.AcademicDegree;
import com.solvd.laba.block1.universityEnrollment.enums.Subject;
import com.solvd.laba.block1.universityEnrollment.interfaces.IReport;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Professor extends Person {
    private String employeeID;
    private Set<Subject> specializations = new HashSet<>();
    private AcademicDegree degree;

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

    public String provideReport(IReport report) {
        return report.provideReport();
    }

    public AcademicDegree getDegree() {
        return degree;
    }

    public void setDegree(AcademicDegree degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "employeeID='" + employeeID + '\'' +
                ", specializations=" + specializations +
                ", degree=" + degree +
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
        return Objects.equals(employeeID, professor.employeeID) && Objects.equals(specializations, professor.specializations) && degree == professor.degree;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, specializations, degree);
    }
}
