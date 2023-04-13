package lk.hiranwj.employeeme.service;

import lk.hiranwj.employeeme.dto.EmployeeDTO;
import lk.hiranwj.employeeme.entity.Employee;
import lk.hiranwj.employeeme.repo.EmployeeRepo;
import lk.hiranwj.employeeme.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

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
}
