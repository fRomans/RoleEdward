package repository.dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import repository.config.DBHelper;

import javax.persistence.NoResultException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        this.sessionFactory = createSessionFactory(configuration);
    }

    @Override
    public void create(User model) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(model);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public User readById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        } catch (NoResultException e) {
            return null;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User readByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User u where u.email= :email", User.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> readAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (NoResultException e) {
            return null;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(User model) {
        if (readById(model.getId()) != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.update(model);
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) {
        User user = readById(id);
        if (user != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.delete(user);
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
