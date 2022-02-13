package irimi.springbootdemo.dao;

import java.util.List;

public interface DataAccessObject<T> {

    T getById(int id);

    List<T> getAll();

    void save(T t);

    void deleteById(int id);
}
