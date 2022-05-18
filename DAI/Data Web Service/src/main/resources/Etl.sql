USE dwh;

-- Extract to stage and transform

-- Last load date used to load the latest data to the fact table */
DECLARE @LastLoadDate datetime;

SET @LastLoadDate = (SELECT MAX([LastLoadDate]) FROM [etl].[LogUpdate]
						WHERE [etl].[LogUpdate].[Table] = 'FactEnvironment');

-- Remove foreign key constraints from FactSale, because we are truncating the dim tables
ALTER TABLE [stage].[FactEnvironment] DROP CONSTRAINT FK_FactEnvironment_0;

--- Clear tables, insert data and add constraints
truncate table [stage].[DimArea];

insert into [stage].[DimArea]
([area_id]
 ,[name])
 SELECT [area_id], [name]
 FROM [source].[dbo].[area] a;

 truncate table [stage].[FactEnvironment];

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
 FROM [source].[dbo].[measurement] m
 INNER JOIN [source].[dbo].[area] a
 on m.[area_id] = a.[area_id]
 WHERE measured_date > (@LastLoadDate);

 ALTER TABLE [stage].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_0 FOREIGN KEY (area_id) REFERENCES [stage].[DimArea] (area_id);

-- Incremental Load
-- Checks for new records in the stage
-- New and future load dates.
 DECLARE @NewLoadDate INT;
 DECLARE @NewLastLoadDate DATETIME;
 SET @NewLastLoadDate = GETDATE();
 SET @NewLoadDate = CONVERT(CHAR(8), @NewLastLoadDate, 112);
-- Add one minute to the last load date to make sure it doesn't load records in that minute in the next load */
 SET @NewLastLoadDate = DATEADD(mi, 1, @NewLastLoadDate);


 DECLARE @FutureDate INT;
 SET @FutureDate = 99990101;

 -- start get added
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
 WHERE area_id in (SELECT area_id
 FROM stage.DimArea except select AreaId
 FROM dwh.DimArea
 Where ValidTo = 99990101);


-- Checks for changed records in the stage

 -- Here we find only the changed records and put them into a temporary table (tmp)
 SELECT [area_id], -- get all records in stage
 [name]
 INTO #tmp
 FROM stage.DimArea EXCEPT SELECT AreaId, --except the ones that already exist in the dw and have a valid date that is in the future
 [Name]
 FROM [dwh].[DimArea]
 WHERE ValidTo=99990101
 --then except new records, because we are only interested in changed records
 EXCEPT
 SELECT [area_id],
 [name]
 FROM stage.DimArea
 WHERE area_id IN (SELECT area_id
 FROM stage.DimArea EXCEPT SELECT [AreaId]
 FROM dwh.DimArea
 WHERE ValidTo=99990101);

 -- Insert into edw table only the changed records inserted into the tmp table
 INSERT INTO dwh.DimArea (AreaId,
 Name,
 ValidFrom,
 ValidTo)
 SELECT
 area_id,
 name,
 @NewLoadDate,
 @FutureDate
 from #tmp;

 -- Update the date of the changed records in the edw to the new load date
 UPDATE dwh.DimArea
 SET ValidTo=@NewLoadDate-1
 where AreaId in (
 SELECT [area_id]
 from #tmp) and dwh.DimArea.ValidFrom<@NewLoadDate;

 drop table if exists #tmp;

-- Checks for deleted records in the stage
-- Update the date on the deleted record in the source table in the dw
 UPDATE dwh.DimArea
 SET ValidTo=@NewLoadDate-1
 WHERE AreaId in (
 SELECT AreaId
 FROM dwh.DimArea
 WHERE AreaId in ( SELECT AreaId FROM dwh.DimArea
 EXCEPT SELECT area_id
 FROM stage.DimArea)) and ValidTo=99990101;

 INSERT INTO etl.LogUpdate ([Table], [LastLoadDate]) VALUES ('DimArea', @NewLastLoadDate);

 INSERT INTO [dwh].[FactEnvironment]
  ([A_ID]
  ,[D_ID]
  ,[T_ID]
  ,[temperature]
  ,[humidity]
  ,[co2])
  SELECT a.[A_ID]
  ,d.[D_ID]
  ,t.[T_ID]
  ,f.[temperature]
  ,f.[humidity]
  ,f.[co2]
  FROM [stage].[FactEnvironment] f
  inner join [dwh].[DimArea] as a
  on a.AreaId = f.area_id
  inner join [dwh].[DimDate] as d
  on d.Date = f.measuredDate
  inner join [dwh].[DimTime] as t
  on t.[minute] = f.[minute] AND t.[hour] = f.[hour]
  WHERE a.ValidTo=99990101;

 INSERT INTO etl.LogUpdate ([Table], [LastLoadDate]) VALUES ('FactEnvironment', @NewLastLoadDate);