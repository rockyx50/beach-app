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


DROP TABLE IF EXISTS beachList;
CREATE TABLE beachList(
   id SERIAL,
   State_Code     VARCHAR(250) NOT NULL
  ,County         VARCHAR(250) NOT NULL
  ,Beach_Name     VARCHAR(250) NOT NULL
  ,Monitored      VARCHAR(250)
  ,Not_Monitored  VARCHAR(250)
  ,Tier_Rank      INTEGER  NOT NULL
  ,Comments       VARCHAR(250)
  ,BeachLength_Mi FLOAT NOT NULL
  ,BeachLength_km float NOT NULL
  ,Rating         FLOAT NOT NULL
  ,PRIMARY KEY(id)
);

DROP TABLE IF EXISTS beach_history;
CREATE TABLE beach_history (
  entry_id SERIAL,
  beach_id int NOT NULL,
  beach_name varchar(250) NOT NULL,
  rating int NOT NULL,
  "user" varchar(250) NOT NULL,
  rating_date DATE NOT NULL DEFAULT CURRENT_DATE,
  PRIMARY KEY (entry_id),
  CONSTRAINT fk_beach_id FOREIGN KEY(beach_id) REFERENCES beachlist(id)
);