package br.com.cisp.go.repository;


import br.com.cisp.go.repository.orm.DepartmentOrm;

public interface DepartmentRepository {

    DepartmentOrm save(DepartmentOrm orm);
}
