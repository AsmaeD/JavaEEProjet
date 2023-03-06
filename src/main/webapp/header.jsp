<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="fr">
<meta charset="UTF-8">

<header>
	<!-- NavBar classique présente sur toutes les pages -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
		<div class="container">
			<a class="navbar-brand" href="Accueil">ENI Enchères</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<c:if test="${empty Utilisateur}">
				<div class="collapse navbar-collapse" id="navbarResponsive">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="Inscription">S'inscrire
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="Connexion">Se connecter</a></li>
					</ul>
				</div>
			</c:if>

			<c:if test="${empty Utilisateur}">
				<div class="collapse navbar-collapse" id="navbarResponsive">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="deconnexion">Se
								déconnecter </a>
					</ul>
				</div>
			</c:if>
		</div>
	</nav>
</header>