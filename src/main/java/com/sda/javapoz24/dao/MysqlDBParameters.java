package com.sda.javapoz24.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MysqlDBParameters {
    private final static String PROPERTIES_PATH = "/jdbc.properties";

    private String username;
    private String password;
    private String dbName;
    private String host;
    private int port;

    static MysqlDBParameters loadFromResources() throws IOException {
        MysqlDBParameters dbParameters = new MysqlDBParameters();

        Properties properties = new Properties();
        InputStream stream = MysqlDBParameters.class.getResourceAsStream(PROPERTIES_PATH);

        if (stream != null) {
            properties.load(stream);

            dbParameters.setUsername(properties.getProperty("database.jdbc.username"));
            dbParameters.setPassword(properties.getProperty("database.jdbc.password"));
            dbParameters.setDbName(properties.getProperty("database.jdbc.dbname"));
            dbParameters.setHost(properties.getProperty("database.jdbc.host"));
            dbParameters.setPort(Integer.parseInt(properties.getProperty("database.jdbc.port")));
        }
        return dbParameters;
    }
}
