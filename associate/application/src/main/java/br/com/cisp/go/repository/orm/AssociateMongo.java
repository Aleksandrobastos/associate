package br.com.cisp.go.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "associate")
public record AssociateMongo(

        @Id
        String id,
        Integer companyId,
        @Indexed(unique = true)
        Integer code,
        @Indexed(unique = true)
        String cnpj,
        String name
) {
        public static final String SEQUENCE_NAME = "company_id_sequences";
}
