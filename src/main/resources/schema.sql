CREATE TABLE flight (
  flight_number VARCHAR(10) PRIMARY KEY,
  origin VARCHAR(50) NOT NULL,
  destination VARCHAR(50) NOT NULL,
  departure_time TIMESTAMP NOT NULL,
  available_seats INT NOT NULL
);
