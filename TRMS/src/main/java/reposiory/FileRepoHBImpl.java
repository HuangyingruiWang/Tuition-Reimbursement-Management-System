//package reposiory;
//
//import models.File;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import util.HibernateUtil;
//import util.MyLogger;
//
//import java.util.List;
//
//public class FileRepoHBImpl implements FileRepo{
//
//    @Override
//    public File addFile(File input) {
//        Session session = HibernateUtil.getSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            input.setFile_id((int)session.save(input));
//            tx.commit();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            MyLogger.logger.error("HibernateException");
//            if(tx != null) tx.rollback();
//            return null;
//        } finally {
//            session.close();
//        }
//        MyLogger.logger.info("Execution completed");
//        return input;
//    }
//
//    @Override
//    public File getFile(int id) {
//        Session session = HibernateUtil.getSession();
//        File t = null;
//        try {
//            t = session.get(File.class, id);
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            MyLogger.logger.error("HibernateException");
//        } finally {
//            session.close();
//        }
//        MyLogger.logger.info("Execution completed");
//        return t;
//    }
//
//    @Override
//    public List<File> getAllFiles() {
//        Session session = HibernateUtil.getSession();
//        List<File> Files = null;
//        try {
//            Files = session.createQuery("FROM File").list();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            MyLogger.logger.error("HibernateException");
//        } finally {
//            session.close();
//        }
//        MyLogger.logger.info("Execution completed");
//        return Files;
//    }
//
//    @Override
//    public File updateFile(File change) {
//        Session session = HibernateUtil.getSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.update(change);
//            tx.commit();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            MyLogger.logger.error("HibernateException");
//            if(tx != null) tx.rollback();
//            return null;
//        } finally {
//            session.close();
//        }
//        MyLogger.logger.info("Execution completed");
//        return change;
//    }
//
//    @Override
//    public File deleteFile(File input) {
//        Session session = HibernateUtil.getSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.delete(input);
//            tx.commit();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            MyLogger.logger.error("HibernateException");
//            if(tx != null) tx.rollback();
//            return null;
//        } finally {
//            session.close();
//        }
//        MyLogger.logger.info("Execution completed");
//        return input;
//    }
//
//}
