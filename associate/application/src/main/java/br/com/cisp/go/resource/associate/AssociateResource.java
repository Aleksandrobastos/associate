package br.com.cisp.go.resource.associate;

import br.com.cisp.go.repository.AssociateRepository;
import br.com.cisp.go.repository.orm.AssociateOrm;
import br.com.cisp.go.repository.orm.AssociateUpdateOrm;
import br.com.cisp.go.resource.adapter.AssociateAdapter;
import br.com.cisp.go.resource.associate.dto.AssociateRequest;
import br.com.cisp.go.resource.associate.dto.AssociateUpdateRequest;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/associate/v1")
public class AssociateResource {

    private final AssociateRepository repository;

    public AssociateResource(AssociateRepository repository) {
        this.repository = repository;
    }

    @ResponseStatus(OK)
    @PostMapping("/save")
    public AssociateOrm save(@RequestBody AssociateRequest request){
        return repository.save(AssociateAdapter.cast(request));
    }
    @ResponseStatus(OK)
    @GetMapping("/associate/{companyId}")
    public AssociateOrm associate(@PathVariable final Integer companyId) {
        return repository.findByCompanyId(companyId);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/associate/{companyId}")
    public void delete(@PathVariable final Integer companyId) {
        repository.delete(companyId);
    }

    @ResponseStatus(OK)
    @PutMapping("/associate/{companyId}")
    public AssociateUpdateOrm update(
            @PathVariable final Integer companyId, @RequestBody final AssociateUpdateRequest request){
        return repository.update(AssociateAdapter.cast(request), companyId);
    }
}
