
		<!-- font awesome and foundation-->
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="./foundation.css">

  <!-- jquery --> 
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>


	<!-- angular -->
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-animate.js"></script>
<script	src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.1.2.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9//angular-route.js"></script>



   <!-- user imports -->
<script src="directives.js"></script>
<script src="constants.js"></script>
<link rel="stylesheet" type="text/css" href="./SAstyles.css">
<script src="fbscript.js"></script>




   <!-- material UI --> 
<link href="//cdn.muicss.com/mui-0.4.7/css/mui.min.css" rel="stylesheet" type="text/css" />
<script src="//cdn.muicss.com/mui-0.4.7/js/mui.min.js"></script>

   <!-- bootstrap -->
<link 	href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"	rel="stylesheet">



		<!-- circular loader -->
<link rel="stylesheet" type="text/css" title="A" href="../styles/circularLoader.css">


<!-- navigation drawer -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.1.0/css/drawer.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/iScroll/5.1.3/iscroll.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.1.0/js/drawer.min.js"></script>



				<!-- material ui -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>






<style>
.center {
	 box-shadow: 5px 5px 8px #aaa;
	padding: 30px 2% 30px;
	left: 35%;
	width: 30%;
	position: fixed;
	max-width: 380px;
	border: 1px solid #dadada;
	background-color: white;
	top: 20%;
	min-height: 60%; 
}

.logo {
	max-width: 30%;
	height: auto;
	width: auto/9;
}

.fbLogin {
	margin-top: 10%;
}

.loginButton {
	margin-top: 10%;
	width: 60%;
	height: 80%;
	display:none;
}

.loginPanel {
	background: url('../images/wall.jpg');
}

.hidePageLoad
{    display: none;
}
</style>

<body style="background-color: #f7f7f7;">



	<div class="center">

		<div align="middle" class="title">
			<h3>Student Assist</h3>
		</div>
		<div align="middle">


			<div id="loginLanding">

				<img src="../images/ic_logo.png" class="logo"> <br />

				<div align="middle" id="progressBar">

					<img src="../images/anim_loader.gif"> <br />

				</div>

				<div class="loginButton">


					<a href="#"><img src="../images/fbLogin.png" id="login"></a>


				</div>

			</div>
			<div id="homeScreen" class="hidePageLoad">

				<a href="#"><img
					src="../images/icon_home.png" class="logo homeScreenIntent"></a> <br /> <img
					src="../images/icon_plane.png" class="logo fbLogin ">

			</div>
		</div>
	</div>



</body>

<div id="homeScreenActivity" class="hidePageLoad">
			<%@include file="HomeScreenActivity.jsp"%>

</div>
