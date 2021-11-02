package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "supervisors")
public class Supervisor {
    @Id
    private int user_id;

    private int supervisors_id;

    public Supervisor() {
    }

    public Supervisor(int user_id, int supervisors_id) {
        this.user_id = user_id;
        this.supervisors_id = supervisors_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSupervisors_id() {
        return supervisors_id;
    }

    public void setSupervisors_id(int supervisors_id) {
        this.supervisors_id = supervisors_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supervisor that = (Supervisor) o;
        return getUser_id() == that.getUser_id() && getSupervisors_id() == that.getSupervisors_id();
    }

    public Supervisor(int supervisors_id) {
        this.supervisors_id = supervisors_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getSupervisors_id());
    }

    @Override
    public String toString() {
        return "Supervisors{" +
                "user_id=" + user_id +
                ", supervisors_id=" + supervisors_id +
                '}';
    }
}
