package irimi.springbootdemo.service;

import java.util.List;

public interface Service<T> {

    T get(int id);

    List<T> getAll();

    void save(T t);

    void delete(int id);
}
