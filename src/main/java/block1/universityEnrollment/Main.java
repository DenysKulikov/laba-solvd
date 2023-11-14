package block1.universityEnrollment;

import com.solvd.laba.block1.universityEnrollment.course.AdmissionRequirements;
import com.solvd.laba.block1.universityEnrollment.course.Course;
import com.solvd.laba.block1.universityEnrollment.enums.Specialization;
import com.solvd.laba.block1.universityEnrollment.enums.Subject;
import com.solvd.laba.block1.universityEnrollment.exceptions.InvalidGPAException;
import com.solvd.laba.block1.universityEnrollment.exceptions.UniversityNotSetException;
import com.solvd.laba.block1.universityEnrollment.persons.Person;
import com.solvd.laba.block1.universityEnrollment.persons.Professor;
import com.solvd.laba.block1.universityEnrollment.persons.Student;
import com.solvd.laba.block1.universityEnrollment.university.Department;
import com.solvd.laba.block1.universityEnrollment.university.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        University myUniversity = new University("My University");

        Department computerScience = new Department("Computer Science");
        Department mathematics = new Department("Mathematics");
        computerScience.addSpecialization(Specialization.SOFTWARE_ENGINEERING);
        mathematics.addSpecialization(Specialization.APPLIED_MATH);

        Professor prof1 = new Professor("Smith", "Ronan", 45, "EMP123");
        Professor prof2 = new Professor("Johnson", "Melbourne", 50, "EMP456");
        prof1.addSubject(Subject.MATH);
        prof1.addSubject(Subject.PROGRAMMING);
        prof2.addSubject(Subject.PROGRAMMING);

        Course programmingCourse = new Course("CS101", "Introduction to Programming", Subject.PROGRAMMING);
        Course math = new Course("MATH101", "Calculus", Subject.MATH);

        University kpi = new University("KPI");
        Department department = new Department("FIOT");
        kpi.addDepartment(department);
        kpi.addDepartment(computerScience);
        kpi.addDepartment(mathematics);

        Student student1 = new Student("Alice", "Lord", 20, "STU001", 60,
                myUniversity, computerScience, Specialization.SOFTWARE_ENGINEERING);
        Student student2 = new Student("Bob", "Iger", 22, "STU002", 93,
                myUniversity, mathematics, Specialization.APPLIED_MATH);

        LOGGER.info("University Name: " + myUniversity.getUniversityName());
        LOGGER.debug("Departments: " + myUniversity.departmentsNumber());

        Person person = new Student("Denys", "Kulikov", 21, "1", 86,
                kpi, mathematics, Specialization.APPLIED_MATH);
        System.out.println("Person description: " + person.getDescription());
        System.out.println(person);
        person.joinCourse(programmingCourse);
        person.joinCourse(math);

        AdmissionRequirements admissionRequirements =
                new AdmissionRequirements(kpi, student1, prof1);
        try {
            admissionRequirements.getCost((Student) person);
        } catch (InvalidGPAException | UniversityNotSetException e) {
            throw new RuntimeException(e);
        }

        try(FileReader fileReader = new FileReader(new File("log-file.log"))) {
            int character;
            StringBuilder fileContent = new StringBuilder();

            while ((character = fileReader.read()) != -1) {
                fileContent.append((char) character);
            }

            String fileContentAsString = fileContent.toString();
            System.out.println(fileContentAsString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(FileReader fileReader = new FileReader(new File("summary-file.log"))) {
            int character;
            StringBuilder fileContent = new StringBuilder();

            while ((character = fileReader.read()) != -1) {
                fileContent.append((char) character);
            }

            String fileContentAsString = fileContent.toString();
            System.out.println(fileContentAsString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
