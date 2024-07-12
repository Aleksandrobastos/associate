package br.com.cisp.go.resource.segment;

import br.com.cisp.go.repository.SegmentRepository;
import br.com.cisp.go.repository.orm.SegmentOrm;
import br.com.cisp.go.repository.orm.SegmentUpdateOrm;
import br.com.cisp.go.resource.adapter.SegmentAdapter;
import br.com.cisp.go.resource.segment.dto.SegmentRequest;
import br.com.cisp.go.resource.segment.dto.SegmentUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/segment/v1")
public class SegmentResource {

    private final SegmentRepository repository;

    public SegmentResource(SegmentRepository repository) {
        this.repository = repository;
    }

    @ResponseStatus(CREATED)
    @PostMapping("/save")
    public SegmentOrm save(@RequestBody @Valid final SegmentRequest request){
        return repository.save(SegmentAdapter.cast(request));
    }

    @ResponseStatus(OK)
    @GetMapping("/list")
    public List<SegmentOrm> list() {
        return repository.findAll();
    }

    @ResponseStatus(OK)
    @GetMapping("/code/{code}")
    public SegmentOrm findByCode(@PathVariable final Integer code) {
        return repository.findByCode(code);
    }

    @ResponseStatus(OK)
    @PutMapping("/code/{code}")
    public SegmentUpdateOrm update(
            @PathVariable final Integer code,
            @RequestBody @Valid final SegmentUpdateRequest request) {
        return repository.update(code, SegmentAdapter.cast(request));
    }

    @ResponseStatus(OK)
    @DeleteMapping("/code/{code}")
    public void delete(@PathVariable final Integer code) {
        repository.delete(code);
    }
}
