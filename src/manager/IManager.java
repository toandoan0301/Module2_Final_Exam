package manager;

import java.util.List;

public interface IManager<E> {
    void add(E e);
    void edit(int id,E e);
    void delete(int id);
    E findMaxPrice();
    List<E> findAll();
}
