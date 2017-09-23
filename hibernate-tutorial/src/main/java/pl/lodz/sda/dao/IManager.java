package pl.lodz.sda.dao;

import java.util.List;

public interface IManager<T> {

    public T save(T entity);

    public T update(T entity);

    public T find(Long id);

    public List<T> findAll();

    public void deleteAll();

    public T delete(long id);
}
