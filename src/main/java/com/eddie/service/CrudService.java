package com.eddie.service;

import com.eddie.exception.GuildSystemException;

import javax.validation.ConstraintViolationException;

public interface CrudService<T, ID> {

    T add(T t) throws GuildSystemException, ConstraintViolationException;

    T edit(T t) throws GuildSystemException, ConstraintViolationException;

    T findById(ID id);

    void delete(T t);
}
