package service;

import models.User;
import reposiory.HeadRepo;
import reposiory.SupervisorRepo;
import reposiory.UserRepo;

import java.util.List;

public class UserServiceImpl implements UserService{
    UserRepo ur;
    HeadRepo hr;
    SupervisorRepo sr;

    public UserServiceImpl(UserRepo ur, HeadRepo hr, SupervisorRepo sr) {
        this.ur = ur;
        this.hr = hr;
        this.sr = sr;
    }

    @Override
    public User getUser(int id) {
        return ur.getUser(id);
    }


    public User getUserByDId(int id) {
        return hr.getHeadByDepartmentId(id);
    }

    public User getSuperByUserId(int id) {
        return ur.getUser(sr.getSupervisor(id).getSupervisors_id());
    }

}
