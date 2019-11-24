--
-- 	Group 91 
--
--  First drop any existing tables. Any errors are ignored.
--


drop table Customers cascade constraints;
drop table Rentals cascade constraints;
drop table Reservations cascade constraints;
drop table Returns cascade constraints;
drop table Vehicles cascade constraints;
drop table VehicleTypes cascade constraints;


--
-- Adding each table.
--
CREATE TABLE Customers
(
    dlicense  varchar2(7) PRIMARY KEY,
    cellphone number(10) UNIQUE,
    name      varchar2(30),
    address   varchar2(70)
);

CREATE TABLE VehicleTypes
(
    vtname   varchar2(30) PRIMARY KEY,
    features varchar2(200),
    wrate    number(3),
    drate    number(3),
    hrate    number(3),
    wirate   number(3),
    dirate   number(3),
    hirate   number(3),
    krate    number(3)
);

CREATE TABLE Reservations
(
    confNo       number(7) PRIMARY KEY,
    vtname       varchar2(30),
    dlicense     varchar2(7),
    fromDateTime date,
    toDateTime   date,
    city         varchar(70),
    location     varchar(70),
    foreign key (vtname) REFERENCES VehicleTypes,
    foreign key (dlicense) REFERENCES Customers
);

CREATE TABLE Vehicles
(
    vlicense varchar2(7) PRIMARY KEY,
    make     varchar2(15),
    model    varchar2(15),
    year     number(4),
    color    varchar2(15),
    odometer number(5),
    status   varchar2(15),
    vtname   varchar2(30),
    location varchar(30),
    city     varchar(30),
    foreign key (vtname) REFERENCES VehicleTypes
);

CREATE TABLE Rentals
(
    rid          number(8) PRIMARY KEY,
    vlicense     varchar2(7),
    dlicense     varchar2(7),
    fromDateTime date,
    toDateTime   date,
    odometer     number(5),
    cardName     varchar2(30),
    cardNo       number(16),
    expDate      char(5),
    confNo       number(7),
    foreign key (vlicense) REFERENCES Vehicles,
    foreign key (dlicense) REFERENCES Customers,
    foreign key (confNo) REFERENCES Reservations
);

CREATE TABLE Returns
(
    rid      number(8) PRIMARY KEY,
    dateTime date,
    odometer number(5),
    fullTank number(1),
    value    number(5),
    foreign key (rid) REFERENCES Rentals
);

--
-- Adding some data as a starting point.
--

INSERT INTO Customers
VALUES ('1982451', 6046885225, 'Kevin Smith', '3308 Ash St Vancouver BC V5Z 3E3');
INSERT INTO Customers
VALUES ('0923443', 6046892577, 'Zhen Zhong', '122 Walter Hardwick Ave 305 Vancouver BC V5Y 0C9');
INSERT INTO Customers
VALUES ('4238280', 6047313240, 'Miguel Romero', '2485 Broadway W 414 Vancouver BC V6K 2E8');


INSERT INTO Customers
VALUES ('0000111', 6045758167, 'Dean Thomas', '1487 27th Ave E Vancouver BC V5N 2W6');
INSERT INTO Customers
VALUES ('0000222', 7788084287, 'Seamus Finnegan', '1949 Comox St 305 Vancouver BC V6G 1R7');
INSERT INTO Customers
VALUES ('0000333', 7788084289, 'Parvati Patil', '5980 Battison St Vancouver BC V5R 4M8');
INSERT INTO Customers
VALUES ('0000444', 6048612403, 'Padma Patil', '5980 Battison St Vancouver BC V5R 4M8');
INSERT INTO Customers
VALUES ('0000555', 6045970234, 'Pansy Parkinson', '1410 Tolmie St Vancouver BC V6R 4B3');
INSERT INTO Customers
VALUES ('0000666', 7780394729, 'Nympadora Tonks', '2 2536 7th Ave W Vancouver BC V6K 1Y9');
INSERT INTO Customers
VALUES ('0000777', 7783948294, 'Kingsley Shacklebolt', '836 30th Ave E Vancouver BC V5V 2W1');
INSERT INTO Customers
VALUES ('0000888', 6042052036, 'Fleur Delacour', '2439 7th Ave W Vancouver BC V6K 1Y6');
INSERT INTO Customers
VALUES ('0000999', 7781132923, 'Olympe Maxime', '303 621 57th Ave W Vancouver BC V6P 6P5');
INSERT INTO Customers
VALUES ('1111222', 7782348811, 'Poppy Pomfrey', '2425 7th Ave W Vancouver BC V6K 1Y6');
INSERT INTO Customers
VALUES ('1111333', 2502349234, 'Remus Lupin', '275 28th Ave E Vancouver BC V5V 2M5');
INSERT INTO Customers
VALUES ('1111444', 2508794049, 'Cho Chang', '5 12th Ave W Vancouver BC V5Y 1T4');
INSERT INTO Customers
VALUES ('1111555', 2501868942, 'Horace Slughorn', '106 588 45th Ave W Vancouver BC V5Z 4S3');
INSERT INTO Customers
VALUES ('1111666', 2501901823, 'Vernon Dursley', 'Number 4 Privet Drive, Little Whinging');
INSERT INTO Customers
VALUES ('1111777', 2502834020, 'Minerva McGonnogal', '563 Union St Vancouver BC V6A 2B7');

