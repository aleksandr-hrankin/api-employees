package ua.antibyte.model.dto.response;

import lombok.Data;

@Data
public class EmployeeResponseDto {
    private Long id;
    private String title;
    private String first;
    private String last;
    private String picture;
}
