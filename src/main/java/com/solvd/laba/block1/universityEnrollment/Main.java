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
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
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
        department.addSpecialization(Specialization.APPLIED_MATH);
        department.addSpecialization(Specialization.SOFTWARE_ENGINEERING);
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
            student1.setGradePointAverage(80);
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
        programmingCourse.addStudent(new Student(person.getName(), person.getSurname(), person.getStudentID()));
        person.joinCourse(math);
        math.addStudent(new Student(person.getName(), person.getSurname(), person.getStudentID()));

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
            LOGGER.error(e.getMessage());
        }

        try {
            // Read the entire file into a String using Apache Commons IOUtils
            String fileContent = FileUtils.readFileToString(new File("input.txt"), StandardCharsets.UTF_8);

            // Split the content into words using StringUtils
            String[] words = StringUtils.split(fileContent);

            // Count the occurrences of each word using a HashMap
            Map<String, Long> wordCountMap = Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(),
                    Collectors.counting()));

            // Count the number of unique elements
            long uniqueElementsCount = wordCountMap.values().stream().filter(count -> count == 1).count();
            // Write the result to text_unique_words.txt using FileUtils
            FileUtils.writeStringToFile(new File("text_unique_words.txt"),
                    "Number of unique elements: " + uniqueElementsCount, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        List<String> words = new ArrayList<>(List.of("apple", "banana", "orange", "kiwi", "strawberry"));

        Optional<String> largestString = words.stream()
                .max(Comparator.comparingInt(String::length));

        largestString.ifPresent(value -> LOGGER.trace("Largest word in words: " + value));

        // Runnable
        admissionRequirements.writeToLog(() -> LOGGER.trace("Writing to log file"));

        // Consumer
        programmingCourse.addStudent(student1);
        student1.joinCourse(new Course(programmingCourse.getCourseCode(), programmingCourse.getCourseName(), programmingCourse.getSubject()));
        programmingCourse.addStudent(student2);
        student2.joinCourse(new Course(programmingCourse.getCourseCode(), programmingCourse.getCourseName(), programmingCourse.getSubject()));
        LOGGER.trace("Students before sorting: " + programmingCourse.getStudents());
        programmingCourse.sortStudents(
                (students) -> students.sort(Comparator.comparingDouble(Student::getGradePointAverage).reversed())
        );

        LOGGER.trace("Students after sorting: " + programmingCourse.getStudents());

        // Supplier
        department.getSpecializations().toList().stream()
                .forEach(specialization -> {
                    double totalCost = Arrays.stream(specialization.getSubjects())
                            .mapToDouble(subject -> subject.getCost())
                            .sum();

                    LOGGER.trace("Total cost for " + specialization.name() + ": " + totalCost);
                });

        // Function
        Optional<Specialization> specializationWithPhysic = department.filterSpecializations(
                specializationCustomLinkedList -> new ArrayList<>(specializationCustomLinkedList.toList())
                .stream()
                .filter(specialization -> Arrays.asList(specialization.getSubjects()).contains(Subject.PHYSIC))
                .findFirst()
                .orElse(null));
        LOGGER.trace("Specialization with Physic: " + specializationWithPhysic);

        Optional<Set<Department>> departmentsWithPhysics = kpi.filterSpecializations(
                departments -> departments.stream()
                        .filter(department1 -> department1.getSpecializations().toList().stream()
                                .anyMatch(specialization -> Arrays.asList(specialization.getSubjects()).contains(Subject.PHYSIC)))
                        .collect(Collectors.toSet()));
        departmentsWithPhysics.ifPresent(value -> LOGGER.trace("Departments with Physic: " + value));

        // Predicate
        boolean resultCheckDepartmentPresence = kpi.checkDepartmentPresence(departmentsOpt ->
                departmentsOpt.map(departmentsSet ->
                                departmentsSet.stream()
                                        .anyMatch(department::equals))
                        .orElse(false));
        LOGGER.trace(resultCheckDepartmentPresence);

        // Custom lambdas
        String messageFromProfessor1 = prof1.provideReport(() -> "Hi! I am professor");
        LOGGER.trace(messageFromProfessor1);

        AtomicReference<String> line1 = new AtomicReference<>();
        kpi.summarize(university -> {
            line1.set("University Specialization.SOFTWARE_ENGINEERING" + university.getUniversityName() + " has " + university.getDepartments().size()
                    + " departments");
            return university.getDepartments().size();
        });
        LOGGER.trace(line1.get());

        Optional<Set<Specialization>> result = department.provideSpecialization(
                specializations -> specializations.toList().stream()
                        .filter(specialization -> Arrays.asList(specialization.getSubjects()).contains(Subject.PHYSIC))
                        .collect(Collectors.toSet())
        );
        result.ifPresent(value -> LOGGER.trace("Departments containing Subject.PHYSIC: " + value));

        List<Department> computerScienceDepartments = kpi.getDepartments().stream()
                .filter(department1 -> department1.getDepartmentName().contains("Computer Science"))
                .filter(computerScienceDepartment -> computerScienceDepartment.getSpecializations().toList().stream()
                        .allMatch(specialization -> specialization.getAverageExamScore() == 190))
                .collect(Collectors.toList());
        LOGGER.trace(computerScienceDepartments);

        List<String> uppercasedDepartmentNames = kpi.getDepartments().stream()
                .peek(department1 -> LOGGER.trace("Original Department Name: " + department1.getDepartmentName()))
                .map(department1 -> department1.getDepartmentName().toUpperCase())
                .collect(Collectors.toList());

        LOGGER.trace("Uppercased Department Names: " + uppercasedDepartmentNames);
    }
}

