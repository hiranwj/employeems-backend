package lk.hiranwj.employeeme.repo;

import lk.hiranwj.employeeme.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> { // <Entity type, Primary key type>

}
