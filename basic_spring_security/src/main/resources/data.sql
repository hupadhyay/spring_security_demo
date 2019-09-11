INSERT INTO users (username, password, enabled)
	VALUES ('himanshu', 'himserver', true);
	
INSERT INTO users (username, password, enabled)
	VALUES ('hrishik', 'himserver', true);
	
INSERT INTO authorities (username, authority)
	VALUES ('himanshu', 'ROLE_USER');
	
INSERT INTO authorities (username, authority)
	VALUES ('hrishik', 'ROLE_ADMIN');