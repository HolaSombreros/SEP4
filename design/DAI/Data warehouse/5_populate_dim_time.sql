USE [dwh]

DECLARE @Time DATETIME

SET @TIME = CONVERT(VARCHAR,'12:00:00 AM',108)

ALTER TABLE [dwh].[FactEnvironment] DROP CONSTRAINT FK_FactEnvironment_2; /*T_ID*/


TRUNCATE TABLE dwh.DimTime

WHILE @TIME <= '11:59:00 PM'

 BEGIN

 INSERT INTO dwh.DimTime([hour], [minute])

 SELECT

 DATEPART(HOUR,@Time)
 AS [Hour]

 , DATEPART(MINUTE,@Time) [Minute]

 SET @TIME = DATEADD(minute,1,@Time)

 END

UPDATE dwh.DimTime

SET [HOUR] = '0' + [HOUR]

WHERE LEN([HOUR]) = 1

UPDATE dwh.DimTime

SET [MINUTE] = '0' + [MINUTE]

WHERE LEN([MINUTE]) = 1


ALTER TABLE [dwh].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_2 FOREIGN KEY (T_ID) REFERENCES [dwh].[DimTime] (T_ID);