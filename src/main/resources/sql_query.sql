DROP TABLE if exists `user`;

CREATE TABLE `test`.`user` (
  `id` INT(8) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(25) NULL COMMENT '',
  `age` INT NULL COMMENT '',
  `isAdmin` BIT(1) NULL DEFAULT 0 COMMENT '',
  `createdDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


INSERT INTO `user` (`name`,`age`,`isAdmin`) VALUES ("Elmo",47,0),("Kasimir",31,1),("Alfonso",45,0),("Aidan",99,1),("Kareem",29,1),("Tyrone",42,1),("Akeem",20,0),("Oren",105,1),("Wesley",68,1),("Upton",45,1);
INSERT INTO `user` (`name`,`age`,`isAdmin`) VALUES ("Lyle",17,1),("Kennedy",9,0),("Aquila",27,0),("Dennis",109,0),("Palmer",78,0),("Ezekiel",66,0),("Alfonso",55,1),("Mohammad",36,0),("Kermit",81,1),("Myles",58,0);
INSERT INTO `user` (`name`,`age`,`isAdmin`) VALUES ("Yuli",69,1),("Ethan",64,0),("Macaulay",8,0),("Zachary",103,1),("Damian",113,1),("Malachi",34,0),("Cole",32,0),("Jasper",91,1),("Hyatt",87,1),("Chancellor",96,0);
INSERT INTO `user` (`name`,`age`,`isAdmin`) VALUES ("Jonah",37,0),("Vaughan",20,0),("Jarrod",18,0),("Trevor",113,0),("Jermaine",42,0),("Alan",113,0),("Kevin",34,0),("Jonah",19,1),("Bernard",101,0),("Stephen",42,0);
INSERT INTO `user` (`name`,`age`,`isAdmin`) VALUES ("Tucker",52,0),("Knox",113,0),("Wallace",119,0),("Vladimir",80,1),("Basil",24,0),("Wyatt",21,0),("Keegan",5,1),("Omar",18,0),("Nehru",13,0),("Fritz",12,1);
INSERT INTO `user` (`name`,`age`,`isAdmin`) VALUES ("Axel",100,0),("Ashton",35,1),("Wyatt",93,0),("Harding",10,1),("Ethan",99,0),("Herrod",4,0),("Jin",80,0),("Colt",96,0),("Russell",68,0),("Plato",78,1);
INSERT INTO `user` (`name`,`age`,`isAdmin`) VALUES ("Tyler",44,1),("Uriah",105,1),("Oscar",87,1),("Lucius",90,0),("Paki",27,0),("Ray",81,1),("Rashad",54,1),("Dexter",29,1),("Marsden",52,1),("Raymond",74,1);
INSERT INTO `user` (`name`,`age`,`isAdmin`) VALUES ("Merritt",95,1),("Brandon",24,0),("Mufutau",7,0),("Jackson",73,1),("Addison",16,0),("Upton",35,0),("Vladimir",4,0),("Yardley",116,1),("Roth",69,1),("Lars",41,0);
INSERT INTO `user` (`name`,`age`,`isAdmin`) VALUES ("Igor",118,1),("Cooper",74,0),("Tad",113,1),("Roth",63,1),("Julian",75,0),("Kenyon",23,0),("Walter",1,0),("Jason",19,1),("Anthony",92,1),("Josiah",3,0);
INSERT INTO `user` (`name`,`age`,`isAdmin`) VALUES ("Ira",18,0),("Paul",69,0),("Nehru",98,0),("Shad",5,1),("Gavin",82,1),("Oren",68,0),("Reed",70,1),("Edward",98,0),("Ryan",95,1),("Fulton",60,1);