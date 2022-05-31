USE [dwh]

-- Create LogUpdate table --
CREATE TABLE [etl].[LogUpdate] (
[Table] VARCHAR(50) NULL,
[LastLoadDate] DATETIME NULL
) ON [PRIMARY]
GO

-- The initial date inserted is the date of the first record in the source database --
INSERT INTO [etl].[LogUpdate] ([Table], [LastLoadDate]) VALUES ('DimArea', '2021-04-01 00:00')
INSERT INTO [etl].[LogUpdate] ([Table], [LastLoadDate]) VALUES ('FactEnvironment', '2021-04-01 00:00')