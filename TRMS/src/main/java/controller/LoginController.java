package controller;

import com.google.gson.Gson;
import io.javalin.http.Handler;
import models.Login;
import models.User;
import service.LoginService;
import util.MyLogger;

public class LoginController {
    LoginService ls;
    Gson gson = new Gson();

    public LoginController(LoginService ls) {
        this.ls = ls;
    }

    public Handler checkLogin = (context) -> {
        String str = context.header("Authorization");
        if(str != null){
            String[] arry = str.split(":",2);
            Login l = new Login();
            l.setUsername(arry[0]);
            l.setPassword(arry[1]);
            l = ls.checkLogin(l);
            if(l !=null){
                User u = ls.getUserByLogin(l);
                context.result(gson.toJson(u));
                MyLogger.logger.info("The login input found in our user database.");
                context.status(200);
            }else {
                MyLogger.logger.info("Didn't find this user login.");
                context.status(205);
            }
        }else {
            MyLogger.logger.info("Fetch setting has error.");
        }

    };
}
