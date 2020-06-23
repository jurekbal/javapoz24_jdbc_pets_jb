package com.sda.javapoz24.dao;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlDBConnection {
    private MysqlDBParameters parameters;
    private MysqlDataSource dataSource; // z com.mysql.cj.jdbc.MysqlDataSource;

    public MysqlDBConnection() {
        prepareParameters();
    }

    private void prepareParameters() {
        try {
            parameters = MysqlDBParameters.loadFromResources();
        } catch (IOException e) {
            System.err.println("Błąd : " + e.getMessage());
            System.exit(9); // nasz kod błędu np. 9
        }

        dataSource = new MysqlDataSource();
        dataSource.setUser(parameters.getUsername());
        dataSource.setPassword(parameters.getPassword());
        dataSource.setDatabaseName(parameters.getDbName());
        dataSource.setServerName(parameters.getHost());
        dataSource.setPort(parameters.getPort());

        try {
            dataSource.setCreateDatabaseIfNotExist(true);
            dataSource.setServerTimezone("Europe/Warsaw");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection createConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
