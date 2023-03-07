<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/CSS/style.css"
	rel="stylesheet">
<title>Detail Vente</title>
</head>
<body>
	<header>
		<%@ include file="header.jsp"%>
		<br>
		<div style="text-align: center;">
			<h2 class="center">Detail de la vente</h2>
		</div>
	</header>

	<c:if test="${!empty articleAAfficher}">
		<div class="container">

			<c:if test="${!empty image}">
				<img alt="" src="${image}">
			</c:if>
			<form action="DetailVente" method="POST">
				<div class="form-group row">
					<label for="map" class="col-sm-4 col-form-label">Nom de
						l'article</label>
					<div class="col-sm-6">
						<input type="text" name="nomArticle"
							value="${articleAAfficher.nomArticle}" readonly="readonly" />
					</div>
				</div>

				<div class="form-group row">
					<label for="map" class="col-sm-4 col-form-label">Description</label>
					<div class="col-sm-6">
						<p>${articleAAfficher.description}</p>
					</div>
				</div>


				<div class="form-group row">
					<label for="map" class="col-sm-4 col-form-label">Meilleure
						offre</label>
					<div class="col-sm-6">
						<c:if test="${empty enchere}">
							<p>${articleAAfficher.miseAPrix}pts</p>
						</c:if>
						<c:if test="${!empty enchere}">
							<p>${enchere.getMontantEnchere()+10}pts</p>
						</c:if>
					</div>
				</div>

				<div class="form-group row">
					<label for="map" class="col-sm-4 col-form-label">Mise à
						prix</label>
					<div class="col-sm-6">
						<p>${articleAAfficher.miseAPrix}pts</p>
					</div>
				</div>

				<div class="form-group row">
					<label for="map" class="col-sm-4 col-form-label">Retrait</label>
					<div class="col-sm-6">
						<p>Rue : ${retraitArticleSelected.rue }</p>
						<p>Code Postal :${retraitArticleSelected.codePostal }</p>
						<p>Ville :${retraitArticleSelected.ville }</p>
					</div>
				</div>

				<div class="form-group row">
					<label for="map" class="col-sm-4 col-form-label">Vendeur</label>
					<div class="col-sm-6">
						<input type="text" name="pseudoVendeur" value="${vendeur}" />
					</div>
				</div>
				<c:if test="${vendeur != Utilisateur}">
					<div class="form-group row">
						<label for="map" class="col-sm-4 col-form-label">Ma
							Proposotion</label>
						<div class="col-sm-6">
							<input type="number"
								<c:if test="${empty enchere }"> min="${articleAAfficher.miseAPrix+10}" value="${articleAAfficher.miseAPrix+10}"</c:if>
								<c:if test="${!empty enchere }"> min="${enchere.getMontantEnchere()+10}" value="${enchere.getMontantEnchere()+10}"</c:if>
								name="enchere" /> <input type="submit" value="Enchérir"
								class="btn btn-primary mb-2" />

						</div>
			</form>
		</div>

	</c:if>

	<c:if test="${vendeur == Utilisateur }">

		<a
			href="ModifEnchere?nomArticle=${articleAAfficher.nomArticle}&pseudoVendeur=${vendeur}"
			class="btn btn-primary mb-6"></a>

		<a
			href="Delete?nomArticle=${articleAAfficher.nomArticle}&pseudoVendeur=${vendeur}"
			class="btn btn-primary mb-6"></a>

	</c:if>


</body>
</html>