INSERT INTO VehicleTypes
VALUES ('Economy', 'AC, Dual Front Airbags, Anti-lock Braking System, Bluetooth', 250, 50, 10, 50, 10, 2, 1);
INSERT INTO VehicleTypes
VALUES ('Compact', 'AC, Dual Front Airbags, Anti-lock Braking System, Evasive Steering', 300, 60, 12, 100, 20, 4, 2);
INSERT INTO VehicleTypes
VALUES ('Mid-size', 'AC, Dual Front Airbags, Anti-lock Braking System, Sound Enhancement', 350, 70, 14, 150, 30, 6, 3);
INSERT INTO VehicleTypes
VALUES ('Standard', 'AC, Dual Front Airbags, Anti-lock Braking System, Smart Suspension', 400, 80, 16, 200, 40, 8, 4);
INSERT INTO VehicleTypes
VALUES ('Full-size', 'AC, Dual Front Airbags, Anti-lock Braking System, Heads-up Display', 500, 100, 18, 250, 50, 10,
        5);
INSERT INTO VehicleTypes
VALUES ('SUV', 'AC, Dual Front Airbags, Anti-lock Braking System, Keyless Entry', 550, 110, 20, 300, 60, 12, 6);
INSERT INTO VehicleTypes
VALUES ('Truck', 'AC, Dual Front Airbags, Anti-lock Braking System, Heated Steering Wheel', 600, 120, 22, 350, 70, 14,
        7);

INSERT INTO Vehicles
VALUES ('I9H8M1N', 'Toyota', 'Corolla', 2019, 'Black', 00191, 'Rented', 'Economy', '800 Robson St', 'Vancouver');
INSERT INTO Vehicles
VALUES ('NL23NJ3', 'Volkswagen', 'Jetta', 2017, 'White', 00923, 'Available', 'Standard', '4700 Kingsway', 'Burnaby');
INSERT INTO Vehicles
VALUES ('AOUN9S9', 'Land Rover', 'Discovery', 2015, 'Grey', 05283, 'Rented', 'SUV', '525 W Broadway', 'Vancouver');
INSERT INTO Vehicles
VALUES ('N8236A1', 'Ford', 'Fiesta', 2010, 'Blue', 34382, 'Maintenance', 'Compact', '10153 King George Blvd', 'Surrey');

INSERT INTO Vehicles
VALUES ('K4H5G2D', 'BMW', 'X3', 2015, 'Silver', 39420, 'Available', 'Full-size', '525 W Broadway', 'Vancouver');
INSERT INTO Vehicles
VALUES ('D8S9FJ0', 'Honda', 'Accord', 2010, 'Tan', 29384, 'Rented', 'Truck', '10153 King George Blvd', 'Surrey');

