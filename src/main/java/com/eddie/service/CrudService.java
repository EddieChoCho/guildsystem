package com.eddie.service;

public interface CrudService<T, ID> {

    T add(T t);

    T edit(T t);

    T findById(ID id);

    void delete(T t);
}
