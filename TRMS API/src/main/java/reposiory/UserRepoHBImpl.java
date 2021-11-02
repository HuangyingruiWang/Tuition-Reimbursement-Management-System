package reposiory;

import models.Department;
import models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import util.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class UserRepoHBImpl implements UserRepo{
    @Override
    public User addUser(User input) {
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
    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSession();
        List<User> users = null;
        try {
            users = session.createQuery("FROM User").list();
        }catch (HibernateException e){
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        }finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return users;
    }
    @Override
    public List<User> getAllUsersByDepartment(Department department) {
        Session session = HibernateUtil.getSession();
        List<User> users = new ArrayList<>();
        try {
            String hql = "FROM User WHERE department = :depar";
            Query q = session.createQuery(hql);
            q.setParameter("depar", department);
            List results = q.list();
            if(results.size() > 0){
                for(Object u : results){
                    users.add((User)u);
                }
            }
        }catch (HibernateException e){
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        }finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return users;
    }

    @Override
    public User getUser(int id) {
        Session session = HibernateUtil.getSession();
        User u = null;
        try {
            u = session.get(User.class, id);
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
    public User getUserByLoginId(int login_id) {
        Session session = HibernateUtil.getSession();
        User u = null;
        try {
            String hql = "FROM User WHERE log_fk = :login_id";
            Query q = session.createQuery(hql);
            q.setParameter("login_id", login_id);
            List results = q.list();
            if(results.size() > 0) { u = (User) results.get(0); }
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
    public User updateUser(User change) {
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
    public User deleteUser(User input) {
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
