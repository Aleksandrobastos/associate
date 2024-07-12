package br.com.cisp.go.repository;

import br.com.cisp.go.exception.BadRequestException;
import br.com.cisp.go.exception.InternalServiceErrorException;
import br.com.cisp.go.exception.NotFoundException;
import br.com.cisp.go.repository.adapter.DepartmentRepositoryAdapter;
import br.com.cisp.go.repository.mongo.DepartmentRepositoryMongo;
import br.com.cisp.go.repository.orm.AssociateMongo;
import br.com.cisp.go.repository.orm.AssociateOrm;
import br.com.cisp.go.repository.orm.DepartmentMongo;
import br.com.cisp.go.repository.orm.DepartmentOrm;
import br.com.cisp.go.service.SequenceGeneratorService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRespositoryImpl implements DepartmentRepository {

    private final DepartmentRepositoryMongo repository;
    private final AssociateRepository associateRepository;
    private final SequenceGeneratorService service;

    public DepartmentRespositoryImpl(
            DepartmentRepositoryMongo repository,
            AssociateRepository associateRepository, SequenceGeneratorService service) {
        this.repository = repository;
        this.associateRepository = associateRepository;
        this.service = service;
    }


    @Override
    public DepartmentOrm save(DepartmentOrm orm) {
        try {
            Integer generatedCode = orm.departmentId();
            if (generatedCode == null) {
                generatedCode = (int) service.generateSequence(DepartmentMongo.SEQUENCE_NAME);
            }
            AssociateOrm associateOrm = associateRepository.findByCompanyId(orm.companyId());
            DepartmentMongo mongo = DepartmentRepositoryAdapter.cast(orm, generatedCode);
            return DepartmentRepositoryAdapter.cast(repository.save(mongo));
        } catch (DuplicateKeyException ex) {
            throw new BadRequestException("Department already registered for associate");
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new InternalServiceErrorException(ex);
        }
    }
}
