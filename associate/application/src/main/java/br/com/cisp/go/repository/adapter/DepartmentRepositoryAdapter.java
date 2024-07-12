package br.com.cisp.go.repository.adapter;

import br.com.cisp.go.repository.orm.DepartmentMongo;
import br.com.cisp.go.repository.orm.DepartmentOrm;

import java.util.UUID;

public class DepartmentRepositoryAdapter {
    public DepartmentRepositoryAdapter() {
    }

    public static DepartmentMongo cast(final DepartmentOrm orm, final Integer departmentId ) {
        return new DepartmentMongo(
                UUID.randomUUID().toString(),
                departmentId,
                orm.companyId(),
                orm.name()
        );
    }

    public static DepartmentOrm cast(final DepartmentMongo mongo) {
        return new DepartmentOrm(
                mongo.departmentId(),
                mongo.companyId(),
                mongo.name()
        );
    }
}
