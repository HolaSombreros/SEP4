USE testFarmerama

-- Populate Test Source DB --

SET IDENTITY_INSERT [dbo].[barn] ON

INSERT INTO [dbo].[barn] (
	[barn_id],
	[name]
	)
	SELECT 
	[barn_id],
	[name]
	FROM [farmerama].[dbo].[barn]

SET IDENTITY_INSERT [dbo].[barn] OFF


SET IDENTITY_INSERT [dbo].[area] ON

INSERT INTO [dbo].[area] (
	[area_id],
	[name],
	[barn_id]
	)
	SELECT 
	[area_id],
	[name],
	[barn_id]
	FROM [farmerama].[dbo].[area]

SET IDENTITY_INSERT [dbo].[area] OFF


SET IDENTITY_INSERT [dbo].[measurement] ON

INSERT INTO [dbo].[measurement] (
	[measurement_id],
	[area_id],
	[measured_date],
	[temperature],
	[humidity],
	[co2],
	[sound]
	)
	SELECT 
	[measurement_id],
	[area_id],
	[measured_date],
	[temperature],
	[humidity],
	[co2],
	[sound]
	FROM [farmerama].[dbo].[measurement]
	WHERE ([measurement_id] % 100) = 66

SET IDENTITY_INSERT [dbo].[measurement] OFF