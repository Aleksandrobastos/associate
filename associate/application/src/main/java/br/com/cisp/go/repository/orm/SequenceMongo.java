package br.com.cisp.go.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequences")
public record SequenceMongo(
        @Id
        String id,
        long seq
) {
}
