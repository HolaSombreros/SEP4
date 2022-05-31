USE [dwh]

-- Incremental Load and Type 2 Changes --

-- New and future load dates.
DECLARE @NewLoadDate INT
DECLARE @NewLastLoadDate DATETIME
SET @NewLastLoadDate = GETDATE()
SET @NewLoadDate = CONVERT(CHAR(8), GETDATE(), 112)
-- Add one minute to the last load date to make sure it doesn't load records in that minute in the next load --
SET @NewLastLoadDate = DATEADD(mi, 1, @NewLastLoadDate)

DECLARE @FutureDate INT
SET @FutureDate = 99990101

-- Checks for new records in the stage --
INSERT INTO [dwh].[DimArea] (
[AreaId],
[Name],
[ValidFrom],
[ValidTo])
SELECT
[area_id],
[name],
@NewLoadDate,
@FutureDate
FROM [stage].[DimArea]

WHERE [area_id] in (SELECT [area_id]
FROM [stage].[DimArea] EXCEPT SELECT AreaId
FROM [dwh].[DimArea]
WHERE ValidTo = 99990101)

-- Checks for changed records in the stage --

-- Here we find only the changed records and put them into a temporary table (tmp)
SELECT [area_id], -- get all records in stage
[name]
INTO #tmp
FROM [stage].[DimArea] EXCEPT SELECT [AreaId], --except the ones that already exist in the dw and have a valid date that is in the future
[Name]
FROM [dwh].[DimArea]
WHERE [ValidTo] = 99990101
--then except new records, because we are only interested in changed records
EXCEPT
SELECT [area_id],
[name]
FROM [stage].[DimArea]
WHERE [area_id] IN (SELECT [area_id]
FROM [stage].[DimArea] EXCEPT SELECT [AreaId]
FROM [dwh].[DimArea]
WHERE [ValidTo] = 99990101)

-- Insert into edw table only the changed records inserted into the tmp table --
INSERT INTO [dwh].[DimArea] ([AreaId],
[Name],
[ValidFrom],
[ValidTo])
SELECT
[area_id],
[name],
@NewLoadDate,
@FutureDate
FROM #tmp

-- Update the date of the changed records in the edw to the new load date --
UPDATE [dwh].[DimArea]
SET [ValidTo] = @NewLoadDate - 1
WHERE [AreaId] in (
SELECT [area_id]
FROM #tmp) and [dwh].[DimArea].[ValidFrom] < @NewLoadDate

DROP TABLE IF EXISTS #tmp

-- Checks for deleted records in the stage --
-- Update the date on the deleted record in the source table in the dw
UPDATE [dwh].[DimArea]
SET [ValidTo] = @NewLoadDate - 1
WHERE [AreaId] in (
SELECT [AreaId]
FROM [dwh].[DimArea]
WHERE [AreaId] in ( SELECT [AreaId] FROM [dwh].[DimArea]
EXCEPT SELECT [area_id]
FROM [stage].[DimArea])) 
AND [ValidTo] = 99990101

INSERT INTO [etl].[LogUpdate] ([Table], [LastLoadDate]) VALUES ('DimArea', @NewLastLoadDate)

INSERT INTO [dwh].[FactEnvironment]
 ([A_ID]
 ,[D_ID]
 ,[T_ID]
 ,[Temperature]
 ,[Humidity]
 ,[CO2]
 ,[Sound])
 SELECT a.[A_ID]
 ,d.[D_ID]
 ,t.[T_ID]
 ,f.[temperature]
 ,f.[humidity]
 ,f.[co2]
 ,f.[sound]
 FROM [stage].[FactEnvironment] f
 LEFT JOIN [dwh].[DimArea] AS a
 ON a.[AreaId] = f.[area_id]
 LEFT JOIN [dwh].[DimDate] AS d
 ON d.[Date] = f.[measuredDate]
 LEFT JOIN [dwh].[DimTime] AS t
 ON t.[MinutesSinceMidnight] = f.[minute] AND t.[HoursSinceMidnight] = f.[hour]
 WHERE a.[ValidTo] = 99990101

INSERT INTO [etl].[LogUpdate] ([Table], [LastLoadDate]) VALUES ('FactEnvironment', @NewLastLoadDate)