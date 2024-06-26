-- Creacion de la base de datos

CREATE DATABASE IF NOT EXISTS airport_database;

-- Seleccion de la DB

USE airport_database;

-- Tables creation

CREATE TABLE IF NOT EXISTS document_types(
	id INT PRIMARY KEY,
	documenType VARCHAR(40)
);

CREATE TABLE IF NOT EXISTS customer(
	id VARCHAR(5) PRIMARY KEY,
	name VARCHAR(40) NOT NULL,
	lastName VARCHAR(40) NOT NULL,
	age INT NOT NULL,
	idDocumentType INT NOT NULL,
		CONSTRAINT FK_DocumentType FOREIGN KEY (idDocumentType) REFERENCES document_types(id)
);

CREATE TABLE IF NOT EXISTS flight_fares(
	id VARCHAR(5) PRIMARY KEY,
	description VARCHAR(20) NOT NULL,
	details TEXT,
	value double
);

CREATE TABLE IF NOT EXISTS trip(
	id VARCHAR(5) PRIMARY KEY,
	tripDate DATE NOT NULL,
	price DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS booking_status (
	id INT PRIMARY KEY,
	bookingStatus VARCHAR(50) NOT NULL;
);

CREATE TABLE IF NOT EXISTS trip_booking(
	id VARCHAR(5) PRIMARY KEY,
	bookingDate DATE NOT NULL,
	idTrip VARCHAR(5) NOT NULL,
	idBookingStatus INT NOT NULL,
		CONSTRAINT FK_TripBooking FOREIGN KEY (idTrip) REFERENCES trip(id)
		CONSTRAINT FK_BookingStatus FOREIGN KEY (idBookingStatus) REFERENCES booking_status(id)
);

CREATE TABLE IF NOT EXISTS trip_booking_details(
	id VARCHAR(5) PRIMARY KEY,
	idTripBooking VARCHAR(5) NOT NULL,
	idCustomer VARCHAR(5) NOT NULL,
	idFlightFares VARCHAR(5) NOT NULL,
		CONSTRAINT FK_TripBookingDetails FOREIGN KEY (idTripBooking) REFERENCES trip_booking(id),
		CONSTRAINT FK_CustomerBookingDetails FOREIGN KEY (idCustomer) REFERENCES customer(id),
		CONSTRAINT FK_FlightFares FOREIGN KEY (idFlightFares) REFERENCES flight_fares(id)
);

CREATE TABLE IF NOT EXISTS payment (
    id VARCHAR(5) PRIMARY KEY,
    amount DOUBLE NOT NULL,
    paymentMethod VARCHAR(20) NOT NULL,
    creditCardNumber VARCHAR(16) NOT NULL,
    idTripBookingDetails VARCHAR(5) NOT NULL,
    CONSTRAINT FK_TripBookingDetailsPayment FOREIGN KEY (idTripBookingDetails) REFERENCES trip_booking_details(id)
);

CREATE TABLE IF NOT EXISTS manufacturer(
	id VARCHAR(5) PRIMARY KEY,
	manufacturer VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS model(
	id VARCHAR(5) PRIMARY KEY,
	model VARCHAR(50) NOT NULL,
	idManufacturer VARCHAR(5) NOT NULL,
		CONSTRAINT FK_Manufacturer FOREIGN KEY (idManufacturer) REFERENCES manufacturer(id)
);

CREATE TABLE IF NOT EXISTS plane_status(
	id VARCHAR(5) PRIMARY KEY,
	status VARCHAR(40)
);

CREATE TABLE IF NOT EXISTS airline(
	id VARCHAR(5) PRIMARY KEY,
	airline VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS plane(
	id VARCHAR(5) PRIMARY KEY,
	plates VARCHAR(30) NOT NULL,
	capacity INT NOT NULL,
	fabricationDate DATE NOT NULL,
	idModel VARCHAR(5) NOT NULL,
	idStatus VARCHAR(5) NOT NULL,
		CONSTRAINT FK_PlaneModel FOREIGN KEY (idModel) REFERENCES model(id),
		CONSTRAINT FK_PlaneStatus FOREIGN KEY (idStatus) REFERENCES plane_status(id)
);

CREATE TABLE IF NOT EXISTS country(
	id VARCHAR(5) PRIMARY KEY,
	country VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS city(
	id VARCHAR(5) PRIMARY KEY,
	city VARCHAR(50) NOT NULL,
	idCountry VARCHAR(5) NOT NULL,
		CONSTRAINT FK_ContriesCity FOREIGN KEY (idCountry) REFERENCES country(id)
);

CREATE TABLE IF NOT EXISTS airport(
	id VARCHAR(5) PRIMARY KEY,
	airport VARCHAR(50) NOT NULL,
	idCity VARCHAR(5) NOT NULL,
		CONSTRAINT FK_CityAirport FOREIGN KEY (idCity) REFERENCES city(id)
);

CREATE TABLE IF NOT EXISTS gate(
	id VARCHAR(5) PRIMARY KEY,
	gate VARCHAR(10) NOT NULL,
	idAirport VARCHAR(5) NOT NULL,
		CONSTRAINT FK_AirportGate FOREIGN KEY (idAirport) REFERENCES airport(id)
);

CREATE TABLE IF NOT EXISTS tripulation_role(
	id VARCHAR(5) PRIMARY KEY,
	roleName VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee(
	id VARCHAR(5) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	lastName VARCHAR(50) NOT NULL,
	ingressDate DATE NOT NULL,
	idRole VARCHAR(5) NOT NULL,
	idAirline VARCHAR(5) NOT NULL,
	idAirport VARCHAR(5) NOT NULL,
		CONSTRAINT FK_Role FOREIGN KEY (idRole) REFERENCES tripulation_role(id),
		CONSTRAINT FK_AirlineEmployee FOREIGN KEY (idAirline) REFERENCES airline(id),
		CONSTRAINT FK_AirportEmployee FOREIGN KEY (idAirport) REFERENCES airport(id)
);

CREATE TABLE IF NOT EXISTS flight_connection(
	id VARCHAR(5) PRIMARY KEY,
	conectionOrder INT NOT NULL,
	idTrip VARCHAR(5) NOT NULL,
	idPlane VARCHAR(5) NOT NULL,
	idAirportA VARCHAR(5) NOT NULL,
	idAirportB VARCHAR(5) NOT NULL,
		CONSTRAINT FK_TripConnection FOREIGN KEY (idTrip) REFERENCES trip(id),
		CONSTRAINT FK_PlaneConnection FOREIGN KEY (idPlane) REFERENCES plane(id),
		CONSTRAINT FK_AirportA FOREIGN KEY (idAirportA) REFERENCES airport(id),
		CONSTRAINT FK_TripAirportB FOREIGN KEY (idAirportB) REFERENCES airport(id)
);

CREATE TABLE IF NOT EXISTS trip_crews(
	id VARCHAR(5) PRIMARY KEY,
	idEmployee VARCHAR(5),
	idConnection VARCHAR(5),
		CONSTRAINT FK_EmployeeTrip FOREIGN KEY (idEmployee) REFERENCES employee(id),
		CONSTRAINT FK_Connection FOREIGN KEY (idConnection) REFERENCES flight_connection(id)
);

CREATE TABLE IF NOT EXISTS revision_details(
	id VARCHAR(5) PRIMARY KEY,
	description TEXT,
	idEmployee VARCHAR(5) NOT NULL,
		CONSTRAINT FK_EmployeeRevDetails FOREIGN KEY (idEmployee) REFERENCES employee(id)
);

CREATE TABLE IF NOT EXISTS revision(
	id VARCHAR(5) PRIMARY KEY,
	revisionDate DATE NOT NULL,
	idPlane VARCHAR(5) NOT NULL,
	idDetails VARCHAR(5) NOT NULL,
		CONSTRAINT FK_PlaneRevision FOREIGN KEY (idPlane) REFERENCES plane(id),
		CONSTRAINT FK_RevDetails FOREIGN KEY (idDetails) REFERENCES revision_details(id)
);

CREATE TABLE IF NOT EXISTS revision_employee(
	id VARCHAR(5) PRIMARY KEY,
	idEmployee VARCHAR(5) NOT NULL,
	idRevision VARCHAR(5) NOT NULL,
		CONSTRAINT FK_EmployeeRevision FOREIGN KEY (idEmployee) REFERENCES employee(id),
		CONSTRAINT FK_RevisionRevision FOREIGN KEY (idRevision) REFERENCES revision(id)
);