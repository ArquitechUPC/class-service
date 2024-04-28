package org.Arquitech.Gymrat.classservice.Class.service;


import jakarta.transaction.Transactional;
import org.Arquitech.Gymrat.classservice.Class.domain.model.entity.Class;
import org.Arquitech.Gymrat.classservice.Class.domain.persistence.ClassRepository;
import org.Arquitech.Gymrat.classservice.Class.domain.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private Validator validator;

    public ClassServiceImpl(ClassRepository classRepository, Validator validator) {
        this.classRepository = classRepository;
        this.validator = validator;
    }

    @Override
    public List<Class> fetchAll() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Class> fetchById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Class save(Class aClass) {
        return null;
    }

    @Override
    public Class update(Integer id, Class aClass) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
