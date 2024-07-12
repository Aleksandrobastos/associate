package br.com.cisp.go.repository.orm;

public record AssociateOrm(
        Integer companyId,
        Integer code,
        String cnpj,
        String name
) {
}
