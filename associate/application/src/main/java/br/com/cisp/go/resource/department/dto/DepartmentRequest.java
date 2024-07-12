package br.com.cisp.go.resource.department.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record DepartmentRequest(
        @Min(value = 1, message = "must be greater than or equal to 1")
        @Max(value = 999,message = "must be less than or equal to 999")
        Integer companyId,
        @NotEmpty
        String name
) {
}
