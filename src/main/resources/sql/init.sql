CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS companies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS planes (
    id SERIAL PRIMARY KEY,
    manufacturer VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL UNIQUE,
    capacity INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS flights (
    id SERIAL PRIMARY KEY,
    source VARCHAR(255) NOT NULL,
    destination VARCHAR(255) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    plane VARCHAR(255) REFERENCES planes(model) ON DELETE CASCADE,
    company INT REFERENCES companies(id) ON DELETE CASCADE,
    is_departure BOOLEAN DEFAULT false
    );

CREATE TABLE IF NOT EXISTS tickets (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    flight INT REFERENCES flights(id) ON DELETE CASCADE
    );