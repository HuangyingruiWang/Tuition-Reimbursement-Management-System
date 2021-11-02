package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name = "s_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int status_id;

    @Column(name = "name", unique = true, nullable = false)
    private String statusname;

    public Status() {
    }

    public Status(int status_id) {
        this.status_id = status_id;
    }

    public Status(String statusname) {
        this.statusname = statusname;
    }

    public Status(int status_id, String statusname) {
        this.status_id = status_id;
        this.statusname = statusname;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return getStatus_id() == status.getStatus_id() && Objects.equals(getStatusname(), status.getStatusname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus_id(), getStatusname());
    }

    @Override
    public String toString() {
        return "Status{" +
                "status_id=" + status_id +
                ", statusname='" + statusname + '\'' +
                '}';
    }
}
