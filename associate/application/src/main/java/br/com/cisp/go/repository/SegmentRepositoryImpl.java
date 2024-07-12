package br.com.cisp.go.repository;

import br.com.cisp.go.exception.BadRequestException;
import br.com.cisp.go.exception.InternalServiceErrorException;
import br.com.cisp.go.exception.NotFoundException;
import br.com.cisp.go.repository.adapter.SegmentRepositoryAdapter;
import br.com.cisp.go.repository.mongo.SegmentRepositoryWithMongo;
import br.com.cisp.go.repository.orm.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SegmentRepositoryImpl implements SegmentRepository {

    private final SegmentRepositoryWithMongo repository;

    public SegmentRepositoryImpl(SegmentRepositoryWithMongo repository) {
        this.repository = repository;
    }

    @Override
    public SegmentOrm save(final SegmentOrm orm) {
        try {
            SegmentMongo mongo = repository.save(SegmentRepositoryAdapter.cast(orm));
            return SegmentRepositoryAdapter.cast(mongo);
        } catch (DuplicateKeyException ex) {
            throw new BadRequestException("Code segment already registered");
        } catch (Exception ex) {
            throw new InternalServiceErrorException(ex);
        }
    }

    @Override
    public List<SegmentOrm> findAll() {
        return SegmentRepositoryAdapter.cast(repository.findAll());
    }

    @Override
    public SegmentOrm findByCode(final Integer code) {
        try {
            Optional<SegmentMongo> mongo = repository.findByCode(code);
            if (mongo.isEmpty()) {
                throw new NotFoundException("Segment not exist");
            }
            return SegmentRepositoryAdapter.cast(mongo.get());
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new InternalServiceErrorException(ex);
        }
    }

    @Override
    public SegmentUpdateOrm update(Integer code, SegmentUpdateOrm orm) {
        try {
            Optional<SegmentMongo> mongo = repository.findByCode(code);
            if (mongo.isEmpty()) {
                throw new NotFoundException("Segment not exist");
            }
            return SegmentRepositoryAdapter.castUpdate(repository.save(segmentMongo(orm, mongo.get())));
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new InternalServiceErrorException(ex);
        }


    }

    @Override
    public void delete(final Integer code) {
        try {
            Optional<SegmentMongo> mongo = repository.findByCode(code);
            if (mongo.isEmpty()) {
                throw new NotFoundException("Segment not exist");
            }
            repository.delete(mongo.get());
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new InternalServiceErrorException(ex);
        }
    }

    private SegmentMongo segmentMongo(final SegmentUpdateOrm orm, final SegmentMongo mongo) {
        return new SegmentMongo(
                mongo.id(),
                mongo.code(),
                orm.name()
        );
    }
}
