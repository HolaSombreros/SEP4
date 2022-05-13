USE dwh

CREATE TABLE [etl].[LogUpdate] (
[Table] NVARCHAR(50) NULL,
[LastLoadDate] DATETIME NULL
) ON [PRIMARY]
GO

/* The date inserted is hardcode to test the incremental load */
INSERT INTO [etl].[LogUpdate] ([Table], [LastLoadDate]) VALUES ('DimArea', '2022-05-12 23:56')
INSERT INTO [etl].[LogUpdate] ([Table], [LastLoadDate]) VALUES ('FactEnvironment', '2022-05-12 23:56')