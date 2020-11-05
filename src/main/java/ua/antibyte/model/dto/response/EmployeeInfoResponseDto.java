package ua.antibyte.model.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class EmployeeInfoResponseDto {
    private String gender;
    private String title;
    private String first;
    private String last;
    private String email;
    private LocalDate dob;
    private int age;
    private String phone;
    private String picture;
}
