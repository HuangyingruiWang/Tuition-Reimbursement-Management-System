package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "d_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int department_id;
    @Column(name = "type")
    private String departmentname;


    public Department() {
    }

    public Department(String departmentname) {
        this.departmentname = departmentname;
    }

    public Department(int department_id, String department_name) {
        this.department_id = department_id;
        this.departmentname = department_name;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return getDepartment_id() == that.getDepartment_id() && Objects.equals(getDepartmentname(), that.getDepartmentname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartment_id(), getDepartmentname());
    }

    @Override
    public String toString() {
        return "Departments{" +
                "department_id=" + department_id +
                ", departmentname='" + departmentname + '\'' +
                '}';
    }
}
