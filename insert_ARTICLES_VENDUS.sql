USE [TROC_ENCHERES]
GO

INSERT INTO [dbo].[ARTICLES_VENDUS]
           ([nom_article]
           ,[description]
           ,[date_debut_encheres]
           ,[date_fin_encheres]
           ,[prix_initial]
           ,[prix_vente]
           ,[no_utilisateur]
           ,[no_categorie])
     VALUES
           ('Cahier brouillon rempli'
           ,'Je vends un cahier de brouillon auquel je tiens beaucoup. Je   me trouve dans l"obligation de m"en séparer suite à des poursuites judiciaires. Il reste 3 pages blanches'
           ,1677593978
           ,1677594014
           ,125
           ,133
           ,1
           ,1)
GO


