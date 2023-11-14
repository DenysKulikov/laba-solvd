package block1.universityEnrollment.persons;

import com.solvd.laba.block1.universityEnrollment.course.Course;
import com.solvd.laba.block1.universityEnrollment.interfaces.IJoin;
import com.solvd.laba.block1.universityEnrollment.interfaces.IQuit;

import java.util.ArrayList;
import java.util.List;

public abstract class Person implements IQuit, IJoin {
    protected String name;
    protected String surname;
    protected int age;
    protected List<Course> courses = new ArrayList<>();

    public Person(String name, String surname, int age) {
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
