package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//            Util util = new Util();               - Тест соединенния с базой
//            Util.getConnection();

        UserService us = new UserServiceImpl();

        us.createUsersTable();

        us.saveUser("Name1", "LastName1", (byte) 20);
        us.saveUser("Name2", "LastName2", (byte) 25);
        us.saveUser("Name3", "LastName3", (byte) 31);
        us.saveUser("Name4", "LastName4", (byte) 38);

        us.removeUserById(1);
        System.out.println(us.getAllUsers());
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}

