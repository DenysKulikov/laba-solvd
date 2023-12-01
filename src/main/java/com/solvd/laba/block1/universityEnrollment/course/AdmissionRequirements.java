package com.solvd.laba.block1.universityEnrollment.course;

import com.solvd.laba.block1.universityEnrollment.exceptions.InvalidGPAException;
import com.solvd.laba.block1.universityEnrollment.exceptions.UniversityNotSetException;
import com.solvd.laba.block1.universityEnrollment.interfaces.ICountCost;
import com.solvd.laba.block1.universityEnrollment.persons.Professor;
import com.solvd.laba.block1.universityEnrollment.persons.Student;
import com.solvd.laba.block1.universityEnrollment.university.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class AdmissionRequirements implements ICountCost {
    private static final Logger LOGGER = LogManager.getLogger(AdmissionRequirements.class);
    private University university;
    private Student student;
    private Professor professor;
    private static final double MIN_VALID_GPA = 50;
    private static final double MAX_VALID_GPA = 100;

    public AdmissionRequirements(University university, Student student, Professor professor) {
        this.university = university;
        this.student = student;
        this.professor = professor;
    }

    @Override
    public final double getCost(Student student) throws InvalidGPAException, UniversityNotSetException {
        double gpa = student.getGradePointAverage();

        if (gpa < MIN_VALID_GPA || gpa > MAX_VALID_GPA) {
            LOGGER.error("Invalid GPA for cost calculation: gpa < MIN_VALID_GPA & gpa > MAX_VALID_GPA");
            throw new InvalidGPAException("Invalid GPA for cost calculation");
        }
        final double baseCost = 10000;

        // Calculate cost based on the difference between the student's GPA and the desired specialization's average
        // exam score
        double gpaCost = 1000 - ((gpa - student.getDesiredSpecialization().getAverageExamScore()) * 100);

        // Calculate cost based on the courses' prices
        List<Course> courses = student.getCourses();
        double courseCost = 0;
        for (Course course : courses) {
            courseCost += course.getCoursePrice();
        }

        // Total cost
        double totalCost = baseCost + gpaCost + courseCost;
        LOGGER.info("The student " + student.getName() + " wants to enter the university "
                + student.getDesiredUniversity().getUniversityName() + " for department "
                + student.getDesiredDepartment().getDepartmentName() + " and desired specialisation "
                + student.getDesiredSpecialization() + ". Professor for his course will be " + professor.getName()
                + ". The cost of studying will be " + totalCost + ".");
        return totalCost;
    }

    public void writeToLog(Runnable runnable) {
        runnable.run();
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "AdmissionRequirements{" +
                "university=" + university +
                ", student=" + student +
                ", professor=" + professor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmissionRequirements that = (AdmissionRequirements) o;
        return Objects.equals(university, that.university) && Objects.equals(student, that.student) && Objects.equals(professor, that.professor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(university, student, professor);
    }
}
