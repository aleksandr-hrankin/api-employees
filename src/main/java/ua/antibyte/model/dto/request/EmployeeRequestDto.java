package ua.antibyte.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeRequestDto {
    private String gender;
    @JsonProperty("name")
    private EmployeeNameRequestDto employeeNameRequestDto;
    private String email;
    @JsonProperty("dob")
    private EmployeeDobRequestDto employeeDobRequestDto;
    private String phone;
    @JsonProperty("picture")
    private EmployeePictureRequestDto employeePictureRequestDto;
}
