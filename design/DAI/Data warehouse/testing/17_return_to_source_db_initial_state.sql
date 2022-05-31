USE testFarmerama

-- RUN TO RECOVER INITIAL STATE: --
INSERT INTO [dbo].[area] ([name], [barn_id])
VALUES ('Area2', 1)

UPDATE [dbo].[area]
SET name = 'Area1'
WHERE area_id = 1

DELETE FROM [dbo].[area] 
WHERE area_id = 3

DELETE FROM [dbo].[measurement]
 WHERE measurement_id = (SELECT t1.id
                         FROM (SELECT MAX(t2.measurement_id) AS id 
                                 FROM [dbo].[measurement] t2) t1)

