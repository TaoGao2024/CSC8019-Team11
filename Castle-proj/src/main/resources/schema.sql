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
    castleId VARCHAR(4) PRIMARY KEY,
    name VARCHAR(30),
    description VARCHAR(1000),
    entryFee DOUBLE
);
CREATE TABLE RouteInfo (
   routeId VARCHAR(4) PRIMARY KEY,
   castleId VARCHAR(4),
   price DOUBLE,
   availability VARCHAR(30),
   castleDirection BOOLEAN,
   travelTime INT
);
CREATE TABLE RouteStop (
   routeId VARCHAR(4),
   stopId VARCHAR(4),
   sequenceNum INT NOT NULL,
   busService VARCHAR(30),
   PRIMARY KEY (routeId, stopId)
);
CREATE TABLE ScheduleInfo (
  scheduleId VARCHAR(4) PRIMARY KEY,
  routeId VARCHAR(4) NOT NULL,
  departTime TIME NOT NULL,
  arriveTime TIME NOT NULL
);
CREATE TABLE StopInfo (
  stopId VARCHAR(4) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  instructions VARCHAR(1000) --how to walk to castle from stop
);