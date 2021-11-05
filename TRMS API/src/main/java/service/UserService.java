package service;

import models.User;

import java.util.List;

public interface UserService {
    public User getUser(int User_id);
    public User getUserByDId(int id);
    public User getSuperByUserId(int id);
}
