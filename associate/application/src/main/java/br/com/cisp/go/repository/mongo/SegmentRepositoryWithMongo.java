package br.com.cisp.go.repository.mongo;

import br.com.cisp.go.repository.orm.SegmentMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SegmentRepositoryWithMongo extends MongoRepository<SegmentMongo, String> {

    Optional<SegmentMongo> findByCode(Integer code);

}
