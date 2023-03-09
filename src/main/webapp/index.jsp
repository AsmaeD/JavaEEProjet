<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/CSS/style.css" rel = "stylesheet">

<title>Page d'accueil</title>
<script>
	function func(){
		var type = document.getElementsByName("status");
		if (type[0].checked) {
			document.getElementById("ventesEnCours").disabled = true;
			document.getElementById("ventesNonDebutees").disabled = true;
		 	document.getElementById("ventesTerminées").disabled = true;
		 	document.getElementById("ouvertes").disabled = false;
		 	document.getElementById("encours").disabled = false;
			document.getElementById("remportees").disabled = false;
			document.getElementById("ventesEnCours").checked = false;
			document.getElementById("ventesNonDebutees").checked = false;
		 	document.getElementById("ventesTerminées").checked = false;
			
		}
		else if (type[1].checked) {
			document.getElementById("ouvertes").disabled = true;
		 	document.getElementById("encours").disabled = true;
			document.getElementById("remportees").disabled = true;
			document.getElementById("ventesEnCours").disabled = false;
			document.getElementById("ventesNonDebutees").disabled = false;
		 	document.getElementById("ventesTerminées").disabled = false;
			document.getElementById("ouvertes").checked = false;
		 	document.getElementById("encours").checked = false;
			document.getElementById("remportees").checked = false;
		}
			
		
	}
</script>
</head>
<body>


	<%@ include file="header.jsp"%>
	
	<br></br>

	<div class = "container">	
	
		<h2 class = "text-center">Liste des enchères</h2>
		
	<!-- Affichage du formulaire  -->
	
	<h4>Filtres de recherche:</h4>	
	
	<form action="" method="post" class="">
	<div class="row flex-row">
		<div class="col-8">
			<p>
				<input type="text" name="motcle" placeholder="Le nom de l'article contient" class="col-4">
			</p>
			<p>
				<label for="categorie">Catégories</label>
				<select name="categorie" id="categorie">
					<option value="0">Toutes</option>
					<option value="1">Informatique </option>
					<option value="2">Ameublement </option>
					<option value="3">Vêtement </option>
					<option value="4">Sport&Loisirs </option>
				</select>
			</p>
				<div class="row">
						<div class="col-4 <c:if test='${empty Utilisateur}'> visually-hidden</c:if>">
								<input type="radio" name="status" value="Achats" id="Achats" checked="checked" onchange="func();" >
								<label for="Achats">Achats</label> <br>
									<div class="ms-4">	
									
										<input type="checkbox" name="ouvertes" id="ouvertes" value="2" checked="checked">
										<label for="ouvertes">enchères ouvertes</label> <br>
										<input type="checkbox" name="encours" id="encours" value="2" >
										<label for="encours">mes enchères en cours</label> <br>
										<input type="checkbox" name="remportees" id="remportees" value="3" >
										<label for="remportees">mes enchères remportées</label> <br>
										
									</div>
						</div>
						<div class="col-4 <c:if test='${empty Utilisateur}'> visually-hidden</c:if>">
								<input type="radio" name="status" value="Ventes" id="Ventes" onchange="func();">
								<label for="Ventes">Mes Ventes</label><br>	
								<div class="ms-4">
								
									<input type="checkbox" name="ventesEnCours" id="ventesEnCours" value="2" disabled="disabled">
									<label for="ventesEnCours">mes ventes en cours</label > <br>
									<input type="checkbox" name="ventesNonDebutees" id="ventesNonDebutees" value="1" disabled="disabled">
									<label for="ventesNonDebutees">ventes non débutées</label> <br>
									<input type="checkbox" name="ventesTerminées" id="ventesTerminées" value="3" disabled="disabled">
									<label for="ventesTerminées">ventes terminées</label> <br>	
								</div>
						</div>
					</div>
				</div>
			<div class="col-4 d-flex d-flex align-items-center" >
				<input type="submit" value="Rechercher" class="btn btn-primary btn-lg">
			</div>	
		</div>

	</form>
	

	
	<!-- 		Affichage bandeau vert ajout article reussi  \\ depuis servlet NouvelleVente -->
			<c:if test="${validerAjout == true}">
				<div class="container">
					<div class="alert alert-success alert-dismissible fade show" role="alert">
					  <strong>Félicitations !</strong> L'article a bien été ajouté à votre liste d'enchères, BRAVO!.
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</div>
			</c:if>	
			
	<!-- 		Affichage bandeau vert ajout enchere SUCCESSED  \\ depuis servlet DetailVente -->
			<c:if test="${!empty resultatEnchere}">
				<div class="container">
					<div class="alert alert-success alert-dismissible fade show" role="alert">
					  <strong>Hey!</strong> ${resultatEnchere}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</div>
			</c:if>	
	
		<!-- 		Affichage bandeau jaune  ajout enchere FAILED  \\ depuis servlet DetailVente -->
			<c:if test="${!empty resultatEnchereError}">
				<div class="container">
					<div class="alert alert-warning alert-dismissible fade show" role="alert">
					  <strong>Oops!</strong> ${resultatEnchereError}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</div>
			</c:if>	
			
		<!-- 		Affichage bandeau rouge  modif encheres ou suppression refusée  \\ depuis servlet ModifEnchere -->
			<c:if test="${!empty nonDroitModif}">
				<div class="container">
					<div class="alert alert-warning alert-dismissible fade show" role="alert">
					  <strong>Désolé!</strong> ${nonDroitModif}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</div>
			</c:if>	
	
	<!-- Affichage des articles card bootsrap test -->		
	
	
	<div class = "row">
		<c:forEach var="A" items="${listeEnchereEnCours}">
	
				<div class = "col-12 col-lg-6 mb-2 mt-2" >
					<div class = "card flex-row">
						<img alt="image article" src="#">
						<div class="card-body">
						<c:if test="${empty Utilisateur}">${A.getNomArticle()}<br></c:if>
						<c:if test="${!empty Utilisateur}"><a href="DetailVente?nomArticle=${A.getNomArticle()}&pseudoVendeur=${A.getPseudoUtilisateur()}"> ${A.getNomArticle()} </a><br></c:if>
						 prix : ${A.getPrixVente()} points <br>
						 Fin d'enchère: ${A.getDateFinEncheres()} <br>
						 Vendeur : <c:if test="${empty Utilisateur}">${A.getPseudoUtilisateur()} </c:if> 
						 			<c:if test="${!empty Utilisateur}"><a href="Profil?pseudo=${A.getPseudoUtilisateur()}">${A.getPseudoUtilisateur()}</a> </c:if>
						</div>
					</div>
				</div>		
		</c:forEach>
	</div>	
	</div>	
</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>


</html>