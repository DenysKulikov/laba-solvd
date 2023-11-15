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
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private static final Logger LOGGER = LogManager.getLogger(Person.class);
    protected String name;
    protected String surname;
    protected int age;
    protected List<Course> courses = new ArrayList<>();

    public Person(String name, String surname, int age) {
        if (age < 0) {
            LOGGER.error("Age cannot be negative");
            throw new InvalidAgeException("Age cannot be negative");
        }
        this.name = name;
        this.surname = surname;
        this.age = age;
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

    public void setAge(int age) {
        this.age = age;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
