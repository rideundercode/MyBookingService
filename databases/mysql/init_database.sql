CREATE DATABASE IF NOT EXISTS reservation;
CREATE DATABASE IF NOT EXISTS hotel;
CREATE DATABASE IF NOT EXISTS payment;
CREATE USER reservation IDENTIFIED BY 'reservation';
CREATE USER hotel IDENTIFIED BY 'hotel';
CREATE USER payment IDENTIFIED BY 'payment';
GRANT ALL PRIVILEGES ON reservation.* TO reservation;
GRANT ALL PRIVILEGES ON hotel.* TO hotel;
GRANT ALL PRIVILEGES ON payment.* TO payment;