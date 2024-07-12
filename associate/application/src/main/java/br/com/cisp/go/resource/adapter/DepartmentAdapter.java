package br.com.cisp.go.resource.adapter;


import br.com.cisp.go.repository.orm.DepartmentOrm;
import br.com.cisp.go.resource.department.dto.DepartmentRequest;

public class DepartmentAdapter {
    public DepartmentAdapter() {
    }

    public static DepartmentOrm cast(DepartmentRequest request) {
        return new DepartmentOrm(
                null,
                request.companyId(),
                request.name().trim()
        );
    }
}
