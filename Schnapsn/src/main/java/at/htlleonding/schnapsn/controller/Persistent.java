package at.htlleonding.schnapsn.controller;

import java.util.List;

public interface Persistent<T> {
    public void save(T entity);

    public void insert(T entity);

    public void delete(int id);

}
