package com.basico.viajes.validator;
 
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.basico.viajes.model.CompradorInfo;
 
// @Component: As a Bean.
@Component
public class CompradorInfoValidator implements Validator {
 
    private EmailValidator mailValidator = EmailValidator.getInstance();
 
    // This Validator support CustomerInfo class.
    public boolean supports(Class<?> clazz) {
        return clazz == CompradorInfo.class;
        }
 
    public void validate(Object target, Errors errors) {
        CompradorInfo compradorInfo = (CompradorInfo) target;
 
        // Check the fields of CustomerInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombrecomprador", "NotEmpty.compradorForm.nombrecomprador");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mailcomprador", "NotEmpty.compradorForm.mailcomprador");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccioncomprador", "NotEmpty.compradorForm.direccioncomprador");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefonocomprador", "NotEmpty.compradorForm.telefonocomprador");
        
//        if (!mailValidator.isValid(compradorInfo.getDireccioncomprador())) {
//            errors.rejectValue("mailcomprador", "Pattern.compradorForm.mailcomprador");
//            }
        }
    }