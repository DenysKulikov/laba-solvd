package com.solvd.laba.block1.universityEnrollment.persons;

import com.solvd.laba.block1.universityEnrollment.course.Course;
import com.solvd.laba.block1.universityEnrollment.enums.Specialization;
import com.solvd.laba.block1.universityEnrollment.exceptions.DepartmentNotSetException;
import com.solvd.laba.block1.universityEnrollment.exceptions.InvalidDesiredSpecializationException;
import com.solvd.laba.block1.universityEnrollment.exceptions.UniversityNotSetException;
import com.solvd.laba.block1.universityEnrollment.university.Department;
import com.solvd.laba.block1.universityEnrollment.university.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Student extends Person {
    private static final Logger LOGGER = LogManager.getLogger(Student.class);
    private String studentID;
    private double gradePointAverage;
    private University desiredUniversity;
    private Department desiredDepartment;
    private Specialization desiredSpecialization;

    public Student(String name, String surname, String studentID) {
        super(name, surname);
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public double getGradePointAverage() {
        return gradePointAverage;
    }

    public void setGradePointAverage(double gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
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
        return "Student";
    }

    public University getDesiredUniversity() {
        return desiredUniversity;
    }

    public void setDesiredUniversity(University desiredUniversity) throws UniversityNotSetException {
        if (desiredUniversity == null) {
            LOGGER.error("University not set");
            throw new UniversityNotSetException("University not set");
        }
        this.desiredUniversity = desiredUniversity;
    }

    public Department getDesiredDepartment() {
        return desiredDepartment;
    }

    public void setDesiredDepartment(Department desiredDepartment) throws DepartmentNotSetException {
        if (desiredDepartment == null) {
            LOGGER.error("Department not set");
            throw new DepartmentNotSetException("Department not set");
        }
        this.desiredDepartment = desiredDepartment;
    }

    public Specialization getDesiredSpecialization() {
        return desiredSpecialization;
    }

    public void setDesiredSpecialization(Specialization desiredSpecialization) throws InvalidDesiredSpecializationException {
        if (desiredSpecialization == null) {
            LOGGER.error("Specialization not set");
            throw new InvalidDesiredSpecializationException("Specialization not set");
        }
        this.desiredSpecialization = desiredSpecialization;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", gradePointAverage=" + gradePointAverage +
                ", desiredUniversity=" + desiredUniversity +
                ", desiredDepartment=" + desiredDepartment +
                ", desiredSpecialization=" + desiredSpecialization +
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
        Student student = (Student) o;
        return Double.compare(gradePointAverage, student.gradePointAverage) == 0 &&
                Objects.equals(studentID, student.studentID) &&
                Objects.equals(desiredUniversity, student.desiredUniversity) &&
                Objects.equals(desiredDepartment, student.desiredDepartment) &&
                desiredSpecialization == student.desiredSpecialization;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, gradePointAverage, desiredUniversity, desiredDepartment, desiredSpecialization);
    }
}
