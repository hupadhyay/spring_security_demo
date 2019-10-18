CREATE TABLE users(
      username VARCHAR(50) NOT NULL PRIMARY KEY,
      password VARCHAR(150) NOT NULL,
      enabled BOOLEAN NOT NULL);

CREATE TABLE authorities (
      username VARCHAR(50) NOT NULL,
      authority VARCHAR(50) NOT NULL,
      CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username));
  
CREATE UNIQUE INDEX ix_auth_username ON authorities (username,authority);

CREATE TABLE ref_token (
		token VARCHAR(100) NOT NULL PRIMARY KEY,
		username VARCHAR(50) NOT NULL,
		millies BIGINT NOT NULL);