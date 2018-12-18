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

<script src="js/jquery.js">
	
</script>
<script src="js/agency.min.js"></script>
<script src="js/checkbook_verif.js">
	
</script>

</head>

<body id="page-top">
	<jsp:include page="deco_name.jsp" />
	<div class="top_left" style="float: left">
		<a href="board.html">
			<button class="button">Menu</button>
		</a>
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
	<div class="transfer-button">
		
	</div>
	<div id="results"></div>
	<div class="account-list">
	<div class="left-list">
			<h2>Liste des comptes courants</h2>
			<c:if test="${empty currentAccounts}">
				<h4>Aucun compte associé à ce client.</h4>
			</c:if>
			<c:if test="${not empty currentAccounts}">
				<table>
					<tr>
						<th>Numéro de compte</th>
						<th class="balance">Solde en &#8364</th>
						<th class="balance">Date d'ouverture</th>
						<th class="balance"></th>
						<th class="balance"></th>
						<th class="balance"></th>
					</tr>
					<c:forEach var="currentAccount" items="${currentAccounts }">
						<tr class="data">
							<!-- 	<td>
							<input type="radio" id="${currentAccount.id}" name="compteADebiter" value="${currentAccount.id}">
							<label for="${currentAccount.id}">n°${currentAccount.number}</label>
						</td>  -->
							<td>${currentAccount.number}</td>
							<td class="balance">${currentAccount.balance}</td>
							<td class="balance">${currentAccount.openDate}</td>
							<td class="balance"><a
								href="retrait.html?id=${currentAccount.id}">
									<button class="button">Retrait</button>
							</a></td>
							<td class="balance">
								<button class="button validate"><span id="span" style="display: none">${currentAccount.id}</span>Demande chéquier</button>
							</td>
							<td class="balance"><a
								href="card.html?id=${currentAccount.id}">
									<button class="button">Demande CB</button>
							</a></td>
						</tr>

					</c:forEach>
				</table>
			</c:if>
		</div>
	
	 <div class="right-list">
			<h2>Liste des comptes épargnes</h2>
			<c:if test="${empty savingAccounts}">
				<h4>Aucun compte associé à ce client.</h4>
			</c:if>
			<c:if test="${not empty savingAccounts}">
				<table>
					<tr>
						<th>Numero de compte</th>
						<th class="balance">Solde en &#8364</th>
						<th class="balance">Date d'ouverture</th>
						<th class="balance"></th>
						<th class="balance"></th>
						
					</tr>
					<c:forEach var="savingAccount" items="${savingAccounts }">
						<tr class="data">
							<td>${savingAccount.number}</td>
							<td class="balance">${savingAccount.balance}</td>
							<td class="balance">${savingAccount.openDate}</td>
							<td class="balance">
								<button class="button validate"><span style="display: none">${savingAccount.id}</span>Demande chéquier
								</button>
							</td>

						</tr>
						<span id="span" style="display: none">${savingAccount.id}</span>
					</c:forEach>
				</table>
			</c:if>
		</div>
		</div>
	<div class="footer-button">
		<a href="transfer.html?id=${sessionScope.client.id}">
			<button class="button" style="margin-top: 50px;">Faire un
				virement</button>
		</a>
	</div>
</body>
</html>