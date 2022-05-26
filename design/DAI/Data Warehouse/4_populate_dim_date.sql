USE [dwh]

-- Populate DimDate --

ALTER TABLE [dwh].[FactEnvironment] DROP CONSTRAINT FK_FactEnvironment_1;

truncate table [dwh].[DimDate]

DECLARE @StartDate DATE;
DECLARE @EndDate DATE;

-- Date of the first record in the source sn --
SET @StartDate = '2021-04-01'

-- Add only 2, otherwise it takes too long to tun it in the cloud. But the value should be larger for the final draft. --
SET @EndDate = DATEADD(YEAR, 2, GETDATE())

WHILE @StartDate <= @EndDate
    BEGIN
    INSERT INTO [dwh].[DimDate]
    ([D_ID],
		[Date],
		[Day],
		[Month],
		[MonthName],
		[Week],
		[Quarter],
	    [Year],
		[DayOfWeek],
		[WeekdayName]
		)
	SELECT
	 CONVERT(CHAR(8), @StartDate, 112) as D_ID,
	 @StartDate as [Date],
	 DATEPART(day, @StartDate) as Day,
	 DATEPART(month, @StartDate) as Month,
	 DATENAME(month, @StartDate) as MonthName,
	 DATEPART(week, @StartDate) as Week,
	 DATEPART(quarter, @StartDate) as Quarter,
	 DATEPART(year, @StartDate) as Year,
	 DATEPART(weekday, @StartDate) as DayOfWeek,
	 DATENAME(weekday, @StartDate) as WeekdayName

	SET @StartDate = DATEADD(dd, 1, @StartDate)
END

ALTER TABLE [dwh].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_1 FOREIGN KEY (D_ID) REFERENCES [dwh].[DimDate] (D_ID);