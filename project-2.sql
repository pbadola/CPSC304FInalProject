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
CREATE TABLE Customers(
	dlicense varchar2(7) PRIMARY KEY,
	cellphone number(10) UNIQUE,
	name varchar2(30),
	address varchar2(70)
	);

CREATE TABLE Returns(
	rid number(8) PRIMARY KEY,
	rdate varchar2(8),
	rtime varchar2(4),
	odometer number(5),
	fullTank number(1),
	rvalue number(5,2)
	);

CREATE TABLE VehicleTypes(
	vtname varchar2(30) PRIMARY KEY,
	features varchar2(70),
	wrate number(4,2),
	drate number(4,2),
	hrate number(4,2),
	wirate number(4,2),
	dirate number(4,2),
	hirate number(4,2),
	krate number(4,2)
	);	

CREATE TABLE Reservations(
	confNo number(7) PRIMARY KEY,
	vtname varchar2(30),
	dlicense varchar2(7),
	fromDate varchar2(8),
	fromTime varchar2(4),
	toDate varchar2(8),
	toTime varchar2(4),
	foreign key (vtname) REFERENCES VehicleTypes,
	foreign key (dlicense) REFERENCES Customers
	);

CREATE TABLE Vehicles(
	vlicense varchar2(7) PRIMARY KEY,
	make varchar2(15),
	model varchar2(15),
	year number(4),
	color varchar2(15),
	odometer number(5),
	status varchar2(15),
	vtname varchar2(30),
	location varchar(30),
	city varchar(30),
	foreign key(vtname) REFERENCES VehicleTypes
	);

CREATE TABLE Rentals(
	rid number(8) PRIMARY KEY,
	vlicense varchar2(7), 
	dlicense varchar2(7),
	fromDate varchar2(8),
	fromTime varchar2(4),
	toDate varchar2(8),
	toTime varchar2(4),
	odometer number(5),
	cardName varchar2(30),
	cardNo number(16),
	expDate varchar2(4),
	confNo number(7), 
	foreign key (vlicense) REFERENCES Vehicles,
	foreign key (dlicense) REFERENCES Customers,
	foreign key (confNo) REFERENCES Reservations
	);

INSERT INTO Customers VALUES ("1982451", 6046885225, "Kevin Smith", "3308 Ash St Vancouver BC V5Z 3E3");
INSERT INTO Customers VALUES ("0923443", 6046892577, "Zhen Zhong", "122 Walter Hardwick Ave 305 Vancouver BC V5Y 0C9");
INSERT INTO Customers VALUES ("4238280", 6047313240, "Miguel Romero", "2485 Broadway W 414 Vancouver BC V6K 2E8");

INSERT INTO
