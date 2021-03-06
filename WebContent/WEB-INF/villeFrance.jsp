<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Villes de France - Gestion</title>

<!-- Bootstrap core CSS -->
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" />
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>

<!-- Custom styles for this template -->
<link href="css/clean-blog.min.css" rel="stylesheet">
<link href="vendor/bootstrap/css/dataTables.bootstrap4.min.css"
	rel="stylesheet">
</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="index.html">Villes de France</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="index.html">Accueil</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="UtilisationVille">Gestion des Villes</a></li>
					<li class="nav-item"><a class="nav-link" href="CalculDistance">Calculer
							une distance</a></li>
					<li class="nav-item"><a class="nav-link" href="Meteo">M�t�o
							dans votre ville</a></li>
					<li class="nav-item"><a class="nav-link" href="about.html">A
							Propos</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Header -->
	<header class="masthead"
		style="background-image: url('img/post-bg.jpg')">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1>Gestion de villes</h1>
						<span class="subheading">Un site de gestion de villes</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Main Content -->
	<div class="container">
		<form method="post" action="AjouterVille">
			<div class="col-xs-8 text-center">
				<button type="submit" class="btn btn-primary">Ajouter une
					ville</button>
			</div>
		</form>
		<div class="row">
			<div>
				<h2 class="post-title">Listes des villes :</h2>
				<p></p>
				<div>
					<table class="table table-striped table-bordered table-hover"
						id="mydata">
						<thead>
							<tr>
								<th scope="col">Code Commune Insee</th>
								<th scope="col">Nom Commune</th>
								<th scope="col">Code Postal</th>
								<th scope="col">Latitude</th>
								<th scope="col">Longitude</th>
								<th scope="col">Modifier</th>
								<th scope="col">Supprimer</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" begin="0" end="${villeFrance.size()-1}"
								step="1">
								<tr>
									<td>${villeFrance.get(i).getCodeCommuneInsee()}</td>
									<td>${villeFrance.get(i).getNomCommune()}</td>
									<td>${villeFrance.get(i).getCodePostal()}</td>
									<td>${villeFrance.get(i).getLattitude()}</td>
									<td>${villeFrance.get(i).getLongitude()}</td>
									<td>
										<form method="post" action="EditerVille">
											<input id="idVille" name="idVille" type="hidden"
												value="${villeFrance.get(i)}">
											<button type="submit" class="btn btn-primary">Modifier</button>
										</form>
									</td>
									<td>
										<form method="post" action="SupprimerVille">
											<input id="idVille" name="idVille" type="hidden"
												value="${villeFrance.get(i)}">
											<button type="submit" class="btn btn-primary">Supprimer</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- Pager -->
			</div>
		</div>
	</div>

	<hr>

	<!-- Footer -->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<ul class="list-inline text-center">
						<li class="list-inline-item"><a href="#"> <span
								class="fa-stack fa-lg"> <i
									class="fas fa-circle fa-stack-2x"></i> <i
									class="fab fa-twitter fa-stack-1x fa-inverse"></i>
							</span>
						</a></li>
						<li class="list-inline-item"><a href="#"> <span
								class="fa-stack fa-lg"> <i
									class="fas fa-circle fa-stack-2x"></i> <i
									class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>
							</span>
						</a></li>
						<li class="list-inline-item"><a href="https://github.com/Volpinso/"> <span
								class="fa-stack fa-lg"> <i
									class="fas fa-circle fa-stack-2x"></i> <i
									class="fab fa-github fa-stack-1x fa-inverse"></i>
							</span>
						</a></li>
					</ul>
					<p class="copyright text-muted">Copyright &copy; Ville France
						2019</p>
				</div>
			</div>
		</div>
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<!--   <script src="vendor/jquery/jquery.min.js"></script> -->
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Custom scripts for this template -->
	<script src="js/clean-blog.min.js"></script>
	<script src="vendor/bootstrap/js/jquery.dataTables.min.js"></script>
	<script src="vendor/bootstrap/js/dataTables.bootstrap4.min.js"></script>
	<script>
    $('#mydata').dataTable();
    </script>

</body>

</html>
