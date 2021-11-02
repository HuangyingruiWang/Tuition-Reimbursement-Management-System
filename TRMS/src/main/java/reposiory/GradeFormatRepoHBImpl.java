package reposiory;

import models.GradeFormat;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.MyLogger;

import java.util.List;

public class GradeFormatRepoHBImpl implements GradeFormatRepo{
    @Override
    public GradeFormat addGradeFormat(GradeFormat input) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            input.setGrade_id((int) session.save(input));
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
    public List<GradeFormat> getAllGradeFormats() {
        Session session = HibernateUtil.getSession();
        List<GradeFormat> GradeFormats = null;
        try {
            GradeFormats = session.createQuery("FROM GradeFormat").list();
        }catch (HibernateException e){
            e.printStackTrace();
            MyLogger.logger.error("HibernateException");
        }finally {
            session.close();
        }
        MyLogger.logger.info("Execution completed");
        return GradeFormats;
    }

    @Override
    public GradeFormat getGradeFormat(int id) {
        Session session = HibernateUtil.getSession();
        GradeFormat u = null;
        try {
            u = session.get(GradeFormat.class, id);
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
    public GradeFormat updateGradeFormat(GradeFormat change) {
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
    public GradeFormat deleteGradeFormat(GradeFormat input) {
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