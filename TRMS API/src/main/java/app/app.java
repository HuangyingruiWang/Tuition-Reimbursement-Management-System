package app;

import controller.EventConntroller;
import controller.LoginController;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import reposiory.*;
import service.*;


public class app {
    static LoginRepo loginRepo = new LoginRepoHBImpl();
    static EventRepo eventRepo = new EventRepoHBImpl();
    static UserRepo userRepo = new UserRepoHBImpl();
    static LoginService ls = new LoginServiceImpl(loginRepo,eventRepo,userRepo);

    static GradeFormatRepo gradeFormatRepo = new GradeFormatRepoHBImpl();
    static EventTypeRepo eventTypeRepo = new EventTypeRepoHBImpl();
    static StatusRepo statusRepo = new StatusRepoHBImpl();
    static SupervisorRepo supervisorRepo = new SupervisorRepoHBImpl();
    static HeadRepo headRepo = new HeadRepoHBImpl();
    static EventService es = new EventServiceImpl(eventRepo,gradeFormatRepo,eventTypeRepo,supervisorRepo,headRepo,statusRepo,userRepo);

    static UserService us = new UserServiceImpl(userRepo,headRepo,supervisorRepo);
    static LoginController lc = new LoginController(ls);
    static EventConntroller ec = new EventConntroller(es,us);

    public static void main(String[] args) {
        //Establish our Javalin Object
        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins);

        //Establish the Route/Endpoints that Javalin will manage.
        establishRoutes(app);
        //Run Javalin
        app.start(5432);
        loginRepo.getLogin(0);
//        repoTest();

    }

    private static void establishRoutes(Javalin app) {
        //Tell Javalin which routes/endpoints Javalin will manage
        app.get("/hello", (ctx) -> ctx.result("Hello World!"));
        //Establish a route to the 'landing' page.
        app.get("/", (ctx) -> ctx.result("Welcome to Our Movie App"));

        addLoginRoutes(app);
        addEventRoutes(app);
    }

    private static void addEventRoutes(Javalin app) {

        //Again, never Actually skip the Service.

        app.get("/users/:u_id/events", ec.getAllEventsByUserId);
        app.post("/users/:u_id/events", ec.addEventForUser);
        app.put("/users/:u_id/events/:e_id", ec.updateEventForUser);
        app.delete("/users/:u_id/events/:e_id",ec.deleteEventForUser);

        app.get("/users/:u_id", ec.getUserById);
        app.get("/supervisors/:s_id", ec.getSupervisorById);
        app.get("/heads/:d_id", ec.getHeadByDepartmentId);

    }

    private static void addLoginRoutes(Javalin app) {


        app.get("/login", lc.checkLogin);

    }
}
