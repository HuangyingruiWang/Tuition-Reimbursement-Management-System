package service;

import models.Login;
import models.User;

import java.util.List;

public interface LoginService {
    public Login getLogin(int Login_id);
    public Login checkLogin(Login login);

    public List<Login> getAllLogins();

    public Login addLogin(Login m);

    public Login updateLogin(Login change);

    public Login deleteLogin(int id);

    public User getUserByLogin(Login login);
}
