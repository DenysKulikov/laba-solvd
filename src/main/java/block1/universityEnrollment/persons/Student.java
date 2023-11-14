package block1.universityEnrollment.persons;

import com.solvd.laba.block1.universityEnrollment.course.Course;
import com.solvd.laba.block1.universityEnrollment.enums.Specialization;
import com.solvd.laba.block1.universityEnrollment.university.Department;
import com.solvd.laba.block1.universityEnrollment.university.University;

import java.util.Objects;

public class Student extends Person {
    private String studentID;
    private double gradePointAverage;
    private University desiredUniversity;
    private Department desiredDepartment;
    private Specialization desiredSpecialization;

    public Student(String name, String surname, int age, String studentID, double gradePointAverage,
                   University desiredUniversity, Department desiredDepartment, Specialization desiredSpecialization) {
        super(name, surname, age);
        this.studentID = studentID;
        this.gradePointAverage = gradePointAverage;
        this.desiredUniversity = desiredUniversity;
        this.desiredDepartment = desiredDepartment;
        this.desiredSpecialization = desiredSpecialization;
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

    public void setUniversity(University university) {
        this.desiredUniversity = university;
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

    public void setDesiredUniversity(University desiredUniversity) {
        this.desiredUniversity = desiredUniversity;
    }

    public Department getDesiredDepartment() {
        return desiredDepartment;
    }

    public void setDesiredDepartment(Department desiredDepartment) {
        this.desiredDepartment = desiredDepartment;
    }

    public Specialization getDesiredSpecialization() {
        return desiredSpecialization;
    }

    public void setDesiredSpecialization(Specialization desiredSpecialization) {
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
