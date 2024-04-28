package org.Arquitech.Gymrat.classservice.Class.mapping;

import org.Arquitech.Gymrat.classservice.Class.domain.model.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.Serializable;

public class ClassMapper {

    @Autowired
    EnhancedModelMapper mapper;
    public Class toModel(ClassDto dto) {
        return mapper.map(dto, Class.class);
    }
    public ClassResource toResource(Class model) {
        return this.mapper.map(model, ClassResource.class);
    }
}
