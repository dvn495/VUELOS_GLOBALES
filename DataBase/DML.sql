INSERT INTO country (id, country) VALUES
('C01', 'USA'),
('C02', 'Canada'),
('C03', 'Mexico'),
('C04', 'Brazil'),
('C05', 'Argentina');

INSERT INTO city (id, city, idCountry) VALUES
('CI01', 'New York', 'C01'),
('CI02', 'Los Angeles', 'C01'),
('CI03', 'Toronto', 'C02'),
('CI04', 'Mexico City', 'C03'),
('CI05', 'Sao Paulo', 'C04');

INSERT INTO airport (id, airport, idCity) VALUES
('A01', 'John F. Kennedy International Airport', 'CI01'),
('A02', 'Los Angeles International Airport', 'CI02'),
('A03', 'Toronto Pearson International Airport', 'CI03'),
('A04', 'Mexico City International Airport', 'CI04'),
('A05', 'São Paulo/Guarulhos–Governador André Franco Montoro International Airport', 'CI05');

INSERT INTO gate (id, gate, idAirport) VALUES
('G01', 'Gate A1', 'A01'),
('G02', 'Gate B2', 'A02'),
('G03', 'Gate C3', 'A03'),
('G04', 'Gate D4', 'A04'),
('G05', 'Gate E5', 'A05');

INSERT INTO tripulation_role (id, roleName) VALUES
('R01', 'Pilot'),
('R02', 'Co-Pilot'),
('R03', 'Flight Attendant'),
('R04', 'Ground Staff'),
('R05', 'Maintenance');

INSERT INTO airline (id, airline) VALUES ('A001', 'SkyHigh Airlines');
INSERT INTO airline (id, airline) VALUES ('A002', 'FlyFast Airways');
INSERT INTO airline (id, airline) VALUES ('A003', 'JetSet Airlines');
INSERT INTO airline (id, airline) VALUES ('A004', 'CloudNine Aviation');
INSERT INTO airline (id, airline) VALUES ('A005', 'BlueSky Airlines');


INSERT INTO employee (id, name, lastName, ingressDate, idRole, idAirline, idAirport) VALUES
('E01', 'John', 'Doe', '2020-01-15', 'R01', 'A01', 'A01'),
('E02', 'Jane', 'Smith', '2019-03-22', 'R03', 'A02', 'A02'),
('E03', 'Robert', 'Brown', '2018-06-10', 'R02', 'A03', 'A03'),
('E04', 'Emily', 'Jones', '2021-09-25', 'R05', 'A04', 'A04'),
('E05', 'Michael', 'Davis', '2017-11-30', 'R04', 'A05', 'A05');

INSERT INTO trip (id, tripDate, price) VALUES
('T01', '2023-07-01', 450.00),
('T02', '2023-07-15', 350.00),
('T03', '2023-08-01', 500.00),
('T04', '2023-08-20', 400.00),
('T05', '2023-09-01', 600.00);

INSERT INTO model (id, model, idManufacturer) VALUES
('M01', 'Boeing 737', 'MAN01'),
('M02', 'Airbus A320', 'MAN02'),
('M03', 'Embraer E190', 'MAN03'),
('M04', 'Boeing 777', 'MAN01'),
('M05', 'Airbus A350', 'MAN02');

INSERT INTO plane_status (id, status) VALUES
('S01', 'Operational'),
('S02', 'Maintenance'),
('S03', 'Out of Service');

INSERT INTO manufacturer (id, manufacturer) VALUES
('MAN01', 'Boeing'),
('MAN02', 'Airbus'),
('MAN03', 'Embraer');

INSERT INTO plane (id, plates, capacity, fabricationDate, idModel, idStatus) VALUES
('P01', 'ABC123', 180, '2015-06-01', 'M01', 'S01'),
('P02', 'DEF456', 220, '2018-03-15', 'M02', 'S02'),
('P03', 'GHI789', 150, '2020-11-10', 'M03', 'S01'),
('P04', 'JKL012', 200, '2017-09-25', 'M04', 'S03'),
('P05', 'MNO345', 250, '2019-01-05', 'M05', 'S02');

INSERT INTO flight_connection (id, connectionOrder, idTrip, idPlane, idAirportA, idAirportB) VALUES
('FC01', 1, 'T01', 'P01', 'A01', 'A02'),
('FC02', 2, 'T02', 'P02', 'A02', 'A03'),
('FC03', 1, 'T03', 'P03', 'A03', 'A04'),
('FC04', 2, 'T04', 'P04', 'A04', 'A05'),
('FC05', 1, 'T05', 'P05', 'A05', 'A01');

INSERT INTO trip_crews (id, idEmployee, idConnection) VALUES
('TC01', 'E01', 'FC01'),
('TC02', 'E02', 'FC02'),
('TC03', 'E03', 'FC03'),
('TC04', 'E04', 'FC04'),
('TC05', 'E05', 'FC05');

INSERT INTO revision_details (id, description, idEmployee) VALUES
('RD01', 'Engine check', 'E05'),
('RD02', 'Wing inspection', 'E05'),
('RD03', 'Fuel system check', 'E05'),
('RD04', 'Navigation system check', 'E05'),
('RD05', 'Landing gear inspection', 'E05');

INSERT INTO revision_employee (id, idEmployee, idRevision) VALUES
('RE01', 'E05', 'R01'),
('RE02', 'E05', 'R02'),
('RE03', 'E05', 'R03'),
('RE04', 'E05', 'R04'),
('RE05', 'E05', 'R05');
