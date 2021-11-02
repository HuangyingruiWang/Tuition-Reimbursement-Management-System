package reposiory;

import models.Head;
import models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.MyLogger;

import java.util.List;

public class HeadRepoHBImpl implements HeadRepo{
    @Override
    public Head addHead(Head input) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(input);
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
    public List<Head> getAllHeads() {
        Session session = HibernateUtil.getSession();
        List<Head> Heads = null;
        try {
            Heads = session.createQuery("FROM Head").list();
        }catch (HibernateException e){
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        }finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return Heads;
    }

    @Override
    public User getHeadByDepartmentId(int id) {
        Session session = HibernateUtil.getSession();
        Head h;
        User u = null;
        try {
            h = session.get(Head.class,id);
            if (h != null){
                u = h.getDepartmenthead();
            }
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
    public Head updateHead(Head change) {
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
    public Head deleteHead(Head input) {
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