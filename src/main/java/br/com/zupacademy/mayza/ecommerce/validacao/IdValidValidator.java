package br.com.zupacademy.mayza.ecommerce.validacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class IdValidValidator implements ConstraintValidator<IdValid, Object> {

    private String domainAttribute;
    private boolean isRequired;
    private Class<?> klass;
    @PersistenceContext
    EntityManager manager;

    @Override
    public void initialize(IdValid constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        isRequired = constraintAnnotation.required();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        if(!isRequired && value == null){
            return true;
        }

        Query query = manager.createQuery("select 1 from " + klass.getName()+ " where " + domainAttribute+"=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();

        return !list.isEmpty();
    }
}
