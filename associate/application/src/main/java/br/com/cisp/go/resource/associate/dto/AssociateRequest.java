package br.com.cisp.go.resource.associate.dto;

public record AssociateRequest(
        Integer code,
        String cnpj,
        String name
) {
}
