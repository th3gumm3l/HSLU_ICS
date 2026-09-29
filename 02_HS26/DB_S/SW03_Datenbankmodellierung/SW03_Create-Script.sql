CREATE DATABASE RecommenDB;

USE RecommenDB;

CREATE TABLE Movies (
  MovieID INTEGER PRIMARY KEY,
  Title TEXT,
  Release_date DATE,
  Budget INTEGER,
  Genres JSON,
  Spoken_languages JSON );

CREATE TABLE Ratings (
  UserID INTEGER,
  MovieID INTEGER,
  Rating INTEGER,
  Timestamp DATETIME,
  PRIMARY KEY (MovieID, UserID),
  CONSTRAINT MovieID_FK
  FOREIGN KEY (MovieID) REFERENCES Movies(MovieID));

CREATE TABLE Genres (
   GenreID INTEGER PRIMARY KEY,
   Name TEXT);

CREATE TABLE Movie_has_Genre (
  MovieID INTEGER,
  GenreID INTEGER,
  PRIMARY KEY (MovieID, GenreID),
  FOREIGN KEY (MovieID) REFERENCES Movies (MovieID),
  FOREIGN KEY (GenreID) REFERENCES genres (GenreID));