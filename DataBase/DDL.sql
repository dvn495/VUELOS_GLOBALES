-- Creacion de la base de datos

CREATE DATABASE airport_database;

-- Seleccion de la DB

USE airport_database;

-- Tables creation

CREATE TABLE document_types(
	id INT PRIMARY KEY AUTO_INCREMENT,
	documenType VARCHAR(40)
);

CREATE TABLE customer(
	id VARCHAR(5) PRIMARY KEY,
	name VARCHAR(40) NOT NULL,
	age INT NOT NULL,
	idDocumentType INT NOT NULL,
		CONSTRAINT FK_DocumentType FOREIGN KEY (idDocumentType) REFERENCES document_types(id)
);

CREATE TABLE flight_fares(
	id INT PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(20) NOT NULL,
	details TEXT,
	value double(2,3)
);

CREATE TABLE trip(
	id INT PRIMARY KEY AUTO_INCREMENT,
	tripDate DATE NOT NULL,
	price DOUBLE(7,3) NOT NULL
);

CREATE TABLE trip_booking(
	id INT PRIMARY KEY AUTO_INCREMENT,
	date DATE NOT NULL,
	idTrip INT NOT NULL
		CONSTRAINT FK_Trip FOREIGN KEY (idTrip) REFERENCES trip(id)
);

CREATE TABLE trip_booking_details(
	id INT PRIMARY KEY AUTO_INCREMENT,
	idTripBooking INT NOT NULL,
	idCustomer VARCHAR(20) NOT NULL,
	idFlightFares INT NOT NULL,
		CONSTRAINT FK_TripBooking FOREIGN KEY (idTripBooking) REFERENCES trip_booking(id),
		CONSTRAINT FK_Customer FOREIGN KEY (idCustomer) REFERENCES customer(id),
		CONSTRAINT FK_FlightFares FOREIGN KEY (idFlightFares) REFERENCES flight_fares(id)
);

-- Employee

CREATE TABLE manufacturer(
	id INT PRIMARY KEY,
	manufacturer VARCHAR(50) NOT NULL
);

CREATE TABLE model(
	id VARCHAR(5) PRIMARY KEY,
	model VARCHAR(50) NOT NULL,
	idManufacturer INT NOT NULL,
		CONSTRAINT FK_Manufacturer FOREIGN KEY (idManufacturer) REFERENCES manufacturer(id)
);

CREATE TABLE status(
	id INT PRIMARY KEY,
	status VARCHAR(40)
);

CREATE TABLE airline(
	id VARCHAR(5) PRIMARY KEY AUTO_INCREMENT,
	airline VARCHAR(40) NOT NULL
);

CREATE TABLE plane(
	id VARCHAR(5) PRIMARY KEY,
	plates VARCHAR(30) NOT NULL,
	capacity INT NOT NULL,
	fabricationDate DATE NOT NULL,
	idModel VARCHAR(5) NOT NULL,
	idStatus INT NOT NULL,
	idAirline
);

CREATE TABLE tripulation_role(
	id INT PRIMARY KEY AUTO_INCREMENT,
	role VARCHAR(30) NOT NULL
);

CREATE TABLE employee(
	
);


