package org.Arquitech.Gymrat.classservice.Class.domain.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "class") // This is a reserved keyword in SQL
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotNull
    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Integer duration;

    @NotNull
    @Column(nullable = false)
    private Integer capacity;

    @NotBlank
    @NotNull
    @Column(length = 30, nullable = false)
    private String instructor;

    @NotBlank
    @NotNull
    @Column(length = 30, nullable = false)
    private String type;

    @NotBlank
    @NotNull
    @Column(length = 30, nullable = false)
    private String level;

    @NotBlank
    @NotNull
    @Column(length = 30, nullable = false)
    private String room;
}
