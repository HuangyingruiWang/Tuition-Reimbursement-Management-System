package controller;

import com.google.gson.Gson;
import io.javalin.http.Handler;
import models.Event;
import models.User;
import service.EventService;
import service.UserService;
import util.MyLogger;

import java.util.List;

public class EventConntroller {
    EventService es;
    UserService us;
    Gson gson = new Gson();

    public EventConntroller(EventService es, UserService us) {
        this.es = es;
        this.us = us;
    }

    public Handler getAllEventsByUserId = (context) -> {
        String input1 = context.pathParam("u_id");
        int u_id;
        List<Event> eventList;
        try {
            u_id = Integer.parseInt(input1);
        }catch (NumberFormatException e){
            u_id = -1;
            MyLogger.logger.error("NumberFormatException");
        }
        eventList = es.getAllEventsByUserId(u_id);
        if(eventList !=null){
            if(eventList.size() != 0){
                context.result(gson.toJson(eventList));
                MyLogger.logger.info("Pull out all events for user " + u_id + ".");
            }
            else {
                MyLogger.logger.info("No events in database for user " + u_id + ".");
            }
            context.status(200);
        }else{
            MyLogger.logger.info("Didn't find this user.");
            context.status(404);
        }
    };

    public Handler getUserById = (context) -> {
        String input1 = context.pathParam("u_id");
        int u_id;
        User user;
        try {
            u_id = Integer.parseInt(input1);
        }catch (NumberFormatException e){
            u_id = -1;
            MyLogger.logger.error("NumberFormatException");
        }
        user = us.getUser(u_id);
        if(user !=null){
            context.result(gson.toJson(user));
            MyLogger.logger.info("Pull out user " + u_id + " information.");
            context.status(200);
        }else{
            MyLogger.logger.info("Didn't find this user.");
            context.status(404);
        }
    };

    public Handler getHeadByDepartmentId = (context) -> {
        String input1 = context.pathParam("d_id");
        int d_id;
        User user;
        try {
            d_id = Integer.parseInt(input1);
        }catch (NumberFormatException e){
            d_id = -1;
            MyLogger.logger.error("NumberFormatException");
        }
        user = us.getUserByDId(d_id);
        if(user !=null){
            context.result(gson.toJson(user));
            MyLogger.logger.info("Pull out user " + d_id + " information.");
            context.status(200);
        }else{
            MyLogger.logger.info("Didn't find this user.");
            context.status(404);
        }
    };

    public Handler getSupervisorById = (context) -> {
        String input1 = context.pathParam("s_id");
        int s_id;
        User user;
        try {
            s_id = Integer.parseInt(input1);
        }catch (NumberFormatException e){
            s_id = -1;
            MyLogger.logger.error("NumberFormatException");
        }
        user = us.getSuperByUserId(s_id);
        if(user !=null){
            context.result(gson.toJson(user));
            MyLogger.logger.info("Pull out user " + s_id + " information.");
            context.status(200);
        }else{
            MyLogger.logger.info("Didn't find this user.");
            context.status(404);
        }
    };


    public Handler addEventForUser = (context) -> {
        String input1 = context.pathParam("u_id");
        int u_id;
        Event event = gson.fromJson(context.body(), Event.class);
        try {
            u_id = Integer.parseInt(input1);
        }catch (NumberFormatException e) {
            u_id = -1;
            MyLogger.logger.error("NumberFormatException");
        }
        event = es.addEvent(event);
        if(event !=null){
            if(event.getEvent_id() != 0){
                MyLogger.logger.info("Add events for user " + u_id + " succeeded");
                context.status(200);
            }else {context.status(422);}
        }else{
            MyLogger.logger.info("Didn't find this user.");
            context.status(404);
        }
    };

    public Handler updateEventForUser = (context) ->{
        String input1 = context.pathParam("u_id");
        String input2 = context.pathParam("e_id");
        int u_id;
        int e_id;
        Event event = gson.fromJson(context.body(), Event.class);
        try {
            u_id = Integer.parseInt(input1);
            e_id = Integer.parseInt(input2);
        }catch (NumberFormatException e){
            u_id = -1;
            e_id = -1;
            MyLogger.logger.error("NumberFormatException");
        }
        event = es.updateEvent(event, u_id, e_id);
        if(event !=null){
            MyLogger.logger.info("Update event for user " + u_id + " succeeded");
            context.status(200);
        }else{
            MyLogger.logger.info("Didn't find this user.");
            context.status(404);
        }
    };

    public Handler deleteEventForUser = (context) ->{
        String input1 = context.pathParam("u_id");
        String input2 = context.pathParam("e_id");
        int u_id;
        int e_id;
        try {
            u_id = Integer.parseInt(input1);
            e_id = Integer.parseInt(input2);
        }catch (NumberFormatException e){
            u_id = -1;
            e_id = -1;
            MyLogger.logger.error("NumberFormatException");
        }
        Event event = es.deleteEvent(u_id, e_id);
        if(event !=null){
            MyLogger.logger.info("Delete event for user " + u_id + " succeeded");
            context.status(200);
        }else{
            MyLogger.logger.info("Didn't find this user.");
            context.status(404);
        }
    };

}
