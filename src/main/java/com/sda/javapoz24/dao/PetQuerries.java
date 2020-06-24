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

    String INSERT_PET = "INSERT INTO `pets`\n" +
            "(`name`, `age`, `ownerName`, `weight`, `pureRace`, `race`)\n" +
            "VALUES (?, ?, ?, ?, ?, ?);";

    String SELECT_PETS = "SELECT * FROM `pets`;";

    String DELETE_PET = "DELETE FROM pets WHERE `id`=?;";

    // auxiliary READ for specific, required UPDATE demands
    String SELECT_PET_BY_ID = "SELECT\n" +
            "`name`,\n" +
            "`age`,\n" +
            "`ownerName`,\n" +
            "`weight`,\n" +
            "`pureRace`,\n" +
            "`race`\n" +
            "FROM pets\n" +
            "WHERE id=?;";

    String UPDATE_PET = "UPDATE pets\n" +
            "SET\n" +
            "`name` = ?,\n" +
            "`age` = ?,\n" +
            "`ownerName` = ?,\n" +
            "`weight` = ?,\n" +
            "`pureRace` = ?,\n" +
            "`race` = ?\n" +
            "WHERE `id` = ?;";
}
