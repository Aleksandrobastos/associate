package br.com.cisp.go.resource.department;

import br.com.cisp.go.repository.DepartmentRepository;
import br.com.cisp.go.repository.orm.DepartmentOrm;
import br.com.cisp.go.resource.adapter.DepartmentAdapter;
import br.com.cisp.go.resource.department.dto.DepartmentRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/department/v1")
public class DepartmentResource {

    private final DepartmentRepository repository;

    public DepartmentResource(DepartmentRepository repository) {
        this.repository = repository;
    }


    @ResponseStatus(CREATED)
    @PostMapping("/save")
    public DepartmentOrm save(@RequestBody @Valid final DepartmentRequest request){
        return repository.save(DepartmentAdapter.cast(request));
    }
}
