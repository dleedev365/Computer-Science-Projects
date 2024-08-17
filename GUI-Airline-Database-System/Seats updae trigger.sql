
CREATE TRIGGER seatsUpdate
ON dbo.Booking AFTER INSERT, DELETE
AS
BEGIN
	DECLARE @fc CHAR(6)
	DECLARE @depart_d DATE
	DECLARE @pid INTEGER
	
	IF EXISTS(SELECT * FROM inserted)
	BEGIN
		SELECT * INTO #t_inserted FROM inserted
		WHILE(EXISTS(SELECT flight_code FROM #t_inserted))
			BEGIN
				SELECT TOP 1 @fc = flight_code, @pid = passenger_id, @depart_d = depart_date FROM #t_inserted
				UPDATE Flight_Instance SET available_seats = available_seats - 1
				WHERE flight_code = @fc AND depart_date = @depart_d
				DELETE FROM #t_inserted WHERE flight_code = @fc AND passenger_id = @pid AND depart_date = @depart_d
			END
	END
	
	IF EXISTS(SELECT * FROM deleted)
	BEGIN
		SELECT * INTO #t_deleted FROM deleted
		WHILE(EXISTS(SELECT flight_code FROM #t_deleted))
			BEGIN
				SELECT TOP 1 @fc = flight_code, @pid = passenger_id, @depart_d = depart_date FROM #t_deleted
				UPDATE Flight_Instance SET available_seats = available_seats + 1
				WHERE flight_code = @fc AND depart_date = @depart_d
				DELETE FROM #t_deleted WHERE flight_code = @fc AND passenger_id = @pid AND depart_date = @depart_d
			END
	END
END

--drop trigger seatsUpdate;