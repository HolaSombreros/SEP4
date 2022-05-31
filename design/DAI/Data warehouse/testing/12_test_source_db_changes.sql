USE [testFarmerama]

-- New Area
INSERT INTO [dbo].[area] ([name], [barn_id])
VALUES ('Area3', 1)

-- Changed Area
UPDATE [dbo].[area]
SET name = 'Main Area'
WHERE area_id = 1

-- Deleted Area
DELETE FROM [dbo].[area] 
WHERE area_id = 2

-- New Measurement (the measure_date is tomorrow, which could not be possible in a real-life setting, 
-- but here it is just for testing purposes
-- In the etl the last load date is set to today, so it will include the record in the incremental load
INSERT INTO [dbo].[measurement] ([measured_date], [area_id], [temperature], [humidity], [co2], [sound])
VALUES (DATEADD(day, 1, GETDATE()), 1, 23.56, 40.48, 335, 74) 