package reposiory;

import models.Department;
import models.User;

import java.util.List;

public interface UserRepo {
    public User addUser(User input);
    public List<User> getAllUsers();
    public List<User> getAllUsersByDepartment(Department department);
    public User getUser(int id);
    public User getUserByLoginId(int login_id);
    public User updateUser(User change);
    public User deleteUser(User input);
}
