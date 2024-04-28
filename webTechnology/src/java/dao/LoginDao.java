package dao;

import model.User;

import dao.HibernateUtil;
import org.hibernate.*;

public class LoginDao {

    public String authenticateUser(String email, String password) {
        String role = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM User WHERE email = :email AND password = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            User user = (User) query.uniqueResult();
            if (user != null) {
                role = user.getRole();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return role;
    }
}
