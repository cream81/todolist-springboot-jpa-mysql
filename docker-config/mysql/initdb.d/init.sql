DROP DATABASE IF EXISTS todolist;
CREATE DATABASE todolist;
USE todolist;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS todo;

CREATE TABLE user
(
  id int PRIMARY KEY AUTO_INCREMENT,
  username varchar(255) UNIQUE KEY,
  password varchar(255) NOT NULL,
  role varchar(255) NOT NULL DEFAULT 'user'
)DEFAULT CHARACTER
  SET=utf8;

CREATE TABLE todo
(
  id int PRIMARY KEY AUTO_INCREMENT,
  description varchar(255) NOT NULL,
  status varchar(255) NOT NULL,
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by varchar(255) REFERENCES user(role),
  last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  last_modified_by varchar(255) REFERENCES user(role),
  is_deleted tinyint(1) NOT NULL DEFAULT false
)DEFAULT CHARACTER
  SET=utf8;

  INSERT INTO user
    (username, password)
  VALUES
    ("user", "$2a$10$4aY672Pznbg68T8tiP2SjODC0GbLJ2pYjgqmbkhs8mjj31TTk.tD2");
  -- demo1
  
  INSERT INTO user
    (username, password, role)
  VALUES
    ("admin", "$2a$10$zcTxzeP0lkgBVZ8bhWfVpOYa.cQ4.I8Y/fuxkRXMtuqBo.nqbNeCy", "admin");
  -- demo2

  INSERT INTO todo
    (description, status, created_by, last_modified_by)
  VALUES
    ("内容1", "未着手", "user", "user");

  INSERT INTO todo
    (description, status, created_by, last_modified_by)
  VALUES
    ("内容2", "対応中", "admin", "admin");

  INSERT INTO todo
    (description, status, created_by, last_modified_by)
  VALUES
    ("内容3", "完了", "user", "admin");