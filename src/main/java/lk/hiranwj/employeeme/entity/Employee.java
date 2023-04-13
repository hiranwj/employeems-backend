package lk.hiranwj.employeeme.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Employee") // For define table name
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // For auto generate id
    private int empId;
    private String empName;
    private String empAddress;
    private String empMobNum;
}
