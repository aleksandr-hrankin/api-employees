package ua.antibyte.service.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ua.antibyte.model.Employee;
import ua.antibyte.model.Picture;
import ua.antibyte.model.dto.request.EmployeePictureRequestDto;
import ua.antibyte.model.dto.request.EmployeesRequestDto;
import ua.antibyte.model.dto.response.EmployeeInfoResponseDto;
import ua.antibyte.model.dto.response.EmployeeResponseDto;

@Component
public class EmployeeMapper {
    public List<Employee> mapEmployeesRequestDtoToEmployees(EmployeesRequestDto employeesDto) {
        return employeesDto.getEmployees().stream()
                .map(employeeDto -> {
                    Employee employee = new Employee();
                    employee.setGender(employeeDto.getGender());
                    employee.setTitle(employeeDto.getEmployeeNameRequestDto().getTitle());
                    employee.setFirst(employeeDto.getEmployeeNameRequestDto().getFirst());
                    employee.setLast(employeeDto.getEmployeeNameRequestDto().getLast());
                    employee.setEmail(employeeDto.getEmail());
                    employee.setDob(employeeDto.getEmployeeDobRequestDto().getDate());
                    employee.setAge(employeeDto.getEmployeeDobRequestDto().getAge());
                    employee.setPhone(employeeDto.getPhone());

                    Picture picture = mapEmployeePictureRequestDtoToPicture(
                            employeeDto.getEmployeePictureRequestDto());
                    employee.setPicture(picture);
                    return employee; })
                .collect(Collectors.toList());
    }

    public EmployeeResponseDto mapEmployeeToResponseDto(Employee employee) {
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setId(employee.getId());
        employeeResponseDto.setTitle(employee.getTitle());
        employeeResponseDto.setFirst(employee.getFirst());
        employeeResponseDto.setLast(employee.getLast());
        employeeResponseDto.setPicture(employee.getPicture().getMedium());
        return employeeResponseDto;
    }

    public EmployeeInfoResponseDto mapEmployeeToInfoResponseDto(Employee employee) {
        EmployeeInfoResponseDto employeeInfoResponseDto = new EmployeeInfoResponseDto();
        employeeInfoResponseDto.setGender(employee.getGender());
        employeeInfoResponseDto.setTitle(employee.getTitle());
        employeeInfoResponseDto.setFirst(employee.getFirst());
        employeeInfoResponseDto.setLast(employee.getLast());
        employeeInfoResponseDto.setEmail(employee.getEmail());
        employeeInfoResponseDto.setDob(employee.getDob());
        employeeInfoResponseDto.setAge(employee.getAge());
        employeeInfoResponseDto.setPhone(employee.getPhone());
        employeeInfoResponseDto.setPicture(employee.getPicture().getLarge());
        return employeeInfoResponseDto;
    }

    private Picture mapEmployeePictureRequestDtoToPicture(
            EmployeePictureRequestDto employeePictureRequestDto) {
        Picture picture = new Picture();
        picture.setLarge(employeePictureRequestDto.getLarge());
        picture.setMedium(employeePictureRequestDto.getMedium());
        return picture;
    }
}
