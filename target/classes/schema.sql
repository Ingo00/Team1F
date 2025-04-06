CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(50) PRIMARY KEY,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS flight (
  flight_number VARCHAR(10) PRIMARY KEY,
  origin VARCHAR(50) NOT NULL,
  destination VARCHAR(50) NOT NULL,
  departure_time TEXT NOT NULL,
  available_seats INT NOT NULL
);

CREATE TABLE IF NOT EXISTS bookings (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username VARCHAR(50) NOT NULL,
  flight_number VARCHAR(10) NOT NULL,
  seats_booked INT NOT NULL,
  booking_time TIMESTAMP NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username),
  FOREIGN KEY (flight_number) REFERENCES flight(flight_number)
);
