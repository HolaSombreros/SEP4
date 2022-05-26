USE [dwh]

-- Create a time dimension that includes minutes since midnight and their corresponding hour, as well as the time of day --
DECLARE @Time DATETIME
SET @TIME = CONVERT(VARCHAR,'12:00:00 AM',108)

ALTER TABLE [dwh].[FactEnvironment] DROP CONSTRAINT FK_FactEnvironment_2

TRUNCATE TABLE [dwh].[DimTime]

WHILE @TIME <= '11:59:00 PM'

 BEGIN

 INSERT INTO dwh.DimTime([HoursSinceMidnight], [MinutesSinceMidnight])
 SELECT 
	DATEPART(HOUR,@Time)
  , DATEPART(MINUTE,@Time)

 SET @TIME = DATEADD(minute,1,@Time)
 END

UPDATE [dwh].[DimTime]
SET [HoursSinceMidnight] = '0' + [HoursSinceMidnight]
WHERE LEN([HoursSinceMidnight]) = 1

UPDATE [dwh].[DimTime]
SET [MinutesSinceMidnight] = '0' + [MinutesSinceMidnight]
WHERE LEN([MinutesSinceMidnight]) = 1

UPDATE [dwh].[DimTime]
SET [TimeOfDay] =   
    ( CASE  
         WHEN ([HoursSinceMidnight] BETWEEN 6 AND 11) THEN 'Morning'  
		 WHEN ([HoursSinceMidnight] BETWEEN 12 AND 17) THEN 'Afternoon' 
		 WHEN ([HoursSinceMidnight] BETWEEN 18 AND 23) THEN 'Evening' 
         ELSE ('Night')  
       END  
    )  

ALTER TABLE [dwh].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_2 FOREIGN KEY (T_ID) REFERENCES [dwh].[DimTime] (T_ID);

