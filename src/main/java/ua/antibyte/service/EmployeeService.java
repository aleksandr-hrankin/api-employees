package ua.antibyte.service;

import java.util.List;
import ua.antibyte.model.Employee;

public interface EmployeeService {
    void addAll(List<Employee> employees);

    Employee findById(Long id);

    List<Employee> getAll();
}
