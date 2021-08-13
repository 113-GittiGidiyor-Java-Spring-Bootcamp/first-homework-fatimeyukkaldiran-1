package patika.dev.repository;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll();
    T findById(long id);
    void saveToDb(T object);
    void deleteFromDb(T object);
    void deleteFromDb(long id);
    void updateOnDb(T object, long id);
}
