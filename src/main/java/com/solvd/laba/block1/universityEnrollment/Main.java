package com.solvd.laba.block1.universityEnrollment;

import com.solvd.laba.block1.universityEnrollment.course.AdmissionRequirements;
import com.solvd.laba.block1.universityEnrollment.course.Course;
import com.solvd.laba.block1.universityEnrollment.enums.Specialization;
import com.solvd.laba.block1.universityEnrollment.enums.Subject;
import com.solvd.laba.block1.universityEnrollment.exceptions.*;
import com.solvd.laba.block1.universityEnrollment.persons.Professor;
import com.solvd.laba.block1.universityEnrollment.persons.Student;
import com.solvd.laba.block1.universityEnrollment.university.Department;
import com.solvd.laba.block1.universityEnrollment.university.University;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws DepartmentNotSetException {
        University myUniversity = new University("My University");

        Department computerScience = new Department("Computer Science");
        Department mathematics = new Department("Mathematics");
        computerScience.addSpecialization(Specialization.SOFTWARE_ENGINEERING);
        mathematics.addSpecialization(Specialization.APPLIED_MATH);
        System.out.println(mathematics.getSpecializations());

        Professor prof1 = new Professor("Smith", "Ronan", "EMP123");
        Professor prof2 = new Professor("Johnson", "Melbourne", "EMP456");
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

        Student student1 = new Student("Alice", "Lord", "STU001");
        Student student2 = new Student("Bob", "Iger", "STU002");

        LOGGER.trace("University Name: " + myUniversity.getUniversityName());
        LOGGER.trace("Departments: " + myUniversity.departmentsNumber());

        Student person = new Student("Denys", "Kulikov", "STU003");

        try {
            prof1.setAge(45);
            prof2.setAge(50);
            student1.setAge(21);
            student1.setGradePointAverage(86);
            student1.setDesiredDepartment(computerScience);
            student2.setAge(22);
            student2.setGradePointAverage(93);
            student2.setDesiredDepartment(mathematics);
            person.setAge(20);
            person.setGradePointAverage(92);
            person.setDesiredDepartment(mathematics);
        } catch (InvalidAgeException | DepartmentNotSetException e) {
            LOGGER.error(e.getMessage());
        }

        person.setDesiredDepartment(mathematics);

        try {
            student1.setDesiredSpecialization(Specialization.SOFTWARE_ENGINEERING);
            student2.setDesiredSpecialization(Specialization.APPLIED_MATH);
            person.setDesiredSpecialization(Specialization.APPLIED_MATH);
        } catch (InvalidDesiredSpecializationException e) {
            LOGGER.error(e.getMessage());
        }

        try {
            student1.setDesiredUniversity(myUniversity);
            student2.setDesiredUniversity(myUniversity);
            person.setDesiredUniversity(kpi);
        } catch (UniversityNotSetException e) {
            LOGGER.error(e.getMessage());
        }

        System.out.println("Person description: " + person.getDescription());
        System.out.println(person);
        person.joinCourse(programmingCourse);
        person.joinCourse(math);

        AdmissionRequirements admissionRequirements =
                new AdmissionRequirements(kpi, student1, prof1);
        try {
            admissionRequirements.getCost(person);
        } catch (InvalidGPAException | UniversityNotSetException e) {
            LOGGER.error(e.getMessage());
        }

        try {
            // Read the entire file into a String using Apache Commons FileUtils
            File file = new File("summary-file.log");
            String fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

            // Print the file content using StringUtils
            LOGGER.trace(StringUtils.defaultString(fileContent));
        } catch (IOException e) {
            e.printStackTrace();
            // Consider logging the exception using your logger, e.g., LOGGER.error(e.getMessage());
        }

        List<String> wordsList = new ArrayList<>();

        try {
            // Read the entire file into a String using Apache Commons IOUtils
            String fileContent = FileUtils.readFileToString(new File("input.txt"), StandardCharsets.UTF_8);

            // Split the content into words using StringUtils
            String[] words = StringUtils.split(fileContent);

            // Add words to the ArrayList
            Collections.addAll(wordsList, words);

            // Count the occurrences of each word using a HashMap
            Map<String, Long> wordCountMap = Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            // Count the number of unique elements
            long uniqueElementsCount = wordCountMap.values().stream().filter(count -> count == 1).count();
            LOGGER.info("Number of unique elements: " + uniqueElementsCount);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
