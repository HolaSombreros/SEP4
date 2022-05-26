USE [dwh]
GO

-- Create tables in the stage schema --
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id= OBJECT_ID(N'[dbo].[FactEnvironment]') AND type in (N'U'))
DROP TABLE [stage].[FactEnvironment]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id= OBJECT_ID(N'[dbo].[DimArea]') AND type in (N'U'))
DROP TABLE [stage].[DimArea]
GO


CREATE TABLE [stage].[FactEnvironment] (
 [measurement_id] INT NOT NULL,
 [area_id] INT NOT NULL,
 [measuredDate] date,
 [minute] int,
 [hour] int,
 [temperature] float,
 [humidity] float,
 [co2] int,
 [sound] float
 CONSTRAINT PK_FactEnvironment PRIMARY KEY CLUSTERED(measurement_id))

 CREATE TABLE [stage].[DimArea] (
 [area_id] INT NOT NULL,
 [name] varchar(255),
 CONSTRAINT PK_area PRIMARY KEY CLUSTERED(area_id))

 ALTER TABLE [stage].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_0 FOREIGN KEY (area_id) REFERENCES [stage].[DimArea] (area_id)