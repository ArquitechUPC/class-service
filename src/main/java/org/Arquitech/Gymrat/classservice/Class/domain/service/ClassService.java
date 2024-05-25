package org.Arquitech.Gymrat.classservice.Class.domain.service;

import org.Arquitech.Gymrat.classservice.Class.domain.model.entity.Class;
import org.Arquitech.Gymrat.classservice.Class.resource.classes.ClassResource;
import org.Arquitech.Gymrat.classservice.Class.resource.payment.PaymentDto;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    List<Class> fetchAll();
    Optional<Class> fetchById(Integer Id);
    Class save(Class aClass);
    Class update(Class aClass);
    boolean deleteById(Integer id);
    ClassResource processClassPayment(Integer clientId, Integer classId, PaymentDto paymentDto);
}
