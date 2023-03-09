Use [TROC_ENCHERES]
GO

IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'troch_encheres_user')
BEGIN
    CREATE USER [troch_encheres_user] FOR LOGIN [troch_encheres_user]
    EXEC sp_addrolemember N'db_owner', N'troch_encheres_user'
END;
GO