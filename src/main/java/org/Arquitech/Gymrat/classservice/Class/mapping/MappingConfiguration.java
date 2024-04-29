package org.Arquitech.Gymrat.classservice.Class.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("clientMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ClassMapper classMapper(){
        return new ClassMapper();
    }
}