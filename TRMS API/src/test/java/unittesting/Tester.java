package unittesting;

import models.*;
import org.junit.jupiter.api.Test;
import reposiory.*;
import service.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Tester {
    static EventRepo er = new EventRepoHBImpl();
    static GradeFormatRepo gr = new GradeFormatRepoHBImpl();
    static EventTypeRepo tr = new EventTypeRepoHBImpl();
    static SupervisorRepo supervisorRepo = new SupervisorRepoHBImpl();
    static HeadRepo headRepo = new HeadRepoHBImpl();
    static StatusRepo sr = new StatusRepoHBImpl();
    static UserRepo ur = new UserRepoHBImpl();
    static EventService es = new EventServiceImpl(er,gr,tr,supervisorRepo,headRepo,sr,ur);

    static List<Integer> eventList = new ArrayList<>();
    static GradeFormat format = gr.getGradeFormat(5);
    static EventType type = tr.getEventType(3);
    static Status status = sr.getStatus(16);
    static User user = ur.getUser(3);
    static String grade = "C";
    static Event createEvent = new Event(er.timeupdate() + 7 * 24 * 60 * 60, er.timeupdate(), 20, true, false, "111@111.com", "1222cstreet","dayday",grade,format,type,status,user);
    static Event deleteEvent = new Event(2,status, user);

    static LoginRepo lr = new LoginRepoHBImpl();
    static LoginService ls = new LoginServiceImpl(lr,er,ur);

    static HeadRepo hr = new HeadRepoHBImpl();
    static UserService us = new UserServiceImpl(ur,hr,supervisorRepo);

    static DepartmentRepo dr = new DepartmentRepoHBImpl();

    @Test
    public void user(){
        Login l = new Login("sad","sad");
        lr.addLogin(l);
        Department depart = dr.getDepartment(2);
        User u = new User("qqq","esae@sda",l,depart);
        ur.addUser(u);
        u.setBenCo(true);
        u = ur.updateUser(u);
        assertNotNull(u);
        u = ur.deleteUser(u);
        assertNotNull(u);
        lr.deleteLogin(l);
        assertNotNull(us.getSuperByUserId(1));
    }

    @Test
    public void supervisor(){
        Supervisor s = new Supervisor(6);
        supervisorRepo.addSupervisor(s);
        assertEquals(s,supervisorRepo.getSupervisor(s.getUser_id()));
        s.setSupervisors_id(5);
        s = supervisorRepo.updateSupervisor(s);
        assertNotNull(s);
        s = supervisorRepo.deleteSupervisor(s);
        assertNotNull(s);
    }

    @Test
    public void status(){
        Status status = new Status("gg");
        sr.addStatus(status);
        assertEquals(status,sr.getStatus(status.getStatus_id()));
        List<Status> statuses = sr.getAllStatuss();
        assertNotNull(statuses);
        status.setStatusname("ok");
        status = sr.updateStatus(status);
        assertNotNull(status);
        status = sr.deleteStatus(status);
        assertNotNull(status);
    }

    @Test
    public void grade(){
        GradeFormat gradeFormat = new GradeFormat("a","letter");
        gr.addGradeFormat(gradeFormat);
        assertEquals(gradeFormat,gr.getGradeFormat(gradeFormat.getGrade_id()));
        List<GradeFormat> gradeFormats = gr.getAllGradeFormats();
        assertNotNull(gradeFormats);
        gradeFormat.setcutoff_grade("d");
        gradeFormat = gr.updateGradeFormat(gradeFormat);
        assertNotNull(gradeFormat);
        gradeFormat = gr.deleteGradeFormat(gradeFormat);
        assertNotNull(gradeFormat);
    }

    @Test
    public void head(){
        User u = ur.getUser(6);
        Head head = new Head(u);
        hr.addHead(head);
        assertEquals(head.getDepartmenthead(),hr.getHeadByDepartmentId(head.getDepartment_fk()));
        assertNotNull(hr.getAllHeads());
        head.setDepartmenthead(ur.getUser(7));
        head = hr.updateHead(head);
        assertNotNull(head);
        head = hr.deleteHead(head);
        assertNotNull(head);
    }

    @Test
    public void eventType(){
        EventType type = new EventType("abc",.2);
        tr.addEventType(type);
        assertNotNull(tr.getAllEventTypes());
        assertEquals(type,tr.getEventType(type.getType_id()));
        type.setPercetage(.5);
        type = tr.updateEventType(type);
        assertNotNull(type);
        type = tr.deleteEventType(type);
        assertNotNull(type);
    }

    @Test
    public void department(){
        Department d = new Department("abc");
        dr.addDepartment(d);
        assertNotNull(dr.getAllDepartments());
        assertEquals(d,dr.getDepartment(d.getDepartment_id()));
        d.setDepartmentname("123");
        d = dr.updateDepartment(d);
        assertNotNull(d);
        d = dr.deleteDepartment(d);
        assertNotNull(d);
    }

    @Test
    public void getAllasEmployee(){
        List<Event> events = es.getAllEventsByUserId(8);
        assertNotNull(events);
    }
    @Test
    public void getAllasHead(){
        List<Event> events = es.getAllEventsByUserId(3);
        assertNotNull(events);
    }
    @Test
    public void getAllasBenCo(){
        List<Event> events = es.getAllEventsByUserId(1);
        assertNotNull(events);
    }


    @Test
    public void createEvent(){
        Event e = es.addEvent(createEvent);
        createEvent.setEvent_id(e.getEvent_id());
        assertEquals(createEvent, e);
        eventList.add(e.getEvent_id());
        e = es.updateEvent(e,1,createEvent.getEvent_id());
        assertEquals(9,e.getStatus().getStatus_id());
        er.deleteEvent(createEvent);
    }

    @Test
    public void getEvent(){
        Event e = es.getEvent(1);
        assertNotNull(e);
        List<Event> events = er.getAllEventsByStatus(1);
        assertNotNull(events);
    }

    @Test
    public void getAllEvent(){
        List<Event> e = es.getAllEvents();
        assertNotNull(e);
    }

    @Test
    public void deleteEvent(){
        er.addEvent(deleteEvent);
        Event e = es.deleteEvent(3,deleteEvent.getEvent_id());
        assertNotNull(e);
    }

    @Test
    public void createLogin(){
        Login login = new Login("CS","CS123");
        login = ls.checkLogin(login);
        User u = ls.getUserByLogin(login);
        assertNotNull(u);
        login.setUsername("123");
        login = ls.addLogin(login);
        List<Login> logins = ls.getAllLogins();
        assertNotNull(logins);
        Login l = ls.getLogin(1);
        assertNotNull(l);
        l = ls.checkLogin(login);
        assertNotNull(l);
        l.setUsername("123");
        l = ls.updateLogin(l);
        assertNotNull(l);
        l = ls.deleteLogin(l.getLog_id());
        assertNotNull(l);
    }

    @Test
    public void createUser(){
        User u = us.getUser(1);
        User head = us.getUserByDId(4);
        assertEquals(u,head);
    }
}
