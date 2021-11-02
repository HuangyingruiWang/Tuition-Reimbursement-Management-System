package reposiory;

import models.Supervisor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import util.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class SupervisorRepoHBImpl implements SupervisorRepo{
    @Override
    public Supervisor addSupervisor(Supervisor input) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            input.setUser_id((int) session.save(input));
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
    public List<Supervisor> getAllUsersBySupervisor(int supervisor_id) {
        Session session = HibernateUtil.getSession();
        List<Supervisor> supervisors = new ArrayList<>();
        try {
            String hql = "FROM Supervisor WHERE supervisors_id = :super";
            Query q = session.createQuery(hql);
            q.setParameter("super", supervisor_id);
            List results = q.list();
            if(results.size() > 0){
                for(Object i : results){
                    supervisors.add((Supervisor)i);
                }
            }
        }catch (HibernateException e){
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        }finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return supervisors;
    }

    @Override
    public Supervisor getSupervisor(int id) {
        Session session = HibernateUtil.getSession();
        Supervisor s = null;
        try {
            s = session.get(Supervisor.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        } finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return s;
    }

    @Override
    public Supervisor updateSupervisor(Supervisor change) {
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
    public Supervisor deleteSupervisor(Supervisor input) {
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