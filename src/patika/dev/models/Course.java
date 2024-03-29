package patika.dev.models;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String courseName;
    private String courseCode;
    private int creditScore;

    @ManyToOne
    @JoinColumn(nullable = false,name = "instructor_id")
    private Instructor instructor;
    @ManyToMany(mappedBy = "courseList")
    private List<Student> studentList = new ArrayList<>();

    public Course() {
    }

    public Course(String courseName, String courseCode, int creditScore) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditScore = creditScore;
    }

    public String getCourseName() {

        return courseName;
    }

    public void setCourseName(String courseName) {

        this.courseName = courseName;
    }

    public String getCourseCode() {

        return courseCode;
    }

    public void setCourseCode(String courseCode) {

        this.courseCode = courseCode;
    }

    public int getCreditScore() {

        return creditScore;
    }

    public void setCreditScore(int creditScore) {

        this.creditScore = creditScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode);
    }

    public List<Student> getStudentList() {

        return studentList;
    }

    public void setStudentList(List<Student> studentList) {

        this.studentList = studentList;
    }

    public Instructor getInstructor() {

        return instructor;
    }

    public void setInstructor(Instructor instructor) {

        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", creditScore=" + creditScore +
                '}';
    }
}
