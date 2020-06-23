package com.sda.javapoz24.dao;

public interface PetQuerries {
    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `pets` (\n" +
            "`id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
            "`name` VARCHAR(40),\n" +
            "`age` integer,\n" +
            "`ownerName` VARCHAR(40),\n" +
            "`weight` DOUBLE,\n" +
            "`pureRace` BOOLEAN,\n" +
            "`race` VARCHAR(40)\n" +
            ");";
}
