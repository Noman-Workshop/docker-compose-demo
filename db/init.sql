-- server: mssql

-- use the master database
USE master;
GO

-- create a database tax
CREATE DATABASE tax;
GO

-- create a login nbr with password nbr@Tax123
CREATE LOGIN nbr WITH PASSWORD = 'nbr@Tax123';
GO

-- use the tax database
USE tax;
GO

-- create a user nbr
CREATE USER nbr FOR LOGIN nbr;

-- make nbr owner of the tax database
EXEC sp_addrolemember 'db_owner', 'nbr';
GO
