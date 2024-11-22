package com.kanishka.esd_final_project.service;

import com.kanishka.esd_final_project.dto.EmployeeRequest;
import com.kanishka.esd_final_project.dto.LoginRequest;
import com.kanishka.esd_final_project.entity.Employee;
import com.kanishka.esd_final_project.exception.EmployeeNotFoundException;
import com.kanishka.esd_final_project.helper.Encryption;
import com.kanishka.esd_final_project.helper.JWTHelper;
import com.kanishka.esd_final_project.mapper.EmployeeMapper;
import com.kanishka.esd_final_project.mapper.StudentMapper;
import com.kanishka.esd_final_project.repo.EmployeeRepo;
import com.kanishka.esd_final_project.repo.StudentRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepo employeeRepo;
    private final Encryption encryption;
    private final JWTHelper jWTHelper;

    public boolean createEmployee(EmployeeRequest request) {
        Employee employee = employeeMapper.toEmployee(request);
        employee.setPassword(encryption.encode(employee.getPassword()));
        employeeRepo.save(employee);
        return true;
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepo.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with email " + email + " not found"));
    }

    public String login(@Valid LoginRequest request) {
        Employee employee = getEmployeeByEmail(request.email());
        if(employee == null) {
            throw new EmployeeNotFoundException("Employee with email " + request.email() + " not found");
        }
        if(!encryption.validates(request.password(), employee.getPassword()))
        {
            throw new EmployeeNotFoundException("Invalid password");
        }
        return jWTHelper.generateToken(request.email());
    }
}
