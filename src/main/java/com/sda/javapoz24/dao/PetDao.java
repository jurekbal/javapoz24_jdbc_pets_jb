package com.sda.javapoz24.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PetDao {
    private MysqlDBConnection connector;

    public PetDao(MysqlDBConnection connector) {
        this.connector = connector;

        CreateDatabaseAndTable();
    }

    private void CreateDatabaseAndTable() {
        try (Connection connection = connector.createConnection()){
            PreparedStatement statement = connection.prepareStatement(PetQuerries.CREATE_TABLE);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
