package com.solvd.laba.block1.universityEnrollment.course;

import com.solvd.laba.block1.universityEnrollment.enums.CourseDifficulty;
import com.solvd.laba.block1.universityEnrollment.enums.Subject;
import com.solvd.laba.block1.universityEnrollment.persons.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private String courseCode;
    private String courseName;
    private List<Student> students = new ArrayList<>();
    private final Subject subject;
    private CourseDifficulty courseDifficulty;

    public Course(String courseCode, String courseName, Subject subject) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.subject = subject;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public double getCoursePrice() {
        return subject.getCost();
    }

    public Subject getSubject() {
        return subject;
    }

    public CourseDifficulty getCourseDifficulty() {
        return courseDifficulty;
    }

    public void setCourseDifficulty(CourseDifficulty courseDifficulty) {
        this.courseDifficulty = courseDifficulty;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", students=" + students +
                ", subject=" + subject +
                ", courseDifficulty=" + courseDifficulty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode) && Objects.equals(courseName, course.courseName) && Objects.equals(students, course.students) && subject == course.subject && courseDifficulty == course.courseDifficulty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, courseName, students, subject, courseDifficulty);
    }
}
