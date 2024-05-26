package org.Arquitech.Gymrat.classservice.Class.resource.clas;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class ClassResource {
    private Integer id;
    private String name;
    private String description;
    private Integer duration;
    private Integer actualCapacity;
    private Integer capacity;
    private String instructor;
    private String type;
    private String level;
    private String room;
}
