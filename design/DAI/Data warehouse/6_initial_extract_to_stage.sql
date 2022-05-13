USE [dwh]
GO

/**** Extract to stage and transform ****/

ALTER TABLE [stage].[FactEnvironment] DROP CONSTRAINT FK_FactEnvironment_0;

/**** Clear tables, insert data and add constraints. ****/
truncate table [stage].[DimArea]

insert into [stage].[DimArea]
([area_id]
 ,[name])
 SELECT [area_id], [name]
 FROM [source].[dbo].[area] a

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
 FROM [source].[dbo].[measurement] m
 INNER JOIN [source].[dbo].[area] a
 on a.[area_id] = m.[area_id]
 -- To test the incremental load, we only load data until this date
WHERE m.measured_date < '2022-05-13'


ALTER TABLE [stage].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_0 FOREIGN KEY (area_id) REFERENCES [stage].[DimArea] (area_id);

