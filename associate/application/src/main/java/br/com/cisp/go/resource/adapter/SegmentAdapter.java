package br.com.cisp.go.resource.adapter;

import br.com.cisp.go.repository.orm.SegmentOrm;
import br.com.cisp.go.repository.orm.SegmentUpdateOrm;
import br.com.cisp.go.resource.segment.dto.SegmentRequest;
import br.com.cisp.go.resource.segment.dto.SegmentUpdateRequest;

public class SegmentAdapter {

    public SegmentAdapter() {
    }

    public static SegmentOrm cast(final SegmentRequest request) {
        return new SegmentOrm(
                request.code(),
                request.name()
        );
    }

    public static SegmentUpdateOrm cast(final SegmentUpdateRequest request) {
        return new SegmentUpdateOrm(
                request.name()
        );
    }
}
