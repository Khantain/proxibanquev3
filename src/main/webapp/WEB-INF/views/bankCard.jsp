<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Ajout d'une carte</title>


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
<body>
	<jsp:include page="/WEB-INF/views/deco_name.jsp"></jsp:include>
	<div class="top_left" style="float:left" >
			<a href="/proxibanquev3/board.html"> 
			<button class="button"> Accueil</button> </a>
</div>
	<section class="head"></section>
	<h1 class="page-title">Ajout de la carte bancaire pour ${client.firstname} ${client.lastname}</h1>
	<div class="form-cont">
		<form method="post" action="createCard.html?id=${id}">
			<div class="edit-form">
				<div class="label-container">
					<label for="number">Numero</label> 
					<label for="type">Type</label> 
				</div>
				<div class="input-container">
					<input type="text" id="number"  name="number" maxlength="45">
					<input type="text" id="type" name="type" maxlength="45">
				</div> 
				
			</div>
		<div class="buttons">
			<button class="button">Valider</button>
			<button type="reset" class="button">Réinitialiser</button>
		</div>
		</form>
			<a href="index.html">
				<button class="button">Retour à l'accueil</button>
			</a>
		</div>
	
</body>
</html>