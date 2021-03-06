DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;

CREATE TABLE genres
(genre VARCHAR(255) PRIMARY KEY NOT NULL UNIQUE);

CREATE TABLE authors
(id INTEGER PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255) NOT NULL);

CREATE TABLE books
(id INTEGER PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(255) NOT NULL UNIQUE,
authors_id INTEGER NOT NULL,
genres_genre VARCHAR(255) NOT NULL,
FOREIGN KEY (authors_id) REFERENCES authors(id),
FOREIGN KEY (genres_genre) REFERENCES genres(genre));