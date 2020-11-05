package ua.antibyte.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class EmployeesRequestDto {
    @JsonProperty("results")
    private List<EmployeeRequestDto> employees;
}
