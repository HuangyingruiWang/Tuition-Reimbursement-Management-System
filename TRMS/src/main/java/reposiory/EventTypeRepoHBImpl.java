package reposiory;

import models.EventType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.MyLogger;

import java.util.List;

public class EventTypeRepoHBImpl implements EventTypeRepo{
    @Override
    public EventType addEventType(EventType input) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            input.setType_id((int) session.save(input));
            tx.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
            if(tx != null) tx.rollback();
            return null;
        }finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return input;
    }

    @Override
    public List<EventType> getAllEventTypes() {
        Session session = HibernateUtil.getSession();
        List<EventType> EventTypes = null;
        try {
            EventTypes = session.createQuery("FROM EventType").list();
        }catch (HibernateException e){
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        }finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return EventTypes;
    }

    @Override
    public EventType getEventType(int id) {
        Session session = HibernateUtil.getSession();
        EventType u = null;
        try {
            u = session.get(EventType.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        } finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return u;
    }

    @Override
    public EventType updateEventType(EventType change) {
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
    public EventType deleteEventType(EventType input) {
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
}