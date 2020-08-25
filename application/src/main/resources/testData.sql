INSERT INTO autoservice.Master (id, name, category, busy, orderId) values (5, 'Evgeniy', 3, 0, null);
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