package com.senla.courses.autoservice.dao.jdbcdao;

import com.lib.dicontainer.annotations.Singleton;
import com.lib.utils.ConsoleHelper;
import com.lib.utils.PropertyUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

@Singleton
public class DbJdbcConnector {

    private static Connection connection;

    private final String CONNECTION_PROPERTIES = "C:\\Users\\slone_000\\IdeaProjects\\sergey_ashikhmin\\application\\src\\main\\resources\\db.properties";
    private static String url;
    private static String username;
    private static String password;
    private static String driver;

    public DbJdbcConnector() {
        loadConnectionProperties();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
            }
        }
        return connection;
    }

    private void loadConnectionProperties() {
        PropertyUtil.loadConfig(CONNECTION_PROPERTIES);
        Map<String, String> properties = PropertyUtil.getProperties();
        this.url = properties.get("url");
        this.username = properties.get("username");
        this.password = properties.get("password");
        this.driver = properties.get("driver");
    }
}
