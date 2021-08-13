package patika.dev.api.controllers;

import patika.dev.services.StudentService;
import patika.dev.models.Student;

import java.util.List;

public class StudentController {
   private StudentService studentService = new StudentService();

   public Student findStudent(long id){
       return studentService.findById(id);
   }
   public List<Student> findAllStudent(){
       return studentService.findAll();
   }
   public void saveStudent(Student student){
        studentService.saveToDb(student);
   }
   public void deleteStudent(int id){
       studentService.deleteFromDb(id);
   }
   public void updateStudent(Student student, long id){

       studentService.updateOnDb(student,id);
   }
}
