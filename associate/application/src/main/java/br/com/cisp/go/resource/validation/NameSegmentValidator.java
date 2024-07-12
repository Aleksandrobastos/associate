package br.com.cisp.go.resource.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class NameSegmentValidator implements ConstraintValidator<NameSegment, String> {

    private static final Set<String> NOMES_VALIDOS = new HashSet<>();

    static {
        NOMES_VALIDOS.add("Alimentos");
        NOMES_VALIDOS.add("Bebidas");
        NOMES_VALIDOS.add("Prod. de Limpeza");
        NOMES_VALIDOS.add("Papel");
        NOMES_VALIDOS.add("HPC Hig. Pessoal e Cosm");
        NOMES_VALIDOS.add("Util. Domestica");
        NOMES_VALIDOS.add("Comp. Eletroelet.");
        NOMES_VALIDOS.add("Papelaria");
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return NOMES_VALIDOS.stream().anyMatch(validName -> validName.equalsIgnoreCase(name));
    }
}
