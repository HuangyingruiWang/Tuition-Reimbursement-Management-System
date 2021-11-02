package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "u_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column(unique = true,nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(columnDefinition = "boolean")
    private boolean isBenCo;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "log_fk", referencedColumnName = "l_id")
    private Login login;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "department_fk", referencedColumnName = "d_id")
    private Department department;

    public User() {
    }

    public User(int user_id) {
        this.user_id = user_id;
    }

    public User(String name, String email, Login login, Department department) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.department = department;
    }

    public User(int user_id, String name, String email, boolean isBenCo, Login login, Department department) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.isBenCo = isBenCo;
        this.login = login;
        this.department = department;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBenCo() {
        return isBenCo;
    }

    public void setBenCo(boolean benCo) {
        isBenCo = benCo;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUser_id() == user.getUser_id() && isBenCo() == user.isBenCo() && Objects.equals(getName(), user.getName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getDepartment(), user.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getName(), getEmail(), isBenCo(), getLogin(), getDepartment());
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isBenCo=" + isBenCo +
                ", login=" + login +
                ", department=" + department +
                '}';
    }
}
