package org.Arquitech.Gymrat.classservice.Class.mapping;

import org.Arquitech.Gymrat.classservice.Class.domain.model.entity.Class;
import org.Arquitech.Gymrat.classservice.Class.resource.clas.ClassResource;
import org.Arquitech.Gymrat.classservice.Class.resource.clas.CreateClassResource;
import org.Arquitech.Gymrat.classservice.Class.resource.clas.UpdateClassResource;
import org.Arquitech.Gymrat.classservice.Shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.Serializable;

public class ClassMapper implements Serializable{

    @Autowired
    EnhancedModelMapper mapper;
    public Class toModel(CreateClassResource resource) {
        return this.mapper.map(resource, Class.class);
    }
    public Class toModel(UpdateClassResource resource) {
        return this.mapper.map(resource, Class.class);
    }
    public ClassResource toResource(Class aClass){
        return this.mapper.map(aClass, ClassResource.class);
    }
}
