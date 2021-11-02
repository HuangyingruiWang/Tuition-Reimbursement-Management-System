package service;

import models.*;
import reposiory.*;
import util.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class EventServiceImpl implements EventService{
    EventRepo er;
    GradeFormatRepo gr;
    EventTypeRepo etr;
    SupervisorRepo pr;
    HeadRepo hr;
    StatusRepo sr;
    UserRepo ur;

    public EventServiceImpl(EventRepo er, GradeFormatRepo gr, EventTypeRepo etr, SupervisorRepo pr, HeadRepo hr, StatusRepo sr, UserRepo ur) {
        this.er = er;
        this.gr = gr;
        this.etr = etr;
        this.pr = pr;
        this.hr = hr;
        this.sr = sr;
        this.ur = ur;
    }

    @Override
    public Event getEvent(int id) {
        MyLogger.logger.info("Get information of Event " + id + ".");
        return er.getEvent(id);
    }

    @Override
    public List<Event> getAllEventsByUserId(int user_id) {
        MyLogger.logger.info("Get all Events for user " + user_id + ".");
        int status_id;
        long autoMS = 30000;
        List<Event> eventList = new ArrayList<>();
        //as runner
        User runner = ur.getUser(user_id);
        List<Event> runnerWatchList = er.getAllEventsByUser(runner);
        List<Event> deleteList = new ArrayList<>();
        if(runnerWatchList != null){
            for(Event e : runnerWatchList){
                if((er.timeupdate() - e.getLast_update()) > autoMS){
                    status_id = e.getStatus().getStatus_id();
                    switch (status_id){
                        case 1:
                            e.setStatus(sr.getStatus(2));
                            break;
                        case 2:
                            e.setStatus(sr.getStatus(3));
                            break;
                        case 3:
                            e.setStatus(sr.getStatus(4));
                            break;
                        case 6:
                            e.setStatus(sr.getStatus(7));
                            break;
                    }
                    e.setLast_update(er.timeupdate());
                    er.updateEvent(e);
                    deleteList.add(e);
                }
            }
            runnerWatchList.removeAll(deleteList);
        }
        //as supervisor
        List<Supervisor> usersList = pr.getAllUsersBySupervisor(user_id);
        List<User> supervisorUserList = new ArrayList<>();
        if(usersList != null){
            for(Supervisor s : usersList){
                supervisorUserList.add(ur.getUser(s.getUser_id()));
            }
        }
        List<Event> supervisorWatchList = new ArrayList<>();
        List<Event> headerWatchList = new ArrayList<>();
        List<Event> benCoWatchList = new ArrayList<>();
        for(User u : supervisorUserList){
            List<Event> events = er.getAllEventsByUser(u);
            if (events != null){
                for(Event e : events){
                    status_id = e.getStatus().getStatus_id();
                    switch (status_id){
                        case 1:

                        case 6:

                        case 12:

                        case 14:
                            supervisorWatchList.add(e);
                            break;
                    }

                }
            }

        }
        User head = hr.getHeadByDepartmentId(runner.getDepartment().getDepartment_id());
        if(runner.equals(head)){
            List<User> headerUserList = ur.getAllUsersByDepartment(runner.getDepartment());
            for(User u : headerUserList){
                List<Event> events = er.getAllEventsByUser(u);
                if (events != null){
                    for(Event e : events){
                        status_id = e.getStatus().getStatus_id();
                        switch (status_id){
                            case 2:

                            case 15:
                                headerWatchList.add(e);
                                break;
                        }

                    }
                }

            }
        }
        //as benCo
        if(runner.isBenCo()){
            List<User> benCoUserList = ur.getAllUsers();
            for(User u : benCoUserList){
                List<Event> events = er.getAllEventsByUser(u);
                if (events != null){
                    for(Event e : events){
                        status_id = e.getStatus().getStatus_id();
                        switch (status_id){
                            case 3:

                            case 5:

                            case 7:

                            case 17:
                                benCoWatchList.add(e);
                                break;
                        }

                    }
                }

            }
        }
        if(runnerWatchList != null){ eventList.addAll(runnerWatchList); }
        eventList.removeAll(supervisorWatchList);
        eventList.addAll(supervisorWatchList);
        eventList.removeAll(headerWatchList);
        eventList.addAll(headerWatchList);
        eventList.removeAll(benCoWatchList);
        eventList.addAll(benCoWatchList);
        return eventList;
    }



    @Override
    public List<Event> getAllEvents() {
        return er.getAllEvents();
    }

    @Override
    public Event addEvent(Event m) {
        MyLogger.logger.info("Start add a new Event");
        User user = ur.getUser(m.getUser_fk().getUser_id());
        GradeFormat gf = gr.getGradeFormat(m.getFormat().getGrade_id());
        EventType et = etr.getEventType(m.getType().getType_id());
        m.setFormat(gf);
        m.setType(et);
        m.setUser_fk(user);
        double total = 1000;
        double available = total;
        double pendingaward;
        List<Event> eventList = er.getAllEventsByUser(user);
        for(Event e: eventList){
            if (e.getStatus().getStatus_id() != 9 && e.getStatus().getStatus_id() != 17){
                pendingaward = e.getCost() * e.getType().getPercetage();
                available = available - pendingaward;
            }else if(e.getStatus().getStatus_id() == 17){
                return m;
            }
        }
        if (available > 0 && available >= (m.getCost() * m.getType().getPercetage())){
            MyLogger.logger.info("Add Event succeed");
            m.setLast_update(er.timeupdate());
            m.setStatus(sr.getStatus(1));
        }else {
            MyLogger.logger.info("Your reimbursement exceeds the limit");
            m.setLast_update(er.timeupdate());
            m.setStatus(sr.getStatus(17));
        }
        m = er.addEvent(m);
        return m;
    }

    @Override
    public Event updateEvent(Event change, int user_id, int event_id) {
        MyLogger.logger.info("Start update a Event");
        Event event = er.getEvent(event_id);
        if (event != null){
            User event_owner = event.getUser_fk();
            int current_status = event.getStatus().getStatus_id();
            User supervisor = ur.getUser(pr.getSupervisor(event_owner.getUser_id()).getSupervisors_id());
            User head = hr.getHeadByDepartmentId(event_owner.getDepartment().getDepartment_id());
            User runner = ur.getUser(user_id);
            if(runner.equals(event_owner)){
                switch (current_status){
                    case 4:
                        if(change.getActual_grade() != null){   //4-5
                            event.setActual_grade(change.getActual_grade());
                            event.setStatus(sr.getStatus(5));
                        }else if (event.getFormat().getGrade_id() == 8){  //4-6
//                              event.setFile(change.getFile());
                            event.setStatus(sr.getStatus(6));
                        }
                        break;

                    case 10:
                        if(change.getStatus().getStatus_id() == 2){ //10-2
//                        event.setFile(change.getFile());
                            if (runner.equals(head)) {
                                if (runner.isBenCo()) {
                                    event.setStatus(sr.getStatus(4));
                                } else {
                                    event.setStatus(sr.getStatus(3));
                                }
                            }else {
                                event.setStatus(sr.getStatus(2));
                            }
                        }else {
                            event.setStatus(sr.getStatus(9));
                        }
                        break;
                    case 11:
                        if(change.getStatus().getStatus_id() == 3){ //11-3
//                        event.setFile(change.getFile());
                            if (runner.isBenCo()) {
                                event.setStatus(sr.getStatus(4));
                            } else {
                                event.setStatus(sr.getStatus(3));
                            }
                        }else {
                            event.setStatus(sr.getStatus(9));
                        }
                        break;
                    case 13:
                        if(change.getStatus().getStatus_id() == 4){
//                        event.setFile(change.getFile());
                                event.setStatus(sr.getStatus(4));
                            }else {
                                event.setStatus(sr.getStatus(9));
                            }
                        break;
                    case 16:
                        if(change.getStatus() == null){ //16-3
                            event.setStatus(sr.getStatus(3));
                        }
                        break;

                }
            }
            if(runner.equals(supervisor)){
                switch (current_status){
                    case 1:
                        if(change.getStatus() == null){ //1-2
                            if (runner.equals(head)) {
                                if (runner.isBenCo()) {
                                    event.setStatus(sr.getStatus(4));
                                } else {
                                    event.setStatus(sr.getStatus(3));
                                }
                            }
                        }else {
                            if(change.getStatus().getStatus_id() == 10) {
                                event.setStatus(sr.getStatus(10));
                            }else {
                                event.setStatus(sr.getStatus(9));
                            }
                        }
                        break;

                    case 6:
                        if(change.getStatus() == null){ //6-7
                            if(runner.isBenCo()){
                                event.setStatus(sr.getStatus(8));
                            }else {
                                event.setStatus(sr.getStatus(7));
                            }
                        }else {
                            event.setStatus(sr.getStatus(9));
                        }
                        break;
                    case 12:
                        if(change.getStatus().getStatus_id() == 3){ //12-3
//                        event.setFile(change.getFile());
                            if (runner.isBenCo()) {
                                event.setStatus(sr.getStatus(4));
                            } else {
                                event.setStatus(sr.getStatus(3));
                            }
                        }else {
                            event.setStatus(sr.getStatus(9));
                        }
                        break;

                    case 14:
                        if(change.getStatus().getStatus_id() == 4){ //14-4
//                        event.setFile(change.getFile());
                            event.setStatus(sr.getStatus(4));
                        }else {
                            event.setStatus(sr.getStatus(9));
                        }
                        break;
                }
            }
            if(runner.equals(head)){
                switch (current_status){
                    case 2:
                        if(change.getStatus() == null){ //2-3
                            if(runner.isBenCo()){
                                event.setStatus(sr.getStatus(4));
                            }else {
                                event.setStatus(sr.getStatus(3));
                            }
                        }else {
                            if (change.getStatus().getStatus_id() == 11 || change.getStatus().getStatus_id() == 12){
                                event.setStatus(sr.getStatus(change.getStatus().getStatus_id()));
                            }else {
                                event.setStatus(sr.getStatus(9));
                            }
                        }
                        break;

                    case 15:
                        if(change.getStatus().getStatus_id() == 4){ //15-4
//                        event.setFile(change.getFile());
                            event.setStatus(sr.getStatus(4));
                        }else {
                            event.setStatus(sr.getStatus(9));
                        }
                        break;
                }
            }
            if(runner.isBenCo()){
                switch (current_status){
                    case 3:
                        if(change.getStatus() == null){ //3-4
                            event.setStatus(sr.getStatus(4));
                        }else {
                            if(change.getStatus().getStatus_id() == 13 || change.getStatus().getStatus_id() == 14 || change.getStatus().getStatus_id() == 15){
                                event.setStatus(sr.getStatus(change.getStatus().getStatus_id()));
                            }else {
                                event.setStatus(sr.getStatus(9));
                            }
                        }
                        break;

                    case 5:
                    case 7:
                        if(change.getStatus() == null){ //5-8 7-8
//                        event.setFile(change.getFile());
                            event.setStatus(sr.getStatus(8));
                        }else {
                            event.setStatus(sr.getStatus(9));
                        }
                        break;

                    case 17:
                        if(change.getStatus() == null){ //17-16
                            event.setCost(change.getCost());
                            event.setStatus(sr.getStatus(16));
                        }else {
                            event.setStatus(sr.getStatus(9));
                        }
                        break;
                }
            }
            event.setLast_update(er.timeupdate());
            event = er.updateEvent(event);
        }else{
            MyLogger.logger.info("Can not found this Event" + event_id + " in our database.");
        }

        return event;
    }

    @Override
    public Event deleteEvent(int user_id, int event_id) {
        MyLogger.logger.info("Start delete a Event");
        Event event = er.getEvent(event_id);
        User runner = ur.getUser(user_id);
        if (event != null){
            User event_owner = event.getUser_fk();
            if(runner.equals(event_owner) && event.getStatus().getStatus_id() == 16){
                er.deleteEvent(event);
            }
//            if(runner.isBenCo()){
//                er.deleteEvent(event);
//            }

        }
        return event;
    }
}
