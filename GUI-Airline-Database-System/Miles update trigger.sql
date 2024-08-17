-- MilesUpdateTriggers
CREATE TRIGGER milesUpdate
ON dbo.Booking AFTER INSERT, DELETE, UPDATE
AS
BEGIN
	UPDATE passenger set miles = td.newMiles FROM
	(SELECT p.passenger_id,
		 p.miles + SUM(F.distance) AS newMiles
	FROM passenger p INNER JOIN inserted i
	ON i.passenger_id = p.passenger_id
	INNER JOIN Flight F ON F.flight_code = i.flight_code
	GROUP BY p.passenger_id, p.miles) AS td
	WHERE passenger.passenger_id = td.passenger_id
	
	UPDATE passenger SET miles = td.newMiles FROM
	(SELECT p.passenger_id,
		 p.miles - SUM(F.distance) AS newMiles
	FROM passenger p INNER JOIN deleted i
	ON i.passenger_id = p.passenger_id
	INNER JOIN Flight F ON F.flight_code = i.flight_code
	GROUP BY p.passenger_id, p.miles) AS td
	WHERE passenger.passenger_id = td.passenger_id
END