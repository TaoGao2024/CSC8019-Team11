/*
 * Creation Date: Apr 1, 2025
 * Initializes database schema for mySQL
 *
 * @author Samuel Leung
 * @version 1.0
 *
 * Tables include CastleInfo, RouteInfo, RouteStop, ScheduleInfo, and StopInfo
 *
 */
CREATE TABLE CastleInfo (
    castleId INT PRIMARY KEY,
    name VARCHAR(30),
    description VARCHAR(255),
    entryFee DOUBLE
);
CREATE TABLE RouteInfo (
   routeId INT PRIMARY KEY,
   castleId INT,
   price DOUBLE,
   availability VARCHAR(30),
   castleDirection BOOLEAN,
   travelTime INT
);
CREATE TABLE RouteStop (
   routeId INT,
   stopId INT,
   sequenceNum INT NOT NULL,
   busService VARCHAR(30),
   PRIMARY KEY (routeId, stopId)
);
CREATE TABLE ScheduleInfo (
  scheduleId INT PRIMARY KEY,
  routeId INT NOT NULL,
  departTime VARCHAR(5) NOT NULL,
  arriveTime VARCHAR(5) NOT NULL
);
CREATE TABLE StopInfo (
  stopId INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  instructions VARCHAR(255) --how to walk to castle from stop
);