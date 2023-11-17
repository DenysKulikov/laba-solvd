package com.solvd.laba.block1.universityEnrollment.persons;

import com.solvd.laba.block1.universityEnrollment.course.Course;
import com.solvd.laba.block1.universityEnrollment.exceptions.InvalidAgeException;
import com.solvd.laba.block1.universityEnrollment.interfaces.IJoin;
import com.solvd.laba.block1.universityEnrollment.interfaces.IQuit;

import java.util.ArrayList;
import java.util.List;

public abstract class Person implements IQuit, IJoin {
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

    public abstract int getAge();

    public abstract void setAge(int age) throws InvalidAgeException;

    public List<Course> getCourses() {
        return courses;
    }
}
