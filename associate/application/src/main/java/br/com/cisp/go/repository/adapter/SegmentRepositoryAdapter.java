package br.com.cisp.go.repository.adapter;

import br.com.cisp.go.repository.orm.*;

import java.util.List;
import java.util.UUID;

public class SegmentRepositoryAdapter {

    public SegmentRepositoryAdapter() {
    }

    public static SegmentMongo cast(final SegmentOrm orm) {
        return new SegmentMongo(
                UUID.randomUUID().toString(),
                orm.code(),
                orm.name()
        );
    }

    public static SegmentOrm cast(final SegmentMongo mongo) {
        return new SegmentOrm(
                mongo.code(),
                mongo.name()
        );
    }

    public static List<SegmentOrm> cast(List<SegmentMongo> segments) {
        return segments.stream()
                .map(v -> new SegmentOrm(v.code(), v.name()))
                .toList();
    }

    public static SegmentUpdateOrm castUpdate(SegmentMongo mongo) {
        return new SegmentUpdateOrm(
                mongo.name()
        );
    }
}
