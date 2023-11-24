package com.solvd.laba.block1.universityEnrollment.persons;

import com.solvd.laba.block1.universityEnrollment.course.Course;
import com.solvd.laba.block1.universityEnrollment.exceptions.InvalidAgeException;
import com.solvd.laba.block1.universityEnrollment.interfaces.IJoin;
import com.solvd.laba.block1.universityEnrollment.interfaces.IQuit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class Person implements IQuit, IJoin {
    private static final Logger LOGGER = LogManager.getLogger(Person.class);
    protected String name;
    protected String surname;
    protected int age;
    protected List<Course> courses = new ArrayList<>();

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public abstract String getDescription();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) throws InvalidAgeException {
        if (age < 0) {
            LOGGER.error("Age cannot be negative");
            throw new InvalidAgeException("Age cannot be negative");
        }
        this.age = age;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
