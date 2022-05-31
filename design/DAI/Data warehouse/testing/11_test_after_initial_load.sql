/* Fact Environment Test Case */
SELECT COUNT(*) AS CountOnMeasurementSource
FROM [testFarmerama].[dbo].[measurement]

SELECT COUNT(*) AS CountOnFactDWH
FROM [testFarmeramaDW].[dwh].[FactEnvironment]

/* Number of customers Test Case */
SELECT COUNT(*) AS CountAreaSource
FROM [testFarmerama].[dbo].[area]

SELECT COUNT(*) AS CountAreaDW
FROM  [testFarmeramaDW].[dwh].[DimArea]