<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/CSS/style.css" rel = "stylesheet">
<title>Nouvelle Vente</title>
</head>

<body>

<div class="container">
	<header>
		<div class="header">
			<a href="Accueil" class = "fs-2  text-reset text-decoration-none">ENI - ENCHERES</a>
		</div>
		<div style="text-align: center;">
		<h2 class="center">Nouvelle vente</h2>
		</div>
	</header>


		
			<c:if test="${validateDateDebut == false}">
					<div 	class="alert alert-danger" role="alert">
						Les dates ne sont pas conformes :<br>
						- La date de début de l'enchère doit être postérieure à la date du jour<br>
						- La date de fin de l'enchère doit être postérieure à la date de début de l'enchère
					</div>
			</c:if>
			
			
			<form action="NouvelleVente" method="post" id="formNouvelleVente">
				
					 <div class="form-group row">
		   				 <label for="nomArticle" class="col-sm-2 col-form-label">Article</label>
			   				 <div class="col-sm-10">
			     				 <input type="text" class="form-control" id="nomArticle" name="nomArticle" maxlength="30" required="required" 
			     				 <c:if test="${!empty newArticle }">
			     				 	value="${newArticle.nomArticle }"
			     				 </c:if>
			     				 >
			    			</div>
		  			</div>
		  			
					<div class="form-group row">
		   				 <label for="description" class="col-sm-2 col-form-label">Description</label>
			   				 <div class="col-sm-10">
			     				 <textarea class="form-control" id="description"  rows="5" cols="40" name="description"  required="required" 
				     				 <c:if test="${empty newArticle }">
				     				 placeholder="Entrer une description ici" required="required" 
				     				 </c:if>
				     				 ><c:if test="${!empty newArticle }">${newArticle.description }</c:if></textarea>
			    			</div>
		  			</div>
		  			
		  			<div class="form-group row">
		   				 <label for="categorie" class="col-sm-2 col-form-label">Categorie</label>
			   				 <div class="col-sm-10">
								<select class="custom-select" id="categorie" name="categorie">
								    <c:forEach var="cat" items="${listeDeCategories}">
								    	<option value="${cat.libelle}">${cat.libelle}</option>
								    </c:forEach>
								      
								 </select>
			    			</div>
		  			</div>
		  			
		  			
<!-- 		  			Affichage de l'image si il y en a une -->
		  			<c:if test="${!empty image}">
		  				<div>
		  					<img alt="image de l'article" src="${image}">
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
			     				 <input type="number" class="form-control" id="map" name="map" min="0"  required="required" 
			     				  <c:if test="${!empty newArticle }">
			     				 	value="${newArticle.miseAPrix }"
			     				 </c:if>
			     				 >
			    			</div>
		  			</div>
		  			
					<div class="form-group row">
		   				 <label for="dateDebutEncheres" class="col-sm-2 col-form-label">Début de l'enchère</label>
			   				 <div class="col-sm-10">
			     				 <input type="date" class="form-control" id="dateDebutEncheres" name="dateDebutEnchere"  required="required">
			    			</div>
		  			</div>
		  			
		  			<div class="form-group row">
		   				 <label for="dateFinEncheres" class="col-sm-2 col-form-label">Fin de l'enchère</label>
			   				 <div class="col-sm-10">
			     				 <input type="date" class="form-control" id="dateFinEncheres" name="dateFinEnchere" required="required">
			    			</div>
		  			</div>
		  			
					<fieldset form="formNouvelleVente">
					
						<legend>Retrait</legend>
							<div class="form-group row">
			   				 <label for="nomRue" class="col-sm-2 col-form-label">Rue: </label>
				   				 <div class="col-sm-10">
				     				 <input type="text" class="form-control" id="nomRue" name="nomRue" required="required" 
				     				 <c:if test="${!empty user }">
				     				 value="${user.rue }"
				     				 </c:if>
				     				 
				     				 <c:if test="${!empty retrait }">
				     				 value="${retrait.rue }"
				     				 </c:if>
				     				 >
				    			</div>
			  				</div>
			  			
							<div class="form-group row">
			   				 <label for="codePostal" class="col-sm-2 col-form-label">Code postal: </label>
				   				 <div class="col-sm-10">
				     				 <input type="text" class="form-control" id="codePostal" name="codePostal" required="required" 
				     				 <c:if test="${!empty user }">
				     				 value="${user.codePostale }"
				     				 </c:if>
				     				 
				     				 <c:if test="${!empty retrait }">
				     				 value="${retrait.codePostal }"
				     				 </c:if>
				     				 >
				    			</div>
			  				</div>
			  			
							<div class="form-group row">
			   				 <label for="ville" class="col-sm-2 col-form-label">Ville: </label>
				   				 <div class="col-sm-10">
				     				 <input type="text" class="form-control" id="ville" name="ville" required="required" 
				     				 <c:if test="${!empty user }">
				     				 value="${user.ville }"
				     				 </c:if>
				     				 
				     				 <c:if test="${!empty retrait }">
				     				 value="${retrait.ville }"
				     				 </c:if>
				     				 >
				    			</div>
			  				</div>
						
					</fieldset>
				<input type="submit" value="Enregistrer" class="btn btn-primary mb-2">
				<a href="<%=request.getContextPath()%>/Accueil">
		   			<input type="button" value="Annuler" class="btn btn-primary mb-2"/>
				</a>
			</form>
	</div>
	

</body>
</html>
