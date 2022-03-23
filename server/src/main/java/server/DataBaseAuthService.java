package server;

import java.sql.*;
import java.util.Objects;

public class DataBaseAuthService implements AuthService {

    private static Connection connection;
    private static Statement stmt;


    @Override
    public String getNicknameByLoginAndPassword(String login, String password) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT login, password FROM Subscribers WHERE login = '" + login + "' AND password = '" + password + "';");
        while (rs.next()) {
            if (Objects.equals(rs.getString("login"), login) & Objects.equals(rs.getString("password"), password)) {
                rs = stmt.executeQuery("SELECT name FROM Subscribers WHERE login = '" + login + "' AND password = '" + password + "';");
                return rs.getString("name");
            }
        }
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return false;
    }

    public void swapName(String oldName, String newName) throws SQLException {
         stmt.executeUpdate("UPDATE Subscribers SET name = '" + newName +"' WHERE name = '"+ oldName + "';");
    }

    public void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:chatty.db");
        stmt = connection.createStatement();
    }

    public void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
