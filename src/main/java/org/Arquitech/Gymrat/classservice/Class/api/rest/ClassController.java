package org.Arquitech.Gymrat.classservice.Class.api.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.Arquitech.Gymrat.classservice.Class.domain.model.entity.Class;
import org.Arquitech.Gymrat.classservice.Class.domain.service.ClassService;
import org.Arquitech.Gymrat.classservice.Class.mapping.ClassMapper;
import org.Arquitech.Gymrat.classservice.Class.resource.classes.CreateClassResource;
import org.Arquitech.Gymrat.classservice.Class.resource.classes.ClassResource;
import org.Arquitech.Gymrat.classservice.Class.resource.classes.UpdateClassResource;
import org.Arquitech.Gymrat.classservice.Class.resource.payment.PaymentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Class", description = "Create, Read, Update and Delete Classes")
@RestController
@RequestMapping("api/v1/classes")
@AllArgsConstructor
public class ClassController {
    private final ClassService classService;
    private final ClassMapper mapper;

    @Operation(summary = "Get all registered classes", responses = {
            @ApiResponse(description = "Successfully fetched all classes",
            responseCode = "201",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ClassResource.class)))
    })

    @GetMapping
    public List<Class> fetchAll() {return classService.fetchAll();}

    @Operation(summary = "Get a class by id", responses = {
            @ApiResponse(description = "Successfully fetched plan by id",
            responseCode = "201",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ClassResource.class)))
    })

    @GetMapping("/{id}")
    public ClassResource fetchById(@PathVariable Integer id) {
        return this.mapper.toResource(classService.fetchById(id).get());
    }

    @Operation(summary = "Save a class", responses = {
            @ApiResponse(description = "Class successfully created",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClassResource.class)))
    })

    @PostMapping
    public ClassResource save(@RequestBody CreateClassResource resource) {
        return this.mapper.toResource(classService.save(this.mapper.toModel(resource)));
    }

    @Operation(summary = "Update a class by  id", responses = {
            @ApiResponse(description = "Class successfully updated",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClassResource.class)))
    })

    @PutMapping("/{id}")
    public ResponseEntity<ClassResource> update(@PathVariable Integer id, @RequestBody UpdateClassResource resource) {
        if (id.equals(resource.getId())) {
            ClassResource classResource = mapper.toResource(
                    classService.update(mapper.toModel(resource)));
            return new ResponseEntity<>(classResource, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Delete a class by id", responses = {
            @ApiResponse(description = "Class successfully deleted by id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClassResource.class)))
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (classService.deleteById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{classId}/payment") // Endpoint para procesar el pago de una clase
    public ResponseEntity<ClassResource> processClassPayment(@PathVariable Integer classId, @RequestBody PaymentDto paymentDto) {

        Integer clientId = paymentDto.getClientId();


        ClassResource classResource = classService.processClassPayment(clientId, classId, paymentDto);

        return ResponseEntity.ok(classResource);
    }

}
