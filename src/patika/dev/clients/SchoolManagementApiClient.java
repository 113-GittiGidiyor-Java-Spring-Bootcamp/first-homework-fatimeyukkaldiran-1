package patika.dev.clients;

import patika.dev.models.*;
import patika.dev.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import patika.dev.api.controllers.StudentController;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SchoolManagementApiClient {
   public static StudentController studentController = new StudentController();
    public static void main(String[] args) {


         // saveData();

        //addStudent(studentController)
       // studentController.deleteStudent(5);
        listAllStudents();

       Student students = new Student("Selim Guc","Istanbul/Karakoy", LocalDate.of(1998, Month.AUGUST,1),"Male");

       System.out.println("----------after update---------------------");
      studentController.updateStudent(students,2);
      listAllStudents();


    }

    private static void addStudent(StudentController studentController) {
        Student student = new Student("Selen Heral","Mersin/Toroslar",LocalDate.of(2003,Month.APRIL,10),"Female");
        studentController.saveStudent(student);
    }

    private static void listAllStudents() {
        List<Student> returnedList = studentController.findAllStudent();
        System.out.println(returnedList.size());
        for (Student s : returnedList) {
            System.out.println(s);

        }
    }

    private static void saveData() {
        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

      Student student1 = new Student("Ece Guler","Istanbul/Pendik", LocalDate.of(1998, Month.AUGUST,1),"Female");
      Student student2 = new Student("Kenan Dere","Antalya/Manavgat", LocalDate.of(1995, Month.JANUARY,18),"Male");
      Student student3 = new Student("Gaye Aksu","Mugla/Datca", LocalDate.of(2000, Month.SEPTEMBER,12),"Female");

      Instructor pInstructor1 = new PermanentInstructor("Meltem Alp","Ankara/Çankaya","5849756423",10500.0);
      Instructor pInstructor2 = new PermanentInstructor("Engin Deniz","Izmir/Kas","5245689751",8952.0);
      Instructor vResearcher1 = new VisitingResearcher("Hale Acar","Bursa/Golyazi","536145612",4532.0);
      Instructor vResearcher2 = new VisitingResearcher("Selim Seyven","Istanbul/Kadikoy","548975641",3546.0);

      Course course1 = new Course("Math","MTH",8);
      Course course2 = new Course("Chemistry","CMY",6);
      Course course3 = new Course("History","HTY",5);

        course1.getStudentList().addAll(Arrays.asList(student1, student2)); //for a course, lots of students
        course2.getStudentList().addAll(Arrays.asList(student2, student3));
        course3.getStudentList().addAll(Arrays.asList(student1, student2, student3));

        course1.setInstructor(vResearcher1); //for a course only one instructor
        course2.setInstructor(pInstructor1);
        course3.setInstructor(pInstructor1);

        pInstructor1.getCourseList().addAll(Arrays.asList(course2, course3)); //for an instructor many courses
        vResearcher1.getCourseList().add(course1);

        student1.getCourseList().addAll(Arrays.asList(course1, course3)); //for a student, lots of courses
        student2.getCourseList().addAll(Arrays.asList(course1, course2, course3));
        student3.getCourseList().addAll(Arrays.asList(course2, course3));

        try {
            em.getTransaction().begin();

            em.persist(pInstructor1);
            em.persist(vResearcher1);

            em.persist(course1);
            em.persist(course2);
            em.persist(course3);


            em.persist(student1);
            em.persist(student2);
            em.persist(student3);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }


    }
}
