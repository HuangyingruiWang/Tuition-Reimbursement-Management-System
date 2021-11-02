package service;

import models.Event;
import models.Login;

import models.User;
import reposiory.EventRepo;
import reposiory.LoginRepo;
import reposiory.UserRepo;
import util.MyLogger;

import java.util.List;

public class LoginServiceImpl implements LoginService{
    LoginRepo lr;
    EventRepo er;
    UserRepo ur;

    public LoginServiceImpl(LoginRepo lr, EventRepo er, UserRepo ur) {
        this.lr = lr;
        this.er = er;
        this.ur = ur;
    }

    @Override
    public Login getLogin(int Login_id) {
        MyLogger.logger.info("Create a new Login " + Login_id + ".");
        return lr.getLogin(Login_id);
    }

    @Override
    public User getUserByLogin(Login login) {
        MyLogger.logger.info("Get User information " + login.getUsername() + ".");
        return ur.getUserByLoginId(login.getLog_id());
    }

    @Override
    public Login checkLogin(Login login) {
        MyLogger.logger.info("Get Login information for checking login.");
        return lr.getLoginByLogin(login);
    }

    @Override
    public List<Login> getAllLogins() {
        MyLogger.logger.info("Get information of all Logins.");
        return lr.getAllLogins();
    }

    @Override
    public Login addLogin(Login input) {
        MyLogger.logger.info("Create a new Login " + input.getUsername() + ".");
        return lr.addLogin(input);
    }

    @Override
    public Login updateLogin(Login change) {
        if(getLogin(change.getLog_id()) != null){
            MyLogger.logger.info("Update information of Login " + change.getUsername() + ".");
            return lr.updateLogin(change);
        }
        return null;
    }

    @Override
    public Login deleteLogin(int id) {
        Login l = lr.getLogin(id);
        if(l != null) {
            lr.deleteLogin(l);
        }
        return l;
    }
}
