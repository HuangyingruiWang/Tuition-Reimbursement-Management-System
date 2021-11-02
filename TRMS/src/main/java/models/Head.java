package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "heads")
public class Head {
    @Id
    private int department_fk;

    @OneToOne
    @JoinColumn(name = "departmenthead_fk", referencedColumnName = "u_id")
    private User departmenthead;

    public Head() {
    }

    public Head(User departmenthead) {
        this.departmenthead = departmenthead;
    }

    public Head(int department_fk, User departmenthead) {
        this.department_fk = department_fk;
        this.departmenthead = departmenthead;
    }

    public int getDepartment_fk() {
        return department_fk;
    }

    public void setDepartment_fk(int department_fk) {
        this.department_fk = department_fk;
    }

    public User getDepartmenthead() {
        return departmenthead;
    }

    public void setDepartmenthead(User departmenthead) {
        this.departmenthead = departmenthead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Head head = (Head) o;
        return getDepartment_fk() == head.getDepartment_fk() && Objects.equals(getDepartmenthead(), head.getDepartmenthead());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartment_fk(), getDepartmenthead());
    }

    @Override
    public String toString() {
        return "Head{" +
                "department_fk=" + department_fk +
                ", departmenthead=" + departmenthead+
                '}';
    }
}
