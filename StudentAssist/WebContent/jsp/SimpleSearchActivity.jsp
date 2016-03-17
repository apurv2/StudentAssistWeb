<html>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-animate.js"></script>
<script
	src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.1.2.js"></script>
<script src="example.js"></script>
<script src="constants.js"></script>

<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="./SAstyles.css">

<link rel="stylesheet" type="text/css" href="./foundation.css">



<style>

.dropdown-menu
{
    left: initial;
}

</style>


<div ng-app="ui.bootstrap.demo" ng-controller="simpleSearchController">

	<div class="row" style="max-width: 100%;">

		<div class="large-3 columns"
			style="background: white; max-height: 100%; padding-right: 0; padding-left: 0;">

			<div id="sidebar">

				<%@include file="SimpleSearchFragment.html"%>


			</div>
		</div>

		<div class="large-9 columns adDetailsInfo" style="height: 100%;">

			<div id="sidebar">

				<%@include file="adDetails.html"%>

			</div>

		</div>


	</div>










</div>