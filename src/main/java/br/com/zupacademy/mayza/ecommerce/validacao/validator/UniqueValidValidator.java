package br.com.zupacademy.mayza.ecommerce.validacao.validator;

import br.com.zupacademy.mayza.ecommerce.validacao.validator.UniqueValid;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Locale;

public class UniqueValidValidator implements ConstraintValidator<UniqueValid, Object> {

    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    EntityManager manager;

    @Override
    public void initialize(UniqueValid constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        Query query = manager.createQuery("select 1 from " + klass.getName() + " where " +
                domainAttribute.toLowerCase(Locale.ROOT).trim() +"=:value");
        query.setParameter("value", value.toString().toLowerCase(Locale.ROOT).trim());
        List<?> list = query.getResultList();

        return list.isEmpty();

    }
}
