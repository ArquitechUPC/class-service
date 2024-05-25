package org.Arquitech.Gymrat.classservice.Class.resource.classes;

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
    private String duration;
    private String capacity;
    private String instructor;
    private String type;
    private String level;
    private String room;
}
