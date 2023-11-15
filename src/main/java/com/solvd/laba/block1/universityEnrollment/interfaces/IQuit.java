package com.solvd.laba.block1.universityEnrollment.interfaces;

import com.solvd.laba.block1.universityEnrollment.course.Course;
import com.solvd.laba.block1.universityEnrollment.exceptions.CourseNotSetException;

@FunctionalInterface
public interface IQuit {
    void quitCourse(Course course) throws CourseNotSetException;
}
