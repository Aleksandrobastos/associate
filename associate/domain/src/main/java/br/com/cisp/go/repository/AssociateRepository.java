package br.com.cisp.go.repository;

import br.com.cisp.go.repository.orm.AssociateOrm;
import br.com.cisp.go.repository.orm.AssociateUpdateOrm;

public interface AssociateRepository {

    AssociateOrm save(AssociateOrm orm);

    AssociateOrm findByCompanyId(Integer companyId);

    AssociateUpdateOrm update(AssociateUpdateOrm orm, Integer codeAssociate);

    void delete(Integer codeAssociate);

}
