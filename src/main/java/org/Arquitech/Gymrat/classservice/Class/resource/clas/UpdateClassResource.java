package org.Arquitech.Gymrat.classservice.Class.resource.clas;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClassResource {
    @NotBlank
    @NotNull
    @Min(1)
    private Integer id;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String description;

    @NotBlank
    @NotNull
    private String duration;

    @NotBlank
    @NotNull
    private String capacity;

    @NotBlank
    @NotNull
    private String instructor;

    @NotBlank
    @NotNull
    private String type;

    @NotBlank
    @NotNull
    private String level;

    @NotBlank
    @NotNull
    private String room;
}
