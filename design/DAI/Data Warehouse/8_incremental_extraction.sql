USE [dwh]

/**** Extract to stage and transform ****/

/* Last load date used to load the latest data to the fact table */
DECLARE @LastLoadDate datetime

SET @LastLoadDate = (SELECT MAX([LastLoadDate]) FROM [etl].[LogUpdate]
						WHERE [etl].[LogUpdate].[Table] = 'FactEnvironment')

/**** Remove foreign key constraints from FactSale, because we are truncating the dim tables ****/
ALTER TABLE [stage].[FactEnvironment] DROP CONSTRAINT FK_FactEnvironment_0;

/**** Clear tables, insert data and add constraints. ****/
truncate table [stage].[DimArea]

insert into [stage].[DimArea]
([area_id]
 ,[name])
 SELECT [area_id], [name]
 FROM [farmerama].[dbo].[area] a

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
 FROM [farmerama].[dbo].[measurement] m
 INNER JOIN [farmerama].[dbo].[area] a
 on m.[area_id] = a.[area_id]
 WHERE measured_date > (@LastLoadDate)

 -- Transformation: because the devices can send data within the same minute, there will be duplicate key issues
-- in the dwh schema if the records with same time are not removed (at least one, but here we are deleting both)
DELETE FROM stage.FactEnvironment
WHERE CONCAT(measuredDate, '.' ,minute, '.', hour) in
(SELECT CONCAT(measuredDate, '.' ,minute, '.', hour) FROM stage.FactEnvironment
GROUP BY CONCAT(measuredDate, '.' ,minute, '.', hour)
HAVING COUNT(*) > 1)

ALTER TABLE [stage].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_0 FOREIGN KEY (area_id) REFERENCES [stage].[DimArea] (area_id)



