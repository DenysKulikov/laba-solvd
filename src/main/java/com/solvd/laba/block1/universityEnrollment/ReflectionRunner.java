package com.solvd.laba.block1.universityEnrollment;

import com.solvd.laba.block1.universityEnrollment.course.Course;
import com.solvd.laba.block1.universityEnrollment.enums.Subject;
import com.solvd.laba.block1.universityEnrollment.persons.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.*;

public class ReflectionRunner {
    private static final Logger LOGGER = LogManager.getLogger(ReflectionRunner.class);
    
    public static void main(String[] args) throws InstantiationException {
        // Extracting information about fields, constructors, and methods using reflection
        Class<?> studentClass = Student.class;
        Class<?> courseClass = Course.class;

        // Fields
        LOGGER.trace("Fields:");
        for (Field field : studentClass.getDeclaredFields()) {
            LOGGER.trace("Field Name: " + field.getName());
            LOGGER.trace("Field Type: " + field.getType());
            LOGGER.trace("Modifiers: " + Modifier.toString(field.getModifiers()));
            LOGGER.trace("--------");
        }

        // Constructors
        LOGGER.trace("Constructors:");
        for (Constructor<?> constructor : studentClass.getDeclaredConstructors()) {
            LOGGER.trace("Constructor Name: " + constructor.getName());
            LOGGER.trace("Modifiers: " + Modifier.toString(constructor.getModifiers()));
            LOGGER.trace("Parameters:");
            for (Parameter parameter : constructor.getParameters()) {
                LOGGER.trace("Parameter Name: " + parameter.getName());
                LOGGER.trace("Parameter Type: " + parameter.getType());
                LOGGER.trace("--------");
            }
        }

        // Methods
        LOGGER.trace("Methods:");
        for (Method method : studentClass.getDeclaredMethods()) {
            LOGGER.trace("Method Name: " + method.getName());
            LOGGER.trace("Return Type: " + method.getReturnType());
            LOGGER.trace("Modifiers: " + Modifier.toString(method.getModifiers()));
            LOGGER.trace("Parameters:");
            for (Parameter parameter : method.getParameters()) {
                LOGGER.trace("Parameter Name: " + parameter.getName());
                LOGGER.trace("Parameter Type: " + parameter.getType());
                LOGGER.trace("--------");
            }
        }

        // Creating an object using reflection
        Student studentInstance = null;
        Course courseInstance = null;
        try {
            studentInstance = (Student) studentClass.getDeclaredConstructor(String.class, String.class, String.class)
                    .newInstance("John", "Doe", "123");

            courseInstance = (Course) courseClass.getDeclaredConstructor(String.class, String.class, Subject.class)
                    .newInstance("CS101", "Introduction to Programming", Subject.PROGRAMMING);

            // Get the quitCourse method
            Method joinCourse = Student.class.getDeclaredMethod("joinCourse", Course.class);

            // Make the method accessible if it's not public
            joinCourse.setAccessible(true);

            // Call the joinCourse method on the studentInstance
            joinCourse.invoke(studentInstance, courseInstance);

            Method getDescriptionMethod = studentClass.getDeclaredMethod("getDescription");
            String result = (String) getDescriptionMethod.invoke(studentInstance);
            LOGGER.trace("Result of getDescription: " + result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.trace("Created Student instance: " + studentInstance);
        LOGGER.trace("Created Course instance: " + courseInstance);
    }
}
