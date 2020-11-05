package ua.antibyte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Component;
import ua.antibyte.exception.ApiConnectionException;
import ua.antibyte.model.Employee;
import ua.antibyte.model.dto.request.EmployeesRequestDto;
import ua.antibyte.service.mapper.EmployeeMapper;

@Component
@Log4j
public class EmployeeInitializer {
    private static final String GET_EMPLOYEES_URL = "https://randomuser.me/api/?results=20";
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeInitializer(CloseableHttpClient httpClient,
                               ObjectMapper objectMapper,
                               EmployeeService employeeService,
                               EmployeeMapper employeeMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostConstruct
    private void init() {
        saveEmployeesToDb(loadEmployeesFromApi());
    }

    private EmployeesRequestDto loadEmployeesFromApi() {
        HttpGet request = new HttpGet(GET_EMPLOYEES_URL);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            log.info("Data have been successfully received at " + GET_EMPLOYEES_URL);
            return objectMapper.readValue(response.getEntity().getContent(),
                    EmployeesRequestDto.class);
        } catch (IOException e) {
            throw new ApiConnectionException("Can't send request to " + GET_EMPLOYEES_URL, e);
        }
    }

    private void saveEmployeesToDb(EmployeesRequestDto employeesRequestDto) {
        List<Employee> employees = employeeMapper
                .mapEmployeesRequestDtoToEmployees(employeesRequestDto);
        employeeService.addAll(employees);
    }
}
