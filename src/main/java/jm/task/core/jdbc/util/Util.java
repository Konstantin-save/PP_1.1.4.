package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
//    public static Session HibernateUtil;
//    private static SessionFactory sessionFactory;
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration();
//
//                Properties settings = new Properties();
//                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_db?useSSL=false");
//                settings.put(Environment.USER, "root");
//                settings.put(Environment.PASS, "root");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//
//                settings.put(Environment.SHOW_SQL, "true");
//
//                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//
//                configuration.setProperties(settings);
//
//                configuration.addAnnotatedClass(User.class);
//
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties()).build();
//
//                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return sessionFactory;
//    }
//}
//}
    private static Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";   //url моей базы данных поставить
    private static final String DB_USERNAME = "root";   //имя

    private static final String DB_PASSWORD = "123456";    // пароль



    public static Connection getConnection() {   //класс возвращает коннекшен к базе данных
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("connection ok");
        } catch (SQLException e) {
           e.printStackTrace();
            System.out.println("connection Error");
        }
        return connection;
    }


    public static Util getInstance() {

        return null;
    }
}
