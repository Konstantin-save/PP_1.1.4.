package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction = null;
    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {                                  //СОЗДАНИЕ ТАБЛИЦЫ
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                            "(id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(255), last_name VARCHAR(255), age TINYINT)")
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();                                    //откатить транзакцию
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {                                        //УДАЛЕНИЕ ТАБЛИЦЫ
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users")
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();                                    //откатить транзакцию
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {        //ДОБАВЛЕНИЕ USER
        try (Session session = sessionFactory.openSession()) {            //созд сессию(подкл к базе) и получаем ее из Factory(фабрики сесий)
            transaction = session.beginTransaction();                     //открывается транзакция
            User user = new User(name,lastName,age);                                       //создаем объект класса User
            session.save(user);                                           //сохр объект User в базу
            transaction.commit();                                         //закрываем транзакцию
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();                                    //откатить транзакцию
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {                                   //УДАЛЯЕМ USER ПО ID
        try (Session session = sessionFactory.openSession()) {            //созд сессию(подкл к базе) и получаем ее из Factory(фабрики сесий)
            transaction = session.beginTransaction();                     //открывается транзакция
            User user = session.get(User.class, id);              //получаем работника через id
            session.delete(user);                                          //удаляем работника
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();                                    //откатить транзакцию
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {                                      //ПОЛУЧИТЬ СПИСОК ВСЕХ USER
        List user = null;
        try (Session session = sessionFactory.openSession()) {            //созд сессию(подкл к базе) и получаем ее из Factory(фабрики сесий)
            transaction = session.beginTransaction();                     //открывается транзакция
            user = session.createQuery("from User")                   //запрос HQL-работает не с табл, а с классом
                    .getResultList();
            transaction.commit();                                        //закрываем транзакцию
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();                                     //откатить транзакцию
            }
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void cleanUsersTable() {                                        //ОЧИСТКА ТАБЛИЦЫ
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete User")
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();                                    //откатить транзакцию
            }
            e.printStackTrace();
        }
    }
}

