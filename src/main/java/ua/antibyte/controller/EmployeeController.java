package ua.antibyte.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.antibyte.model.Employee;
import ua.antibyte.model.dto.response.EmployeeInfoResponseDto;
import ua.antibyte.model.dto.response.EmployeeResponseDto;
import ua.antibyte.service.EmployeeService;
import ua.antibyte.service.mapper.EmployeeMapper;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService,
                              EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public String getAll(Model model) {
        List<EmployeeResponseDto> employees = employeeService.getAll().stream()
                .map(employeeMapper::mapEmployeeToResponseDto)
                .collect(Collectors.toList());
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findById(id);
        EmployeeInfoResponseDto employeeInfoResponseDto = employeeMapper
                .mapEmployeeToInfoResponseDto(employee);
        model.addAttribute("employee", employeeInfoResponseDto);
        return "employee-info";
    }
}
