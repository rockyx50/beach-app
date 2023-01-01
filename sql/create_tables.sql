DROP TABLE IF EXISTS beaches;
CREATE TABLE beaches (
  id SERIAL,
  name varchar(250) NOT NULL,
  rating int NOT NULL,
  description varchar(250) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO beaches(name,rating,description) VALUES
('Coco beach',4,'Test description.'),
('Long beach',2,'Test description.'),
('Wasaga beach',1,'Test description.');