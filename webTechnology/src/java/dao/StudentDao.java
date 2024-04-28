/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author jms
 */
public class StudentDao {
    public void addStudent(Student student) {
        // create session
        Session ss = HibernateUtil.getSessionFactory().openSession();
        //create transaction
        Transaction tr = ss.beginTransaction();
        // save Login
        ss.save(student);
        tr.commit();
        ss.close();
    }
    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Student.class);
            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public void deleteStudentById(String id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Student student = (Student) session.get(Student.class, id);
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
     public Student getStudentById(String studentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Student.class);
            criteria.add(Restrictions.eq("id", studentId));
            return (Student) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
     public void updateStudent(Student student) throws Exception {
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