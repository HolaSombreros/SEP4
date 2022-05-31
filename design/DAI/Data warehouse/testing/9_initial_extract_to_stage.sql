USE [testFarmeramaDW]
GO

-- New load date. This will be used in the incremental loads.
DECLARE @NewLoadDate INT;
DECLARE @NewLastLoadDate DATETIME;
SET @NewLastLoadDate = GETDATE();
SET @NewLoadDate = CONVERT(CHAR(8), @NewLastLoadDate, 112);
-- Add one minute to the last load date to make sure it doesn''t load records in that minute in the next load */
SET @NewLastLoadDate = DATEADD(mi, 1, @NewLastLoadDate);

-- Extract to stage and transform --

ALTER TABLE [stage].[FactEnvironment] DROP CONSTRAINT FK_FactEnvironment_0;

-- Clear tables, insert data and add constraints. --
truncate table [stage].[DimArea]

insert into [stage].[DimArea]
([area_id]
 ,[name])
 SELECT [area_id], [name]
 FROM [testFarmerama].[dbo].[area] a

INSERT INTO [etl].[LogUpdate] ([Table], [LastLoadDate])
VALUES ('DimArea', @NewLastLoadDate);

 truncate table [stage].[FactEnvironment]

 insert into [stage].[FactEnvironment]
 ([measurement_id]
 ,[area_id]
 ,[measuredDate]
 ,[minute]
 ,[hour]
 ,[temperature]
 ,[humidity]
 ,[co2]
 ,[sound])
 SELECT m.[measurement_id], a.[area_id],[measured_date], DATEPART(minute, [measured_date]), DATEPART(hour, [measured_date]), [temperature], [humidity], [co2], [sound]
 FROM [testFarmerama].[dbo].[measurement] m
 INNER JOIN [testFarmerama].[dbo].[area] a
 on a.[area_id] = m.[area_id]

-- Transformation: because the devices can send data within the same minute, there will be duplicate key issues
-- in the dwh schema if the records with same time are not removed (at least one, but here we are deleting both)
DELETE FROM stage.FactEnvironment
WHERE CONCAT(measuredDate, '.' ,minute, '.', hour) in
(SELECT CONCAT(measuredDate, '.' ,minute, '.', hour) FROM stage.FactEnvironment
GROUP BY CONCAT(measuredDate, '.' ,minute, '.', hour)
HAVING COUNT(*) > 1)

-- Transformation: Validate that each measurement is within the acceptable validation range.
-- If not, substitute the measurement for the average of the measurements in the extracted table
UPDATE [stage].[FactEnvironment]
SET [temperature] =
	(SELECT ROUND(AVG(temperature), 2)
	FROM [stage].[FactEnvironment]
	WHERE [temperature] BETWEEN -20 AND 60)
WHERE [temperature] is null OR [temperature] NOT BETWEEN -20 AND 60;

UPDATE [stage].[FactEnvironment]
SET [humidity] =
	(SELECT ROUND(AVG(humidity), 2)
	FROM [stage].[FactEnvironment]
	WHERE [humidity] BETWEEN 0 AND 100)
WHERE [humidity] is null OR [humidity] NOT BETWEEN 0 AND 100;

UPDATE [stage].[FactEnvironment]
SET [co2] =
	(SELECT ROUND(AVG(co2), 2)
	FROM [stage].[FactEnvironment]
	WHERE [co2] BETWEEN 50 AND 3000)
WHERE [co2] is null OR [co2] NOT BETWEEN 50 AND 3000;

UPDATE [stage].[FactEnvironment]
SET [sound] =
	(SELECT ROUND(AVG(sound), 2)
	FROM [stage].[FactEnvironment]
	WHERE [sound] BETWEEN 0 AND 150)
WHERE [sound] is null OR [sound] NOT BETWEEN 0 AND 150;

ALTER TABLE [stage].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_0 FOREIGN KEY (area_id) REFERENCES [stage].[DimArea] (area_id);

INSERT INTO [etl].[LogUpdate] ([Table], [LastLoadDate])
VALUES ('FactEnvironment', @NewLastLoadDate);