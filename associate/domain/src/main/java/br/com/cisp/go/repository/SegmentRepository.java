package br.com.cisp.go.repository;

import br.com.cisp.go.repository.orm.SegmentOrm;
import br.com.cisp.go.repository.orm.SegmentUpdateOrm;

import java.util.List;

public interface SegmentRepository {
    SegmentOrm save(SegmentOrm orm);

    List<SegmentOrm> findAll();

    SegmentOrm findByCode(Integer code);

    SegmentUpdateOrm update(Integer code, SegmentUpdateOrm orm);

    void delete(Integer code);
}
