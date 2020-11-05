package ua.antibyte.dao;

import java.util.List;
import ua.antibyte.model.Employee;

public interface EmployeeDao {
    void addAll(List<Employee> employees);

    Employee findById(Long id);

    List<Employee> getAll();
}
