
use reservation;

CREATE TABLE ROLE 
(
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(20),
PRIMARY KEY (ID)
)

CREATE table user_role(
user_id int,
role_id int,
FOREIGN KEY (user_id)
REFERENCES user(id),
FOREIGN KEY (role_id)
REFERENCES role(id)
)

INSERT INTO role values(1,'ADMIN');

INSERT INTO user_role values(1,1);

SELECT * FROM user;

SELECT * FROM role;

SELECT * FROM user_role;

drop table role

drop table user_role



