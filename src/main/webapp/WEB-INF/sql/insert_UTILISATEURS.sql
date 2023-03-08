USE [TROC_ENCHERES]
GO

INSERT INTO [dbo].[UTILISATEURS]
           ([pseudo]
           ,[nom]
           ,[prenom]
           ,[email]
           ,[telephone]
           ,[rue]
           ,[code_postal]
           ,[ville]
           ,[mot_de_passe]
           ,[credit]
           ,[administrateur])
     VALUES
           ('Montesqieu'
           ,'LOULOUTTE'
           ,'Jean-Eude'
           ,'anna.louloutte@gmail.com'
           ,'0665522510'
           ,'51 rue de la chandelle'
           ,'53000'
           ,'Laval'
           ,'lolodelach235'
           ,125
           ,1)
GO