INSERT INTO Reservations
VALUES (8028940, 'Compact', '1982451', TO_DATE('2018-05-22 08:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2018-05-24 08:00:00', 'yyyy-mm-dd hh24:mi:ss'), '10153 King George Blvd', 'Surrey');
INSERT INTO Reservations
VALUES (9208042, 'SUV', '1982451', TO_DATE('2019-11-18 13:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-12-01 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), '525 W Broadway', 'Vancouver');
INSERT INTO Reservations
VALUES (9422131, 'Economy', '0923443', TO_DATE('2019-11-20 13:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-11-27 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), '800 Robson St', 'Vancouver');
INSERT INTO Reservations
VALUES (1283084, 'Standard', '4238280', TO_DATE('2020-01-01 15:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2020-01-01 19:00:00', 'yyyy-mm-dd hh24:mi:ss'), '4700 Kingsway', 'Burnaby');

INSERT INTO Reservations
VALUES (7286515, 'Full-size', '0000111', TO_DATE('2019-01-20 13:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-01-27 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), '525 W Broadway', 'Vancouver');
INSERT INTO Reservations
VALUES (1086732, 'Truck', '0000222', TO_DATE('2019-06-03 16:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-06-04 14:00:00', 'yyyy-mm-dd hh24:mi:ss'), '10153 King George Blvd', 'Surrey');
INSERT INTO Reservations
VALUES (7949918, 'Compact', '0000333', TO_DATE('2019-09-02 19:10:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-09-17 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), '10153 King George Blvd', 'Surrey');
INSERT INTO Reservations
VALUES (1264800, 'SUV', '0000444', TO_DATE('2019-08-04 13:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-08-27 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), '525 W Broadway', 'Vancouver');
INSERT INTO Reservations
VALUES (7726528, 'Economy', '0000555', TO_DATE('2019-04-26 11:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-05-26 14:00:00', 'yyyy-mm-dd hh24:mi:ss'), '800 Robson St', 'Vancouver');
INSERT INTO Reservations
VALUES (6007209, 'Standard', '0000666', TO_DATE('2019-02-14 08:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-02-15 09:30:00', 'yyyy-mm-dd hh24:mi:ss'), '4700 Kingsway', 'Burnaby');
INSERT INTO Reservations
VALUES (4345475, 'Full-size', '0000777', TO_DATE('2019-11-11 11:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-11-27 08:00:00', 'yyyy-mm-dd hh24:mi:ss'), '525 W Broadway', 'Vancouver');
INSERT INTO Reservations
VALUES (3607984, 'Truck', '0000888', TO_DATE('2019-01-21 11:45:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-02-07 16:35:00', 'yyyy-mm-dd hh24:mi:ss'), '10153 King George Blvd', 'Surrey');
INSERT INTO Reservations
VALUES (1296662, 'Compact', '0000999', TO_DATE('2019-06-03 14:20:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-06-05 16:20:00', 'yyyy-mm-dd hh24:mi:ss'), '10153 King George Blvd', 'Surrey');
INSERT INTO Reservations
VALUES (3538755, 'SUV', '0000999', TO_DATE('2019-09-19 11:15:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-09-20 12:00:00', 'yyyy-mm-dd hh24:mi:ss'), '525 W Broadway', 'Vancouver');
INSERT INTO Reservations
VALUES (0238051, 'Economy', '1111222', TO_DATE('2018-12-03 16:45:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2018-12-15 11:15:00', 'yyyy-mm-dd hh24:mi:ss'), '800 Robson St', 'Vancouver');
INSERT INTO Reservations
VALUES (3059818, 'Standard', '1111333', TO_DATE('2019-03-13 09:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-03-19 11:00:00', 'yyyy-mm-dd hh24:mi:ss'), '4700 Kingsway', 'Burnaby');
INSERT INTO Reservations
VALUES (0170831, 'Full-size', '1111444', TO_DATE('2019-04-09 13:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-04-19 12:00:00', 'yyyy-mm-dd hh24:mi:ss'), '525 W Broadway', 'Vancouver');
INSERT INTO Reservations
VALUES (5537730, 'Truck', '1111666', TO_DATE('2019-05-09 11:15:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-05-11 16:10:00', 'yyyy-mm-dd hh24:mi:ss'), '10153 King George Blvd', 'Surrey');
INSERT INTO Reservations
VALUES (1718118, 'Compact', '1111666', TO_DATE('2019-06-03 12:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-06-13 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), '10153 King George Blvd', 'Surrey');
INSERT INTO Reservations
VALUES (1212347, 'SUV', '1111777', TO_DATE('2019-01-30 11:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-02-03 16:45:00', 'yyyy-mm-dd hh24:mi:ss'), '525 W Broadway', 'Vancouver');
INSERT INTO Reservations
VALUES (0631610, 'Economy', '1111777', TO_DATE('2019-02-27 13:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-02-28 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), '800 Robson St', 'Vancouver');
INSERT INTO Reservations
VALUES (4966308, 'Standard', '1111777', TO_DATE('2019-05-29 09:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-05-30 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), '4700 Kingsway', 'Burnaby');
INSERT INTO Reservations
VALUES (7391229, 'Full-size', '1111777', TO_DATE('2019-06-11 16:10:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-06-15 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), '525 W Broadway', 'Vancouver');

INSERT INTO Rentals
VALUES (91384411, 'N8236A1', '1982451', TO_DATE('2018-05-22 08:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2018-05-24 08:00:00', 'yyyy-mm-dd hh24:mi:ss'), 30042, 'MasterCard',
        0141325672839274, '05/21', 8028940);
INSERT INTO Rentals
VALUES (10319331, 'AOUN9S9', '1982451', TO_DATE('2019-11-18 13:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-12-01 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 05283, 'MasterCard',
        0141325672839274, '05/21', 9208042);
INSERT INTO Rentals
VALUES (83831044, 'I9H8M1N', '0923443', TO_DATE('2019-11-20 13:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-11-27 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 00191, 'Visa', 9484927753423448,
        '08/23', 9422131);
INSERT INTO Rentals
VALUES (45364896, 'K4H5G2D', '0000111', TO_DATE('2019-01-20 13:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-01-27 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 92834, 'American Express', 0701763462143386,
        '09/22', 7286515);
INSERT INTO Rentals
VALUES (96840482, 'D8S9FJ0', '0000222', TO_DATE('2019-06-03 16:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-06-04 14:00:00', 'yyyy-mm-dd hh24:mi:ss'), 29462, 'Visa', 2628471545328386,
        '11/21', 1086732);
INSERT INTO Rentals
VALUES (86410233, 'N8236A1', '0000333', TO_DATE('2019-09-02 19:10:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-09-17 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 49582, 'Mastercard',
        0780758549296463,
        '09/21', 7949918);
INSERT INTO Rentals
VALUES (12365434, 'AOUN9S9', '0000444', TO_DATE('2019-08-04 13:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-08-27 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 29485, 'Visa', 2731525812154151,
        '08/22', 1264800);
INSERT INTO Rentals
VALUES (09275839, 'I9H8M1N', '0000555', TO_DATE('2019-04-26 11:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-05-26 14:00:00', 'yyyy-mm-dd hh24:mi:ss'), 20384, 'Visa', 0910572471609307,
        '06/21', 7726528);
INSERT INTO Rentals
VALUES (77793472, 'NL23NJ3', '0000666', TO_DATE('2019-02-14 08:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-02-15 09:30:00', 'yyyy-mm-dd hh24:mi:ss'), 29374, 'American Express',
        2334937461090739,
        '04/23', 6007209);
INSERT INTO Rentals
VALUES (11111111, 'K4H5G2D', '0000777', TO_DATE('2019-11-11 11:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-11-27 08:00:00', 'yyyy-mm-dd hh24:mi:ss'), 17940, 'Visa', 6354280641529249,
        '02/23', 4345475);
INSERT INTO Rentals
VALUES (22222222, 'D8S9FJ0', '0000888', TO_DATE('2019-01-21 11:45:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-02-07 16:35:00', 'yyyy-mm-dd hh24:mi:ss'), 67426, 'Mastercard',
        4304389335583440,
        '03/22', 3607984);
INSERT INTO Rentals
VALUES (33333333, 'N8236A1', '0000999', TO_DATE('2019-06-03 14:20:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-06-05 16:20:00', 'yyyy-mm-dd hh24:mi:ss'), 47753, 'Visa', 7612370990016463,
        '09/21', 1296662);
INSERT INTO Rentals
VALUES (44444444, 'AOUN9S9', '0000999', TO_DATE('2019-09-19 11:15:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-09-20 12:00:00', 'yyyy-mm-dd hh24:mi:ss'), 56635, 'American Express', 7893968606563348,
        '10/21', 3538755);
INSERT INTO Rentals
VALUES (55555555, 'I9H8M1N', '1111222', TO_DATE('2018-12-03 16:45:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2018-12-15 11:15:00', 'yyyy-mm-dd hh24:mi:ss'), 35636, 'Visa', 4901723195664757,
        '12/22', 0238051);
INSERT INTO Rentals
VALUES (66666666, 'NL23NJ3', '1111333', TO_DATE('2019-03-13 09:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-03-19 11:00:00', 'yyyy-mm-dd hh24:mi:ss'), 45644, 'Mastercard', 4763617834520354,
        '04/21', 3059818);
INSERT INTO Rentals
VALUES (77777777, 'K4H5G2D', '1111444', TO_DATE('2019-04-09 13:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-04-19 12:00:00', 'yyyy-mm-dd hh24:mi:ss'), 87686, 'Visa', 4189318492303366,
        '10/22', 0170831);
INSERT INTO Rentals
VALUES (88888888, 'D8S9FJ0', '1111666', TO_DATE('2019-05-09 11:15:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-05-11 16:10:00', 'yyyy-mm-dd hh24:mi:ss'), 46784, 'American Express', 5225642196463189,
        '01/21', 5537730);
INSERT INTO Rentals
VALUES (99999999, 'N8236A1', '1111666', TO_DATE('2019-06-03 12:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-06-13 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 24534, 'Visa', 6593975550405279,
        '03/22', 1718118);
INSERT INTO Rentals
VALUES (11110000, 'AOUN9S9', '1111777', TO_DATE('2019-01-30 11:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-02-03 16:45:00', 'yyyy-mm-dd hh24:mi:ss'), 76545, 'Visa', 2339678641112972,
        '05/23', 1212347);
INSERT INTO Rentals
VALUES (11112222, 'I9H8M1N', '1111777', TO_DATE('2019-02-27 13:30:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-02-28 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 45644, 'American Express', 1626818938829696,
        '11/23', 0631610);
INSERT INTO Rentals
VALUES (11113333, 'NL23NJ3', '1111777', TO_DATE('2019-05-29 09:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-05-30 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 33333, 'Visa', 2581616115282421,
        '02/21', 4966308);
INSERT INTO Rentals
VALUES (11114444, 'K4H5G2D', '1111777', TO_DATE('2019-06-11 16:10:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2019-06-15 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 78656, 'Mastercard',
        8832573613452243, '08/22', 7391229);

INSERT INTO Returns
VALUES (91384411, TO_DATE('2018-05-24 08:00:00', 'yyyy-mm-dd hh24:mi:ss'), 30068, 1, 160);
INSERT INTO Returns
VALUES (10319331, TO_DATE('2019-12-01 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 42360, 0, 451);
INSERT INTO Returns
VALUES (83831044, TO_DATE('2019-11-27 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 23500, 1, 674);
INSERT INTO Returns
VALUES (45364896, TO_DATE('2019-01-27 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 32949, 1, 567);
INSERT INTO Returns
VALUES (96840482, TO_DATE('2019-06-04 14:00:00', 'yyyy-mm-dd hh24:mi:ss'), 45623, 0, 452);
INSERT INTO Returns
VALUES (86410233, TO_DATE('2019-09-17 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 45609, 1, 764);
INSERT INTO Returns
VALUES (12365434, TO_DATE('2019-08-27 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 64544, 1, 485);
INSERT INTO Returns
VALUES (09275839, TO_DATE('2019-05-26 14:00:00', 'yyyy-mm-dd hh24:mi:ss'), 45644, 0, 259);
INSERT INTO Returns
VALUES (77793472, TO_DATE('2019-02-15 09:30:00', 'yyyy-mm-dd hh24:mi:ss'), 64564, 1, 679);
INSERT INTO Returns
VALUES (11111111, TO_DATE('2019-11-27 08:00:00', 'yyyy-mm-dd hh24:mi:ss'), 12314, 1, 283);
INSERT INTO Returns
VALUES (22222222, TO_DATE('2019-02-07 16:35:00', 'yyyy-mm-dd hh24:mi:ss'), 34238, 0, 489);
INSERT INTO Returns
VALUES (33333333, TO_DATE('2019-06-05 16:20:00', 'yyyy-mm-dd hh24:mi:ss'), 24568, 1, 986);
INSERT INTO Returns
VALUES (44444444, TO_DATE('2019-09-20 12:00:00', 'yyyy-mm-dd hh24:mi:ss'), 84545, 0, 376);
INSERT INTO Returns
VALUES (55555555, TO_DATE('2018-12-15 11:15:00', 'yyyy-mm-dd hh24:mi:ss'), 97867, 1, 876);
INSERT INTO Returns
VALUES (66666666, TO_DATE('2019-03-19 11:00:00', 'yyyy-mm-dd hh24:mi:ss'), 54674, 1, 345);
INSERT INTO Returns
VALUES (77777777, TO_DATE('2019-04-19 12:00:00', 'yyyy-mm-dd hh24:mi:ss'), 87654, 0, 938);
INSERT INTO Returns
VALUES (88888888, TO_DATE('2019-05-11 16:10:00', 'yyyy-mm-dd hh24:mi:ss'), 54378, 0, 559);
INSERT INTO Returns
VALUES (99999999, TO_DATE('2019-06-13 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 87643, 1, 775);
INSERT INTO Returns
VALUES (11110000, TO_DATE('2019-02-03 16:45:00', 'yyyy-mm-dd hh24:mi:ss'), 34647, 1, 938);
INSERT INTO Returns
VALUES (11112222, TO_DATE('2019-02-28 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 75675, 0, 283);
INSERT INTO Returns
VALUES (11113333, TO_DATE('2019-05-30 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), 45674, 1, 599);
INSERT INTO Returns
VALUES (11114444, TO_DATE('2019-06-15 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 78765, 0, 113);
