/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jms
 */
public class SignUpDao {
    public void signup(User sign) {
        // create session
        Session ss = HibernateUtil.getSessionFactory().openSession();
        //create transaction
        Transaction tr = ss.beginTransaction();
        // save Login
        ss.save(sign);
        tr.commit();
        ss.close();
    }
    public List<User> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
}
