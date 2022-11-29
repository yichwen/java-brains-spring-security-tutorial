-- users
INSERT INTO USERS (username, password, enabled)
VALUES('user', 'user', true);
INSERT INTO USERS (username, password, enabled)
VALUES('admin', 'admin', true);
-- authorities
INSERT INTO AUTHORITIES (username, authority)
VALUES('user', 'ROLE_USER');
INSERT INTO AUTHORITIES (username, authority)
VALUES('admin', 'ROLE_ADMIN');
