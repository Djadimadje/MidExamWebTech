/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import model.StudentReg;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class StudentRegDao {

    public void addStudentReg(StudentReg studentReg) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(studentReg);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace(); // Handle or log the exception as needed
        } finally {
            session.close();
        }
    }
     public void updateStudent(StudentReg updatedStudent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(updatedStudent);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteStudentById(int studentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            StudentReg student = session.get(StudentReg.class, regId);
            if (student != null) {
                session.delete(student);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public StudentReg getStudentById(int studentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        StudentReg student = null;
        try {
            student = session.get(StudentReg.class, regId);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return student;
    }

    public List<StudentReg> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<StudentReg> students = null;
        try {
            Criteria criteria = session.createCriteria(StudentReg.class);
            students = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return students;
    }
    
     
}
