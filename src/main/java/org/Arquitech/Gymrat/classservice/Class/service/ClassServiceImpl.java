package org.Arquitech.Gymrat.classservice.Class.service;


import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.Arquitech.Gymrat.classservice.Class.domain.model.entity.Class;
import org.Arquitech.Gymrat.classservice.Class.domain.persistence.ClassRepository;
import org.Arquitech.Gymrat.classservice.Class.domain.service.ClassService;
import org.Arquitech.Gymrat.classservice.Class.resource.admin.AdminServiceClient;
import org.Arquitech.Gymrat.classservice.Class.resource.classes.ClassResource;
import org.Arquitech.Gymrat.classservice.Class.resource.client.ClientDto;
import org.Arquitech.Gymrat.classservice.Class.resource.client.ClientServiceClient;
import org.Arquitech.Gymrat.classservice.Class.resource.payment.PaymentDto;
import org.Arquitech.Gymrat.classservice.Shared.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private AdminServiceClient adminServiceClient;

    @Autowired
    private ClientServiceClient clientServiceClient;

    @Autowired
    private Validator validator;

    public ClassServiceImpl(ClassRepository classRepository, Validator validator) {
        this.classRepository = classRepository;
        this.validator = validator;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Class> fetchAll() {
        return classRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Class> fetchById(Integer Id) {
        if (classRepository.existsById(Id)) {
            return classRepository.findById(Id);
        } else {
            throw new CustomException("Class not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Class save(Class aClass) {
        Set<ConstraintViolation<Class>> violations = validator.validate(aClass);
        if (!violations.isEmpty()) {
            throw new CustomException("Error", HttpStatus.NOT_FOUND);
        }
        return classRepository.save(aClass);
    }

    @Override
    public Class update(Class aClass) {
        Set<ConstraintViolation<Class>> violations = validator.validate(aClass);
        if (!violations.isEmpty()) {
            throw new CustomException("Error", HttpStatus.NOT_FOUND);
        }

        return classRepository.findById(aClass.getId()).map(aClassToUpdate -> {
            aClassToUpdate.setName(aClass.getName());
            aClassToUpdate.setDescription(aClass.getDescription());
            aClassToUpdate.setDuration(aClass.getDuration());
            aClassToUpdate.setCapacity(aClass.getCapacity());
            aClassToUpdate.setInstructor(aClass.getInstructor());
            aClassToUpdate.setType(aClass.getType());
            aClassToUpdate.setLevel(aClass.getLevel());
            aClassToUpdate.setRoom(aClass.getRoom());
            return classRepository.save(aClassToUpdate);
        }).orElseThrow(() -> new CustomException("Class not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public boolean deleteById(Integer id) {
        var classToDelete = classRepository.findById(id)
                .orElseThrow(() -> new CustomException("Class not found", HttpStatus.NOT_FOUND));

        classRepository.delete(classToDelete);
        return true;
    }

    @Override
    public ClassResource processClassPayment(Integer clientId, Integer classId, PaymentDto paymentDto) {

        ClientDto clientDto = clientServiceClient.getClientById(clientId);


        Class classe = classRepository.findById(classId)
                .orElseThrow(() -> new CustomException("Class not found", HttpStatus.NOT_FOUND));

        try {

            String stripeCardToken = clientDto.getStripeCardToken();


            PaymentIntent paymentIntent = PaymentIntent.create(
                    new PaymentIntentCreateParams.Builder()
                            .setAmount(paymentDto.getAmount().multiply(new BigDecimal(100)).longValue())
                            .setCurrency("usd")
                            .setCustomer(clientDto.getStripeCustomerId())
                            .setPaymentMethod(stripeCardToken)
                            .build()
            );


            paymentDto.setStripeTransactionId(paymentIntent.getId());
            paymentDto.setPaymentStatus("paid");

        } catch (StripeException e) {

            throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


        adminServiceClient.notifyPayment(paymentDto);


        return convertToDto(classe);
    }

    private ClassResource convertToDto(Class classe) {
        ClassResource dto = new ClassResource();
        dto.setId(classe.getId());
        dto.setName(classe.getName());
        dto.setDescription(classe.getDescription());
        dto.setDuration(classe.getDuration().toString());
        dto.setCapacity(classe.getCapacity().toString());
        dto.setInstructor(classe.getInstructor());
        dto.setType(classe.getType());
        dto.setLevel(classe.getLevel());
        dto.setRoom(classe.getRoom());
        return dto;
    }
}
