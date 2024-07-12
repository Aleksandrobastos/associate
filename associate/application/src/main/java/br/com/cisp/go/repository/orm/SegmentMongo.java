package br.com.cisp.go.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "segment")
public record SegmentMongo(
        @Id
        String id,
        @Indexed(unique = true)
        Integer  code,
        String name
) {
}
