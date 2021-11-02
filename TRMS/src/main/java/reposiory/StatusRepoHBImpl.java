package reposiory;

import models.Status;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.MyLogger;

import java.util.List;

public class StatusRepoHBImpl implements StatusRepo{
    @Override
    public Status addStatus(Status input) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            input.setStatus_id((int) session.save(input));
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
    public List<Status> getAllStatuss() {
        Session session = HibernateUtil.getSession();
        List<Status> Statuss = null;
        try {
            Statuss = session.createQuery("FROM Status").list();
        }catch (HibernateException e){
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        }finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return Statuss;
    }

    @Override
    public Status getStatus(int id) {
        Session session = HibernateUtil.getSession();
        Status u = null;
        try {
            u = session.get(Status.class, id);
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
    public Status updateStatus(Status change) {
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
    public Status deleteStatus(Status input) {
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