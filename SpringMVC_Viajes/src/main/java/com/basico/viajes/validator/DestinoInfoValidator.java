package com.basico.viajes.validator;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.basico.viajes.dao.DestinoDAO;
import com.basico.viajes.entity.Destino;
import com.basico.viajes.model.DestinoInfo;
 
@Component
public class DestinoInfoValidator implements Validator {
 
    @Autowired
    private DestinoDAO destinoDAO;
 
    // This Validator support ProductInfo class.
    public boolean supports(Class<?> clazz) {
        return clazz == DestinoInfo.class;
        }
 
    public void validate(Object target, Errors errors) {
        DestinoInfo destinoInfo = (DestinoInfo) target;
 
        // Check the fields of ProductInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iddestino", "NotEmpty.destinoForm.code");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.destinoForm.nombre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcioncorta", "NotEmpty.destinoForm.descripcioncorta");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha", "NotEmpty.destinoForm.fecha");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numplazas", "NotEmpty.destinoForm.numplazas");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precio", "NotEmpty.destinoForm.precio");
 
        String iddestino = destinoInfo.getIddestino();
        if (iddestino != null && iddestino.length() > 0) {
            if (iddestino.matches("\\s+")) {
                errors.rejectValue("iddestino", "Pattern.destinoForm.iddestino");
                }
            else if(destinoInfo.isNewDestino()) {
                Destino destino = destinoDAO.findDestino(iddestino);
                if (destino != null) {
                    errors.rejectValue("iddestino", "Duplicate.destinoForm.iddestino");
                    }
                }
            }
        }
    }