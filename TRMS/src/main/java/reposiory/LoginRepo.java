package reposiory;

import models.Login;

import java.util.List;

public interface LoginRepo {
    public Login addLogin(Login input);
    public List<Login> getAllLogins();
    public Login getLogin(int id);
    public Login getLoginByLogin(Login login);
    public Login updateLogin(Login change);
    public Login deleteLogin(Login input);
}
