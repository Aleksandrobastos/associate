package br.com.cisp.go.repository.orm;

public record DepartmentOrm(
        Integer departmentId,
        Integer companyId,
        String name
) {
}
