package me.ran;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/springdata";
        String username = "ranran";
        String password = "ranrantest09";

        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection created: " + connection);
//            String sql = "create table account (id int, username varchar(256), password varchar(256));";
            String sql = "insert into account values (2, 'youngran2', 'youngransecret2');";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.execute();
            }

        }

    }
}
