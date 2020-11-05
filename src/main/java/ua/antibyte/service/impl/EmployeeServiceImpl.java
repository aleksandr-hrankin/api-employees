package ua.antibyte.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import ua.antibyte.dao.EmployeeDao;
import ua.antibyte.model.Employee;
import ua.antibyte.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void addAll(List<Employee> employees) {
        employeeDao.addAll(employees);
    }

    @Override
    public Employee findById(Long id) {
        return employeeDao.findById(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }
}
