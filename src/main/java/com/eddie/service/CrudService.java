package com.eddie.service;

public interface CrudService<T, ID> {
    void add(T t);
    void edit(T t);
    T findById(ID id);
    void delete(T t);
}
