/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.validation.ConstraintViolationException;
import model.Academic;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AcademicDAO {
       public void addAcademic(Academic course) {
        // create session
        Session ss = HibernateUtil.getSessionFactory().openSession();
        //create transaction
        Transaction tr = ss.beginTransaction();
        // save Login
        ss.save(course);
        tr.commit();
        ss.close();
    }
        public Academic getCourseById(int semesterId) {
        // Open a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Academic semester = null;
        
        try {
            // Begin a transaction
            session.beginTransaction();
            
            // Retrieve the Semester entity with the given ID
            semester = (Academic) session.get(Academic.class, semesterId);
            
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
public void deleteCourseById(int courseId) {
    // Open a Hibernate session
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;
    
    try {
        // Begin a transaction
        transaction = session.beginTransaction();
        
        // Retrieve the Course entity with the given ID
        Academic course = (Academic) session.get(Academic.class, courseId);
        
        // Delete the course
        if (course != null) {
            session.delete(course);
            transaction.commit();
        }
    } catch (HibernateException e) {
        // Rollback the transaction if a Hibernate exception occurs
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace(); // Handle or log the exception as needed
        
        // Check if the exception is a ConstraintViolationException (SQL error)
        if (e.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
            // Print the constraint violation message
            System.out.println("Constraint Violation Message: " + cve.getMessage());
        }
    } finally {
        // Close the session
        session.close();
    }
}
       public void updateCourse(Academic updatedCourse) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Retrieve the Course entity from the database with the given ID
            Academic course = (Academic) session.get(Academic.class, updatedCourse.getId());

            if (course != null) {
                // Update the properties of the retrieved course entity with the updated course object
                course.setName(updatedCourse.getName());
                course.setType(updatedCourse.getType());

                // Save the changes by updating the entity in the database
                session.update(course);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle or log the exception as needed
        } finally {
            session.close();
        }
    }
         public List<Academic> getAllCourses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Academic.class);
            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
         }}