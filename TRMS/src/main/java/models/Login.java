package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @Column(name = "l_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int log_id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public Login() {
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Login(int log_id, String username, String password) {
        this.log_id = log_id;
        this.username = username;
        this.password = password;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return getLog_id() == login.getLog_id() && Objects.equals(getUsername(), login.getUsername()) && Objects.equals(getPassword(), login.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLog_id(), getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "Login{" +
                "log_id=" + log_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
