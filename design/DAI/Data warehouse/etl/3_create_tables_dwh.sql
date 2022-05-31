USE [dwh]

-- Create tables in dwh schema --
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id= OBJECT_ID(N'[dwh].[FactEnvironment]') AND type in (N'U'))
DROP TABLE [dwh].[FactEnvironment]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id= OBJECT_ID(N'[dwh].[DimArea]') AND type in (N'U'))
DROP TABLE [dwh].[DimArea]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id= OBJECT_ID(N'[dwh].[DimDate]') AND type in (N'U'))
DROP TABLE [dwh].[DimDate]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id= OBJECT_ID(N'[dwh].[DimTime]') AND type in (N'U'))
DROP TABLE [dwh].[DimTime]
GO

CREATE TABLE [dwh].[DimDate] (
 [D_ID] INT NOT NULL,
 [Date] DATETIME NOT NULL,
 [Day] INT NOT NULL,
 [Month] INT NOT NULL,
 [MonthName] VARCHAR(20) NOT NULL,
 [Week] INT NOT NULL,
 [Quarter] INT NOT NULL,
 [Year] INT NOT NULL,
 [DayOfWeek] INT NOT NULL,
 [WeekdayName] VARCHAR(20) NOT NULL,
 CONSTRAINT [PK_DimDate] PRIMARY KEY CLUSTERED
 (D_ID ASC)
 WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON) ON [PRIMARY]
 ) ON [PRIMARY]
 GO

CREATE TABLE [dwh].[DimArea] (
 [A_ID] INT IDENTITY NOT NULL,
 [AreaId] INT,
 [Name] VARCHAR(50),
 [ValidTo] INT,
 [ValidFrom] INT
 CONSTRAINT PK_DimArea PRIMARY KEY CLUSTERED(A_ID))

CREATE TABLE [dwh].[DimTime] (
 [T_ID] INT IDENTITY NOT NULL,
 [MinutesSinceMidnight] INT,
 [HoursSinceMidnight] INT,
 [TimeOfDay] VARCHAR(10)
 CONSTRAINT PK_DimTime PRIMARY KEY CLUSTERED(T_ID))

CREATE TABLE [dwh].[FactEnvironment] (
 [A_ID] INT NOT NULL,
 [D_ID] INT NOT NULL,
 [T_ID] INT NOT NULL,
 [Temperature] float,
 [Humidity] float,
 [CO2] int,
 [Sound] float
)

ALTER TABLE [dwh].[FactEnvironment] ADD CONSTRAINT PK_FactEnvironment PRIMARY KEY (A_ID, D_ID, T_ID)

ALTER TABLE [dwh].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_0 FOREIGN KEY (A_ID) REFERENCES [dwh].[DimArea] (A_ID)
ALTER TABLE [dwh].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_1 FOREIGN KEY (D_ID) REFERENCES [dwh].[DimDate] (D_ID)
ALTER TABLE [dwh].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_2 FOREIGN KEY (T_ID) REFERENCES [dwh].[DimTime] (T_ID)

