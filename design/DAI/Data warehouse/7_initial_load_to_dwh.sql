USE dwh
/**** Load to dwh ****/
ALTER TABLE [dwh].[FactEnvironment] DROP CONSTRAINT FK_FactEnvironment_0;

truncate table [dwh].[DimArea]

insert into [dwh].[DimArea]
([AreaId]
 ,[Name])
 SELECT [area_id]
 ,[Name]
 FROM [stage].[DimArea]

ALTER TABLE [dwh].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_0 FOREIGN KEY (A_ID) REFERENCES [dwh].[DimArea] (A_ID);

 truncate table [dwh].[FactEnvironment]

 insert into [dwh].[FactEnvironment]
 ([A_ID]
 ,[D_ID]
 ,[T_ID]
 ,[temperature]
 ,[humidity]
 ,[co2]
 ,[sound])
 SELECT a.[A_ID]
 ,d.[D_ID]
 ,t.[T_ID]
 ,f.[temperature]
 ,f.[humidity]
 ,f.[co2]
 ,f.[sound]
 FROM [stage].[FactEnvironment] f
 inner join [dwh].[DimArea] as a
 on a.AreaId = f.area_id
 inner join [dwh].[DimDate] as d
 on d.Date = f.measuredDate
 inner join [dwh].[DimTime] as t
 on t.[minute] = f.[minute] AND t.[hour] = f.[hour]


UPDATE [dwh].DimArea
SET ValidFrom = 20220512, ValidTo = 99990101
GO