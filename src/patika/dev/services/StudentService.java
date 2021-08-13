package patika.dev.services;

import patika.dev.repository.CrudRepository;
import patika.dev.utils.EntityManagerUtils;
import patika.dev.models.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentService implements CrudRepository<Student> {
    EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

    @Override
    public List<Student> findAll() {
       return em.createQuery("from Student",Student.class).getResultList();
    }

    @Override
    public Student findById(long id) {
        return em.find(Student.class, id);
    }

    @Override
    public void saveToDb(Student student) {
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }

    @Override
    public void deleteFromDb(Student student) {

    }

    @Override
    public void deleteFromDb(long id) {
        em.getTransaction().begin();
        Student student = em.createQuery("from Student s where s.id =:id",Student.class).setParameter("id",id).getSingleResult();;
        em.remove(student);
        em.getTransaction().commit();
    }

    @Override
    public void updateOnDb(Student student, long id) {
    try {
        em.getTransaction().begin();
        Student st = em.find(Student.class,id);
        st.setName(student.getName());
        st.setBirthDate(student.getBirthDate());
        st.setAddress(student.getAddress());
        st.setGender(student.getGender());

        em.merge(st);

    }catch (Exception e){
         em.getTransaction().rollback();
    }finally {
        em.getTransaction().commit();

    }
    }
}
