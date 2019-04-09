<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Villes de France - Météo</title>

  <!-- Bootstrap core CSS -->
  <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" />
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

  <!-- Custom styles for this template -->
  <link href="css/clean-blog.min.css" rel="stylesheet">
  <link href="vendor/bootstrap/css/dataTables.bootstrap4.min.css" rel="stylesheet">
   <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css" integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ=="
            crossorigin="" />
   <style type="text/css">
			#map{ /* la carte DOIT avoir une hauteur sinon elle n'apparaît pas */
				height:400px;
			}
		</style>
</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand" href="index.html">Villes de France</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="index.html">Accueil</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="UtilisationVille">Gestion des Villes</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="CalculDistance">Calculer une distance</a>
          </li>
           <li class="nav-item">
            <a class="nav-link" href="Meteo">Météo dans votre ville</a>
           </li>
          <li class="nav-item">
            <a class="nav-link" href="about.html">A Propos</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Header -->
  <header class="masthead" style="background-image: url('img/post-sample-image.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="site-heading">
            <h1>Météo dans votre ville</h1>
            <span class="subheading">Un site de gestion de villes</span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div>
      	 <h2 class="post-title">
      		Météo de votre ville : 
      	 </h2>
      	 <p>
      	 </p>
      	 <div>
      	<table class="table table-striped table-bordered table-hover">
			  <thead>
			    <tr>
				      <th scope="col">Code Commune Insee</th>
				      <th scope="col">Nom Commune</th>
				      <th scope="col">Code Postal</th>
				      <th scope="col">Latitude</th>
				      <th scope="col">Longitude</th>
				      <th scope="col"> Population</th>
				      <th scope="col">Température</th>
				      <th scope="col">Temps</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<tr>
			  		<td>${villeMeteo.getCodeCommuneInsee()}</td>
				    <td>${villeMeteo.getNomCommune()}</td>
				    <td>${villeMeteo.getCodePostal()}</td>
				    <td>${villeMeteo.getLattitude()}</td>
				    <td>${villeMeteo.getLongitude()}</td>
				    <td>${villeMeteo.getPop()}</td>
					<td>${villeMeteo.getTemperature()} °C</td>
					<td><img src="${villeMeteo.getIdTemps()}"/> </td>
			  	</tr>
			  </tbody>
		</table>
		</div>
		<div id="map">
		</div>
		<br>
		<div class="col-xs-8 text-center">	
     		<a href="Meteo" class="btn btn-primary">Retour à la météo</a>
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
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-twitter fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
          </ul>
          <p class="copyright text-muted">Copyright &copy; Ville France 2019</p>
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
      <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js" integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw=="
            crossorigin=""></script>
  <script type="text/javascript">
			// On initialise la latitude et la longitude de Paris (centre de la carte)
			var lat = ${villeMeteo.getLattitude()};
			var lon = ${villeMeteo.getLongitude()};
			var macarte = null;
			// Fonction d'initialisation de la carte
			function initMap() {
				// Créer l'objet "macarte" et l'insèrer dans l'élément HTML qui a l'ID "map"
                macarte = L.map('map').setView([lat, lon], 14);
                // Leaflet ne récupère pas les cartes (tiles) sur un serveur par défaut. Nous devons lui préciser où nous souhaitons les récupérer. Ici, openstreetmap.fr
                L.tileLayer('https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png', {
                    // Il est toujours bien de laisser le lien vers la source des données
                    attribution: 'données © <a href="//osm.org/copyright">OpenStreetMap</a>/ODbL - rendu <a href="//openstreetmap.fr">OSM France</a>',
                    minZoom: 1,
                    maxZoom: 20
                }).addTo(macarte);
                var marker = L.marker([lat, lon]).addTo(macarte);
            }
			window.onload = function(){
				// Fonction d'initialisation qui s'exécute lorsque le DOM est chargé
				initMap(); 
			};
		</script>

</body>

</html>
