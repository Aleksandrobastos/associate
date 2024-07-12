package br.com.cisp.go.repository;

import br.com.cisp.go.exception.BadRequestException;
import br.com.cisp.go.exception.InternalServiceErrorException;
import br.com.cisp.go.exception.NotFoundException;
import br.com.cisp.go.repository.adapter.AssociateRepositoryAdapter;
import br.com.cisp.go.repository.mongo.AssociateRepositoryWithMongo;
import br.com.cisp.go.repository.orm.AssociateMongo;
import br.com.cisp.go.repository.orm.AssociateOrm;
import br.com.cisp.go.repository.orm.AssociateUpdateOrm;
import br.com.cisp.go.service.SequenceGeneratorService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AssociateRepositoryImpl implements AssociateRepository {

    private final AssociateRepositoryWithMongo repository;
    private final SequenceGeneratorService service;

    public AssociateRepositoryImpl(
            AssociateRepositoryWithMongo repository,
            SequenceGeneratorService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public AssociateOrm save(final AssociateOrm orm) {
        try {
            Integer generatedCode = orm.companyId();
            if (generatedCode == null) {
                generatedCode = (int) service.generateSequence(AssociateMongo.SEQUENCE_NAME);
            }
            AssociateMongo mongo =  AssociateRepositoryAdapter.cast(orm, generatedCode);
            return AssociateRepositoryAdapter.cast(repository.save(mongo));
        } catch (DuplicateKeyException ex) {
            throw new BadRequestException("Code or CNPJ already registered");
        } catch (Exception ex) {
            throw new InternalServiceErrorException("Internal service error occurred while saving AssociateOrm", ex);
        }
    }

    @Override
    public AssociateOrm findByCompanyId(final Integer companyId) {
        try {
            Optional<AssociateMongo> optional = repository.findByCompanyId(companyId);
            if (optional.isEmpty()) {
                throw new NotFoundException("Associate not exist");
            }
            return AssociateRepositoryAdapter.cast(optional.get());
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new InternalServiceErrorException(ex);
        }
    }

    @Override
    public void delete(final Integer companyId) {
        try {
            Optional<AssociateMongo> mongo = repository.findByCompanyId(companyId);
            if (mongo.isEmpty()) {
                throw new NotFoundException("Associate not exist");
            }
            repository.delete(mongo.get());
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new InternalServiceErrorException(ex);
        }
    }

    @Override
    public AssociateUpdateOrm update(final AssociateUpdateOrm orm, final Integer companyId) {
        try {
            Optional<AssociateMongo> mongo = repository.findByCompanyId(companyId);
            if (mongo.isEmpty()) {
                throw new NotFoundException("Associate not exist");
            }
            return AssociateRepositoryAdapter.teste(repository.save(associateMongo(orm, mongo.get())));
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new InternalServiceErrorException(ex);
        }

    }

    private AssociateMongo associateMongo(final AssociateUpdateOrm orm, final AssociateMongo mongo) {
        return new AssociateMongo(
                mongo.id(),
                mongo.companyId(),
                mongo.code(),
                mongo.cnpj(),
                orm.name()
        );
    }
}
