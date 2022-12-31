CREATE TABLE IF NOT EXISTS beaches (
  id SERIAL,
  name varchar(250) NOT NULL,
  rating int NOT NULL,
  description varchar(250) NOT NULL,
  PRIMARY KEY (id)
);