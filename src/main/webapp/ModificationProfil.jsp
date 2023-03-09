<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification du profil</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/CSS/style.css" rel = "stylesheet" >

</head>

	

<body>
	<div class = "container">	
			<div class = "header">
				<a href="Accueil" class = "fs-2  text-reset text-decoration-none">ENI - ENCHERES</a>
			</div>
		</div>
		
	<h1 class=" InscriptionTitre text-center">Mon profil</h1>
	
		

		<form name="FormModifProfi" action="ModifProfil" method="POST">
			<fieldset>
			
			<c:if test="${validationMdp == true}">
					<div 	class="alert alert-danger" role="alert">
						Les mots de passe ne sont pas identiques, veuillez réssayer.
					</div>
			</c:if>
			
			<c:if test="${validationMdpAc == true}">
					<div 	class="alert alert-danger" role="alert">
						Le mot de passe actuel n'est pas le bon, veuillez réssayer.
					</div>
			</c:if>
			
			<c:if test="${verifMail == true}">
					<div 	class="alert alert-danger" role="alert">
						pseudo ou mail déjà existant, veuillez réssayer.
					</div>
			</c:if>
			
			<c:if test="${erreurvide == true}">
					<div 	class="alert alert-danger" role="alert">
						Attention une case du formulaire était vide (hors nouveau mot de passe).
					</div>
			</c:if>
			
				<div class="InscriptionContenu justify-content-center d-flex flex-row">
					<div class="InscritpionDivGauche d-flex flex-column">
					
							<p> <label for="pseudo">Pseudo : </label>
							<input type="text" name="pseudo" id="pseudo" value="${utilisateurProfil.getPseudo()}" autofocus required="required"></p>
							
							<p> <label for="prenom">Prénom : </label> 
							<input type="text" name="prenom" id="prenom" value="${utilisateurProfil.getPrenom()}" required="required"> </p>
							
							<p> <label for="tel">Téléphone : </label> 
							<input type="text" name="tel" id="tel" value="${utilisateurProfil.getTelephone()}" required="required"> </p>
							
							<p> <label for="cp">Code Postal : </label> 
							<input type="text" name="cp" id="cp" value="${utilisateurProfil.getCodePostale()}" required="required"> </p>
							
							<p> <label for="mdp">Mot de passe actuel : </label> 
							<input type="password" name="mdp" id="mdp" required="required"> </p>
							
							<p> <label for="mdp"> Nouveau mot de passe : </label> 
							<input type="password" name="newmdp" id="newmdp" > </p>
							
						</div>
						<div class="InscritpionDivDroite d-flex flex-column">
							<p> <label for="nom">Nom : </label>
							<input type="text" name="nom" id="nom" autofocus value="${utilisateurProfil.getNom()}" required="required"></p>
							
							<p> <label for="email">Email : </label> 
							<input type="email" name="email" id="email" required value="${utilisateurProfil.getEmail()}" required="required"> </p>
							
							<p> <label for="rue">Rue : </label> 
							<input type="text" name="rue" id="rue" value="${utilisateurProfil.getRue()}" required="required"> </p>
							
							<p> <label for="ville">Ville : </label> 
							<input type="text" name="ville" id="ville" value="${utilisateurProfil.getVille()}" required="required"> </p>
							
							<p> <label for="ville"></label> 
							
							<p> <label for="confirm">Confirmation : </label> 
							<input type="password" name="confirm" id="confirm" > </p>								
						</div>
						
					</div>
				
					<div class= "InscriptionBouttons">
						<div class="Inscriptiondivbtna">				
							<a href="<%=request.getContextPath()%>/Accueil">
		                       <input type="submit" value="Enregistrer" class="btn btn-primary mb-2 InscriptionBtnEspace"/></a>
							</div>
						
		 			
							<div class="Inscriptiondivbtnb">
								<a href="<%=request.getContextPath()%>/Suppression">
		                       <input type="button" value="Supprimer mon compte" class="btn btn-primary mb-2"/></a>
							</div>
					
						</div>
					
			</fieldset>
		
		</form>	
		
</body>


</html>