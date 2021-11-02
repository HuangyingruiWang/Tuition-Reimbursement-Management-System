package reposiory;

import models.Login;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import util.MyLogger;

import java.util.List;

public class LoginRepoHBImpl implements LoginRepo{
    @Override
    public Login addLogin(Login input) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            input.setLog_id((int) session.save(input));
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
    public List<Login> getAllLogins() {
        Session session = HibernateUtil.getSession();
        List<Login> Logins = null;
        try {
            Logins = session.createQuery("FROM Login").list();
        }catch (HibernateException e){
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        }finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return Logins;
    }

    @Override
    public Login getLogin(int id) {
        Session session = HibernateUtil.getSession();
        Login u = null;
        try {
            u = session.get(Login.class, id);
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
    public Login getLoginByLogin(Login login) {
        Session session = HibernateUtil.getSession();
        Login l = null;
        String hql = "FROM Login WHERE username = :username AND password = :password";
        try {
            Query q = session.createQuery(hql);
            q.setParameter("username", login.getUsername());
            q.setParameter("password", login.getPassword());
            List results = q.list();
            if(results.size() > 0){ l = (Login)results.get(0); }
        } catch (HibernateException e) {
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        } finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return l;
    }

    @Override
    public Login updateLogin(Login change) {
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
    public Login deleteLogin(Login input) {
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