package br.com.cisp.go.resource.segment.dto;

import br.com.cisp.go.resource.validation.NameSegment;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record SegmentRequest(
        @Min(value = 1, message = "must be greater than or equal to 1")
        @Max(value = 8, message = "must be less than or equal to 8")
        Integer code,
        @NotEmpty(message = "must not be empty")
        @NameSegment
        String name
) {
}
