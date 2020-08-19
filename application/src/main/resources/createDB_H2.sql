CREATE SCHEMA IF NOT EXISTS autoservice;
USE autoservice;

CREATE TABLE IF NOT EXISTS autoservice.Garage (
  id INT NOT NULL,
  adress VARCHAR(200) NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS autoservice.GaragePlace (
  id INT NOT NULL,
  type VARCHAR(50) NULL,
  area INT NULL,
  busy TINYINT(1) NOT NULL,
  garageId INT NOT NULL,
  PRIMARY KEY (id, garageId),
  CONSTRAINT fk_GaragePlace_Garage
    FOREIGN KEY (garageId)
    REFERENCES autoservice.Garage (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS autoservice.Orderr (
  id INT NOT NULL,
  submissionDate VARCHAR(100) NULL,
  startDate VARCHAR(100) NULL,
  endDate VARCHAR(100) NULL,
  kindOfWork VARCHAR(200) NULL,
  cost INT NULL,
  status VARCHAR(50) NULL,
  garagePlaceId INT NULL,
  garagePlaceGarageId INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_Order_GaragePlace1
    FOREIGN KEY (garagePlaceId , garagePlaceGarageId)
    REFERENCES autoservice.GaragePlace (id , garageId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS autoservice.Master (
  id INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  category INT NULL,
  busy TINYINT(1) NOT NULL,
  orderId INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_Master_Order1
    FOREIGN KEY (orderId)
    REFERENCES autoservice.Orderr (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

INSERT INTO autoservice.Master (id, name, category, busy) values (1, 'Evgeniy', 3, 0);
INSERT INTO autoservice.Master (id, name, category, busy) values (2, 'Alex', 2, 0);
INSERT INTO autoservice.Master (id, name, category, busy) values (3, 'Ivan', 5, 0);

INSERT INTO autoservice.Garage (id, adress) values (1, 'Orel-Moskovskaya-22');
INSERT INTO autoservice.Garage (id, adress) values (2, 'Orel-Naugorskaya-20');

INSERT INTO autoservice.GaragePlace (id, type, area, busy, garageId) values (1, 'Car lift', 8, 0, 1);
INSERT INTO autoservice.GaragePlace (id, type, area, busy, garageId) values (2, 'Pit', 12, 0, 1);
INSERT INTO autoservice.GaragePlace (id, type, area, busy, garageId) values (3, 'Car lift', 8, 0, 1);
INSERT INTO autoservice.GaragePlace (id, type, area, busy, garageId) values (4, 'Car lift', 8, 0, 1);

INSERT INTO autoservice.Orderr values (1, '2020-06-01 11:00:00.000', '2020-06-01 12:00:00.000', '2020-06-01 13:00:00.000', 'Oil change', 1000, 'ACCEPTED', 1, 1);
INSERT INTO autoservice.Orderr values (2, '2020-05-31 13:00:00.000', '2020-05-31 14:00:00.000', '2020-05-31 15:00:00.000', 'Tire fitting', 300, 'ACCEPTED', 2, 1);
INSERT INTO autoservice.Orderr values (3, '2020-05-31 10:00:00.000', '2020-05-31 11:00:00.000', '2020-05-31 12:00:00.000', 'Diagnostics', 500, 'ACCEPTED', 3, 1);





