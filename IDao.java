
package ma.projet.dao;

import java.util.List;

public interface IDao<T> {
    boolean create(T t);
    T getById(int id);
    List<T> getAll();
}

