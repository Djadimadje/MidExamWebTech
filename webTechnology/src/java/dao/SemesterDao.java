/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.List;
import model.Semester;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jms
 */
public class SemesterDao {
    public void addSemester(Semester sign) {
        // create session
        Session ss = HibernateUtil.getSessionFactory().openSession();
        //create transaction
        Transaction tr = ss.beginTransaction();
        // save Login
        ss.save(sign);
        tr.commit();
        ss.close();
    }
     public List<Semester> getAllSemesters() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Semester.class);
            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Semester getSemesterById(int semesterId) {
        // Open a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Semester semester = null;
        
        try {
            // Begin a transaction
            session.beginTransaction();
            
            // Retrieve the Semester entity with the given ID
            semester = (Semester) session.get(Semester.class, semesterId);
            
            // Commit the transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            // Rollback the transaction if an exception occurs
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace(); // Handle or log the exception as needed
        } finally {
            // Close the session
            session.close();
        }
        
        return semester;
    }
    public void deleteSemesterById(String id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Semester student = (Semester) session.get(Semester.class, id);
            if (student != null) {
                session.delete(student);
                transaction.commit();
            } else {
                throw new Exception("Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e; // Re-throw the exception to be handled in the servlet
        } finally {
            session.close();
        }
    }
    public void updateSemester(Semester student) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e; // Re-throw the exception to be handled in the servlet
        } finally {
            session.close();
        }
    }

}