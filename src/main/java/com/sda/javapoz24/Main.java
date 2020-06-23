package com.sda.javapoz24;

import com.sda.javapoz24.dao.MysqlDBConnection;
import com.sda.javapoz24.dao.PetDao;

public class Main {
    public static void main(String[] args) {
        PetDao dao = new PetDao(new MysqlDBConnection());
    }
}
