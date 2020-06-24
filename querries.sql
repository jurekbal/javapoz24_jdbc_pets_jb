CREATE DATABASE IF NOT EXISTS jpoz24_pets_jdbc;

CREATE TABLE IF NOT EXISTS `pets` (
`id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40),
`age` integer,
`ownerName` VARCHAR(40),
`weight` DOUBLE,
`pureRace` BOOLEAN,
`race` VARCHAR(40)
);

-- insert a record into table
INSERT INTO `pets`
(`name`, `age`, `ownerName`, `weight`, `pureRace`, `race`)
VALUES (?, ?, ?, ?, ?, ?);

-- select all
SELECT * FROM `pets`;

-- delete entry by id
DELETE FROM pets WHERE `id`=?;

-- update entry by id
UPDATE pets
SET
`name` = ?,
`age` = ?,
`ownerName` = ?,
`weight` = ?,
`pureRace` = ?,
`race` = ?
WHERE `id` = ?;

-- select one record by id (no id in result)
SELECT
`name`,
`age`,
`ownerName`,
`weight`,
`pureRace`,
`race`
FROM pets
WHERE id=?;

