package br.com.cisp.go.repository.adapter;

import br.com.cisp.go.repository.orm.AssociateMongo;
import br.com.cisp.go.repository.orm.AssociateOrm;
import br.com.cisp.go.repository.orm.AssociateUpdateOrm;
import io.swagger.v3.oas.models.security.SecurityScheme;

import java.util.UUID;

public class AssociateRepositoryAdapter {
    private AssociateRepositoryAdapter() {
    }

    public static AssociateMongo cast(final AssociateOrm orm, final Integer companyId) {
        return new AssociateMongo(
                UUID.randomUUID().toString(),
                companyId,
                orm.code(),
                orm.cnpj(),
                orm.name()
        );
    }

    public static AssociateOrm cast(final AssociateMongo mongo) {
        return new AssociateOrm(
                mongo.companyId(),
                mongo.code(),
                mongo.cnpj(),
                mongo.name()
        );
    }

    public static AssociateUpdateOrm teste(final AssociateMongo mongo) {
        return new AssociateUpdateOrm(
                mongo.name()
        );
    }
}
