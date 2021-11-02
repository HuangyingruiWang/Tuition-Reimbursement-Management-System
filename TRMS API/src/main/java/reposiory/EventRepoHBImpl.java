package reposiory;

import models.Event;
import models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import util.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class EventRepoHBImpl implements EventRepo{

    @Override
    public Event addEvent(Event input) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            input.setEvent_id((int)session.save(input));
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return input;
    }

    @Override
    public Event getEvent(int id) {
        Session session = HibernateUtil.getSession();
        Event t = null;
        try {
            t = session.get(Event.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        } finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return t;
    }

    @Override
    public List<Event> getAllEvents() {
        Session session = HibernateUtil.getSession();
        List<Event> events = null;
        try {
            events = session.createQuery("FROM Event").list();
        } catch (HibernateException e) {
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        } finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return events;
    }

    @Override
    public List<Event> getAllEventsByStatus(int code) {
        Session session = HibernateUtil.getSession();
        List<Event> events = new ArrayList<>();
        try {
            String hql = "FROM Event WHERE status.status_id = :status";
            Query q = session.createQuery(hql);
            q.setParameter("status", code);
            List results = q.list();
            if (results != null){
                for (Object o: results) {
                    events.add((Event)o);
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        } finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return events;
    }

    @Override
    public List<Event> getAllEventsByUser(User user) {
        Session session = HibernateUtil.getSession();
        List<Event> events = new ArrayList<>();
        try {
            String hql = "FROM Event WHERE user_fk = :user";
            Query q = session.createQuery(hql);
            q.setParameter("user", user);
            List results = q.list();
            if (results != null){
                for (Object o: results) {
                    events.add((Event)o);
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        } finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return events;
    }

    @Override
    public Event updateEvent(Event change) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(change);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return change;
    }

    @Override
    public Event deleteEvent(Event input) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(input);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return input;
    }

    @Override
    public long timeupdate() {
        return System.currentTimeMillis()/1000;
    }


}
