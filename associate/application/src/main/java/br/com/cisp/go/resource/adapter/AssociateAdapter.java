package br.com.cisp.go.resource.adapter;

import br.com.cisp.go.repository.orm.AssociateOrm;
import br.com.cisp.go.repository.orm.AssociateUpdateOrm;
import br.com.cisp.go.resource.associate.dto.AssociateRequest;
import br.com.cisp.go.resource.associate.dto.AssociateUpdateRequest;

public class AssociateAdapter {
    private AssociateAdapter(){
    }

    public static AssociateOrm cast(AssociateRequest request) {
        return new AssociateOrm(
                null,
                request.code(),
                request.cnpj(),
                request.name()
        );
    }

    public static AssociateUpdateOrm cast(AssociateUpdateRequest resquest) {
        return new AssociateUpdateOrm(
                resquest.name()
        );
    }
}
