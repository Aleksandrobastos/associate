package br.com.cisp.go.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "department")
@CompoundIndex(def = "{'companyId': 1, 'name': 1}", unique = true)
public record DepartmentMongo(
        @Id
        String id,
        Integer departmentId,
        Integer companyId,
        String name
) {
        public static final String SEQUENCE_NAME = "department_id_sequences";
}
