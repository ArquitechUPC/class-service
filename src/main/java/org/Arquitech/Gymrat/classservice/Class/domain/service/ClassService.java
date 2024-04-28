package org.Arquitech.Gymrat.classservice.Class.domain.service;

import org.Arquitech.Gymrat.classservice.Class.domain.model.entity.Class;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    List<Class> fetchAll();
    Optional<Class> fetchById(Integer id);
    Class save(Class aClass);
    Class update(Integer id, Class aClass);
    void delete(Integer id);
}
