<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification de l'enchère</title>
</head>
<body>

<header>
<h1><a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a></h1>
<div style="text-align: center;">
<h2 class="center">Modification de l'enchère</h2>
</div>
</header>
<div class="container">
			<c:if test="${validateDateDebut == false}">
				<p>Les dates ne sont pas conformes :</p>
<p>- La date de début de l'enchère doit être postérieure à la date du </p>
<p>- La date de fin de l'enchère doit être postérieure à la date de début de l'enchère</p>
			</c:if>			
			
			<form action="ModifEnchere" method="POST" id="formNouvelleVente">
				
					 <div class="form-group row">
		   				<label for="nomArticle" class="col-sm-2 col-form-label">Article</label>
			   			<div class="col-sm-10">
			     			<input type="text" class="form-control" id="nomArticle" name="nomArticle" maxlength="30" required="required" readonly="readonly" value="${articleAModifier.nomArticle }">
			    		</div>
		  			</div>
		  			
					<div class="form-group row">
		   				<label for="description" class="col-sm-2 col-form-label">Description</label>
			   			<div class="col-sm-10">
			     			<textarea class="form-control" id="description"  rows="5" cols="40" name="description"  required="required">${articleAModifier.description}</textarea>
			    		</div>
		  			</div>
		  			
		  			<div class="form-group row">
		   				 <label for="categorie" class="col-sm-2 col-form-label">Categorie</label>
			   				 <div class="col-sm-10">
								<select class="custom-select" id="categorie" name="categorie">
									<option value="${categorieArticleSelected}" autofocus="autofocus">${categorieArticleSelected}</option>
								    <c:forEach var="cat" items="${listeDeCategories}">
								    	<c:if test="${cat.libelle != categorieArticleSelected}">	
								    		<option value="${cat.libelle}">${cat.libelle}</option>
								    	</c:if>	
								     </c:forEach>
								 </select>
			    			</div>
		  			</div>
		  			
<!-- 		  			Affichage de l'image si il y en a une -->
		  			<c:if test="${!empty image}">
		  				<div>
		  					<img alt="image de l'article" src="${image}">
		  					${image}
		  				</div>
		  			</c:if>
		  					  			
		  			
		  			
		  			<div class="form-group row">
		   				<label for="image" class="col-sm-2 col-form-label">Photo de l'article</label>
			   			<div class="col-sm-10">
							<input type="file" name="image" class="btn btn-primary mb-2"/>
			    		</div>
		  			</div>
		  			
		  			<div class="form-group row">
		   				 <label for="map" class="col-sm-2 col-form-label">Mise à prix</label>
			   			 <div class="col-sm-10">
			     			 <input type="number" class="form-control" id="map" name="map" min="0"  required="required" value="${articleAModifier.miseAPrix}">
			    		</div>
		  			</div>
		  			
					<div class="form-group row">
		   				 <label for="dateDebutEncheres" class="col-sm-2 col-form-label">Début de l'enchère</label>
			   			<div class="col-sm-10">
			     			 <input type="date" class="form-control" id="dateDebutEncheres" name="dateDebutEnchere"  required="required" value="${articleAModifier.dateDebutEncheres }">
			    		</div>
		  			</div>
		  			
		  			<div class="form-group row">
		   				 <label for="dateFinEncheres" class="col-sm-2 col-form-label">Fin de l'enchère</label>
			   				 <div class="col-sm-10">
			     				 <input type="date" class="form-control" id="dateFinEncheres" name="dateFinEnchere" required="required" value="${articleAModifier.dateFinEncheres }">
			    			</div>
		  			</div>
		  			
					<fieldset form="formNouvelleVente">
					
						<legend>Retrait</legend>
							<div class="form-group row">
			   				 <label for="nomRue" class="col-sm-2 col-form-label">Rue: </label>
				   				 <div class="col-sm-10">
				     				 <input type="text" class="form-control" id="nomRue" name="nomRue" required="required" value="${retrait.rue }">
				    			</div>
			  				</div>
			  			
							<div class="form-group row">
			   				 <label for="codePostal" class="col-sm-2 col-form-label">Code postal: </label>
				   				 <div class="col-sm-10">
				     				 <input type="text" class="form-control" id="codePostal" name="codePostal" required="required" value="${retrait.codePostal }">
				    			</div>
			  				</div>
			  			
							<div class="form-group row">
			   				 <label for="ville" class="col-sm-2 col-form-label">Ville: </label>
				   				 <div class="col-sm-10">
				     				 <input type="text" class="form-control" id="ville" name="ville" required="required" value="${retrait.ville }">
				    			</div>
			  				</div>
						
					</fieldset>
				<input type="submit" value="Enregistrer" class="btn btn-primary mb-2">
				<a href="/DetailVente?pseudoVendeur=${user.nom}">
		   			<input type="button" value="Annuler" class="btn btn-primary mb-2"/>
				</a>
			</form>
</div>
</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</html>