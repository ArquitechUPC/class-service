package org.Arquitech.Gymrat.classservice.Class.domain.service;

import org.Arquitech.Gymrat.classservice.Class.domain.model.entity.Class;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    List<Class> fetchAll();
    Optional<Class> fetchById(Integer Id);
    Class save(Class aClass);
    Class update(Class aClass);
    void delete(Integer id);
}
