package br.com.cisp.go.resource.segment.dto;

import br.com.cisp.go.resource.validation.NameSegment;
import jakarta.validation.constraints.NotEmpty;

public record SegmentUpdateRequest(
        @NotEmpty
        @NameSegment
        String name
) {
}
