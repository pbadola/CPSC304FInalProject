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

CREATE TABLE Returns
(
    rid      number(8) PRIMARY KEY,
    rdate    char(10),
    time     char(5),
    odometer number(5),
    fullTank number(1),
    value    number(5)
);

CREATE TABLE VehicleTypes
(
    vtname   varchar2(30) PRIMARY KEY,
    features varchar2(70),
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
    confNo   number(7) PRIMARY KEY,
    vtname   varchar2(30),
    dlicense varchar2(7),
    fromDate char(10),
    fromTime char(5),
    toDate   char(10),
    toTime   char(5),
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
    rid      number(8) PRIMARY KEY,
    vlicense varchar2(7),
    dlicense varchar2(7),
    fromDate char(10),
    fromTime char(5),
    toDate   char(10),
    toTime   char(5),
    odometer number(5),
    cardName varchar2(30),
    cardNo   number(16),
    expDate  char(5),
    confNo   number(7),
    foreign key (vlicense) REFERENCES Vehicles,
    foreign key (dlicense) REFERENCES Customers,
    foreign key (confNo) REFERENCES Reservations
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

INSERT INTO Reservations
VALUES (8028940, 'Compact', '1982451', '2018-05-22', '08:00', '2018-05-24', '08:00');
INSERT INTO Reservations
VALUES (9208042, 'SUV', '1982451', '2019-11-18', '13:30', '2019-12-01', '18:00');
INSERT INTO Reservations
VALUES (9422131, 'Economy', '0923443', '2019-11-20', '13:30', '2019-11-27', '18:00');
INSERT INTO Reservations
VALUES (1283084, 'Standard', '4238280', '2020-01-01', '15:00', '2020-01-01', '19:00');

INSERT INTO Rentals
VALUES (91384411, 'N8236A1', '1982451', '2018-05-22', '08:00', '2018-05-24', '08:00', 30042, 'MasterCard',
        0141325672839274, '05/21', 8028940);
INSERT INTO Rentals
VALUES (10319331, 'AOUN9S9', '1982451', '2019-11-18', '13:30', '2019-12-01', '18:00', 05283, 'MasterCard',
        0141325672839274, '05/21', 9208042);
INSERT INTO Rentals
VALUES (83831044, 'I9H8M1N', '0923443', '2019-11-20', '13:30', '2019-11-27', '18:00', 00191, 'Visa', 9484927753423448,
        '08/23', 9422131);

INSERT INTO Returns
VALUES (91384411, '2018-05-24', '08:00', 30068, 1, 160);
