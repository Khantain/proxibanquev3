<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>ProxiBanque SI</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>

<!-- Custom styles for this template -->
<link href="css/agency.min.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">

</head>

<body id="page-top">
	<jsp:include page="deco_name.jsp" />
	<div class="top_left" style="float:left" >
			<a href="/proxibanquev3/board.html"> 
			<button class="button"> Accueil</button> </a>
</div>

	<!-- Header -->
	<header class="masthead">
		<div class="container">
			<div class="intro-text"
				style="padding-top: 150px; padding-bottom: 200px;">
				<div class="intro-lead-in">Bienvenue sur le système
					d'information</div>
				<div class="intro-heading text-uppercase">PROXIBANQUE</div>
			</div>
		</div>
	</header>

	<c:if test="${empty accounts}">
		<h4>Aucun compte associé à ce client.</h4>
	</c:if>
	<c:if test="${not empty accounts}">
		<div class="account-list">
			<form method="POST" action="">
				<div class="left-list">
					<h2>Sélectionner le compte à débiter</h2>
					<table>
						<tr>
							<th>Numero de compte</th>
							<th class="balance">Solde en &#8364</th>
							<th class="balance">Date d'ouverture</th>
						</tr>
						<c:forEach var="accounts" items="${accounts}">
							<tr class="data">
								<td><input type="radio" id="${accounts.id}"
									name="compteADebiter" value="${accounts.id}"> <label
									for="${accounts.id}">n°${accounts.number}</label>
								<td>${accounts.number}</td>
								<td class="balance">${accounts.balance}</td>
							</tr>
						</c:forEach>
					</table>
				</div>

				<div class="right-list">
					<h2>Sélectionner le compte à débiter</h2>
					<table>
						<tr>
							<th>Numero de compte</th>
							<th class="balance">Solde en &#8364</th>
							<th class="balance">Date d'ouverture</th>
						</tr>
						<c:forEach var="accounts" items="${accounts}">
							<tr class="data">
								<td><input type="radio" id="${accounts.id}"
									name="compteACrediter" value="${accounts.id}"> <label
									for="${accounts.id}">n°${accounts.number}</label>
								<td>${accounts.number}</td>
								<td class="balance">${accounts.balance}</td>
							</tr>

						</c:forEach>
					</table>
				</div>

				<div class="label-container">
					<label for="value">Montant à transférer (900 &#8364
						maximum)</label>
				</div>
				<div class="input-container">
					<input type="number" id="value" name="value" max="900" min="0" step=0.01>
				</div>
				<div class="buttons">
					<button class="button">Valider</button>
				</div>
			</form>
		</div>
	</c:if>

</body>
</html>