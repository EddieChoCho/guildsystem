package com.eddie.service.impl;

import com.eddie.exception.GuildSystemException;
import com.eddie.service.CrudService;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by EddieChoCho on 2017/11/7.
 */
public abstract class CrudServiceImpl<T, ID extends Serializable> implements CrudService<T, ID> {

    private JpaRepository<T, ID> jpaRepository;

    public CrudServiceImpl(JpaRepository jpaRepository){
        this.jpaRepository = jpaRepository;
    }

    @Override
    public T add(T object) throws GuildSystemException, ConstraintViolationException {
        validate(object);
        return jpaRepository.save(object);
    }

    @Override
    public T edit(T object) throws GuildSystemException, ConstraintViolationException {
        validate(object);
        return jpaRepository.save(object);
    }

    private void validate(T object) throws ConstraintViolationException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if(violations.size() > 0)
            throw new ConstraintViolationException(violations);
    }


}
