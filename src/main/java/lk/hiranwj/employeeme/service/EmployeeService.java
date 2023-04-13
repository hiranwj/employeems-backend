package lk.hiranwj.employeeme.service;

import lk.hiranwj.employeeme.dto.EmployeeDTO;
import lk.hiranwj.employeeme.entity.Employee;
import lk.hiranwj.employeeme.repo.EmployeeRepo;
import lk.hiranwj.employeeme.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired  // For INJECT, Because JPA methods need to be used in this class
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveEmployee(EmployeeDTO employeeDTO) { // The data is fetched from a DTO
        if(employeeRepo.existsById(employeeDTO.getEmpId())) { // existsById <- JPA method (return a boolean value)
            return VarList.RSP_DUPLICATED;
        }else {
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepo.existsById(employeeDTO.getEmpId())) {
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employeeList = employeeRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<ArrayList<EmployeeDTO>>(){

        }.getType());
    }

    public EmployeeDTO searchEmployee(int empId) {
        if (employeeRepo.existsById(empId)) {
            Employee employee = employeeRepo.findById(empId).orElse(null);
            return modelMapper.map(employee, EmployeeDTO.class);
        }else {
            return null;
        }
    }

    public String deleteEmployee(int empId) {
        if (employeeRepo.existsById(empId)) {
            employeeRepo.deleteById(empId);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
