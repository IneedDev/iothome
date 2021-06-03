CREATE TABLE sensor_data (
id INT NOT NULL IDENTITY PRIMARY KEY,
sensor_id varchar (255),
temperature varchar(255),
humidity varchar(255),
dateCreated DATETIME NOT NULL DEFAULT(GETDATE())
);

TRUNCATE TABLE sensor_data;
drop table sensor_data ;