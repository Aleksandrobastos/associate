package br.com.cisp.go.repository.mongo;

import br.com.cisp.go.repository.orm.DepartmentMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepositoryMongo extends MongoRepository<DepartmentMongo, String> {

    List<DepartmentMongo> findByCompanyId(Integer companyId);
}
