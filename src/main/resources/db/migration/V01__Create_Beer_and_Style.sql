CREATE TABLE beerstyle(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL
) ENGINE=innoDB DEFAULT CHARSET=utf8;

CREATE TABLE beer(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	sku VARCHAR(50) NOT NULL,
	name VARCHAR(100) NOT NULL,
	description TEXT NOT NULL,
	value DECIMAL(10, 2) NOT NULL,
	alcohol_percent DECIMAL(10, 2) NOT NULL,
	commission DECIMAL(10, 2) NOT NULL,
	inventory INTEGER NOT NULL,
	origin VARCHAR(100) NOT NULL,
	taste VARCHAR(50) NOT NULL,
	style_id BIGINT(20) NOT NULL,
	FOREIGN KEY (style_id) REFERENCES beerstyle(id)
) ENGINE=innoDB DEFAULT CHARSET=utf8;

INSERT INTO beerstyle (name) VALUES("Amber Lager");
INSERT INTO beerstyle (name) VALUES("Dark Lager");
INSERT INTO beerstyle (name) VALUES("Pale Lager");
INSERT INTO beerstyle (name) VALUES("Pilsen");
INSERT INTO beerstyle (name) VALUES("IPA");







	