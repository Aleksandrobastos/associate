package br.com.cisp.go.repository.mongo;

import br.com.cisp.go.repository.orm.AssociateMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociateRepositoryWithMongo extends MongoRepository<AssociateMongo, String> {

    Optional<AssociateMongo> findByCompanyId(Integer companyId);
}
