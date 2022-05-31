USE [testFarmeramaDW]
-- Reset Data Warehouse for further testing

ALTER TABLE [dwh].[FactEnvironment] DROP CONSTRAINT FK_FactEnvironment_0;
ALTER TABLE [dwh].[FactEnvironment] DROP CONSTRAINT FK_FactEnvironment_1;
ALTER TABLE [dwh].[FactEnvironment] DROP CONSTRAINT FK_FactEnvironment_2;

truncate table [dwh].[DimArea]

truncate table [dwh].[FactEnvironment]

ALTER TABLE [dwh].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_0 FOREIGN KEY (A_ID) REFERENCES [dwh].[DimArea] (A_ID)
ALTER TABLE [dwh].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_1 FOREIGN KEY (D_ID) REFERENCES [dwh].[DimDate] (D_ID)
ALTER TABLE [dwh].[FactEnvironment] ADD CONSTRAINT FK_FactEnvironment_2 FOREIGN KEY (T_ID) REFERENCES [dwh].[DimTime] (T_ID)

truncate table [etl].[LogUpdate]