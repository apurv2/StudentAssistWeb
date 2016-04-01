<html>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-animate.js"></script>
<script
	src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.1.2.js"></script>
<script src="example.js"></script>
<script src="constants.js"></script>

<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css" href="./SAstyles.css">

<link rel="stylesheet" type="text/css" href="./foundation.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.1.0/css/drawer.min.css">


<!-- jquery & iScroll -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/iScroll/5.1.3/iscroll.min.js"></script>
<!-- drawer.js -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.1.0/js/drawer.min.js"></script>


<style>
.box {
	box-shadow: 5px 5px 8px #aaa;
	border: 1px solid #dadada;
	border-radius: 10px;
	padding: 30px 2% 30px;
	background-color: white;
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
}

.loginPanel {
	background: url('../images/wall.jpg');
}
</style>


<body style="background-color: #f7f7f7; top: 2%;">


	<!-- ApartmentType -->

	<div ng-app="ui.bootstrap.demo"
		ng-controller="postAccommodationController" ng-init="init()">

		<form name="newVacancy">
			<div class="row">
				<div class="small-6 columns">

					<div class="box">

						<div class="title-underlined ">
							<h4>Vacancy Information</h4>
						</div>

						<div class="row">
							<div class="small-6 columns">
								<label>Apartment Type:</label>
							</div>
							<div class="small-6 columns">
								<span uib-dropdown on-toggle="toggled(open)"> <a href
									id="simple-dropdown" uib-dropdown-toggle>
										{{apartmentTypeHeader}} <i class="fa fa-sort-desc"></i>
								</a>
									<ul uib-dropdown-menu aria-labelledby="simple-dropdown">
										<li ng-repeat="choice in apartmentTypes"><a
											ng-click="apartmentType(choice)" href>{{choice}}</a></li>
									</ul>
								</span>
							</div>
						</div>


						<!-- ApartmentName -->

						<div class="row">
							<div class="small-6 columns">

								<label>ApartmentName:</label>

							</div>
							<div class="small-6 columns">
								<span uib-dropdown on-toggle="toggled(open)"> <a href
									id="simple-dropdown" uib-dropdown-toggle>
										{{apartmentNameHeader}} <i class="fa fa-sort-desc"></i>
								</a>
									<ul uib-dropdown-menu aria-labelledby="simple-dropdown">
										<li ng-repeat="choice in apartmentNames"><a
											ng-click="apartmentName(choice.apartmentName)" href>{{choice.apartmentName}}</a>
										</li>
									</ul>
								</span>
							</div>
						</div>

						<!-- NoOfRooms -->
						<div class="row">
							<div class="small-6 columns">


								<label>No. of Rooms:</label>
							</div>
							<div class="small-6 columns">

								<span uib-dropdown on-toggle="toggled(open)"> <a href
									id="simple-dropdown" uib-dropdown-toggle>
										{{noOfRoomsHeader}} <i class="fa fa-sort-desc"></i>
								</a>
									<ul uib-dropdown-menu aria-labelledby="simple-dropdown">
										<li ng-repeat="choice in noOfRooms"><a
											ng-click="changeNoOfRoomsHeader(choice)" href>{{choice}}</a></li>
									</ul>
								</span>
							</div>
						</div>


						<!-- Vacancies -->


						<div class="row">
							<div class="small-6 columns">

								<label>vacancies:</label>

							</div>
							<div class="small-6 columns">

								<span uib-dropdown on-toggle="toggled(open)"> <a href
									id="simple-dropdown" uib-dropdown-toggle>
										{{vacanciesHeader}} <i class="fa fa-sort-desc"></i>
								</a>
									<ul uib-dropdown-menu aria-labelledby="simple-dropdown">
										<li ng-repeat="choice in vacancies"><a
											ng-click="changeVacanciesHeader(choice)" href>{{choice}}</a></li>
									</ul>
								</span>

							</div>
						</div>

						<!-- Gender -->

						<div class="row">
							<div class="small-6 columns">


								<label>gender:</label>
							</div>
							<div class="small-6 columns">


								<span uib-dropdown on-toggle="toggled(open)"> <a href
									id="simple-dropdown" uib-dropdown-toggle> {{genderHeader}}
										<i class="fa fa-sort-desc"></i>
								</a>
									<ul uib-dropdown-menu aria-labelledby="simple-dropdown">
										<li ng-repeat="choice in gender"><a
											ng-click="changeGenderHeader(choice)" href>{{choice}}</a></li>
									</ul>
								</span>
							</div>
						</div>

						<!-- Text input-->

						<div class="row">
							<div class="small-6 columns">



								<label>Cost/Month</label>
							</div>
							<div class="small-6 columns">



								<input id="textinput" name="cost" ng-model="cost" type="number"
									placeholder="cost/month" class="form-control input-md" required>
								<span style="color: red"
									ng-show="newVacancy.cost.$dirty && newVacancy.cost.$invalid">
									<span>Invalid data</span> <span
									ng-show="newVacancy.cost.$error.required">cost per month
										is required</span>
								</span>
							</div>
						</div>

						<!-- Text input-->
						<div class="row">
							<div class="small-6 columns">



								<label>Facebook Id </label>

							</div>
							<div class="small-6 columns">

								<input id="textinput" name="fbId" ng-model="fbId" type="number"
									placeholder="Facebook Id" 
									required> <span style="color: red"
									ng-show="newVacancy.fbId.$dirty && newVacancy.fbId.$invalid">
									<span>Invalid data</span> <span
									ng-show="newVacancy.fbId.$error.required">facebook Id is
										required</span>
								</span>
							</div>
						</div>
						<!-- Text input-->

						<div class="row">
							<div class="small-6 columns">





								<label>Notes/Comments</label>

							</div>
							<div class="small-6 columns">

								<textarea ng-pattern="/^[a-zA-Z0-9 .\n-]*$/" id="textinput"
									name="notes" type="text" placeholder="notes/comments"
									class="form-control input-md" ng-model="notes" required>
              					</textarea>

								<span style="color: red"
									ng-show="newVacancy.notes.$dirty && newVacancy.notes.$invalid">
									<span ng-show="newVacancy.notes.$error.required">notes/comments
										are required</span> <span ng-show="newVacancy.notes.$error.pattern">only
										numbers/letters allowed</span>
								</span>

							</div>
						</div>
						<!-- Button -->

						<label></label>

						<button class="btn btn-primary" ng-disabled="newVacancy.$invalid"
							ng-click="submitClicked()">Submit</button>
					</div>

				</div>

				<div class="small-6 columns">


					<div class="box">

						<div class="title-underlined ">
							<h4>Contact Information</h4>
						</div>
						<div class="row">
							<div class="small-6 columns">
								<label>phone Number:</label>
							</div>

							<div class="small-6 columns">
								<input type="text">
							</div>
						</div>
						<div class="row">

							<div class="small-6 columns">
								<label>email Address:</label>
							</div>

							<div class="small-6 columns">
								<input type="text">
							</div>

						</div>
					</div>



				</div>


			</div>




		</form>
	</div>



</body>

</html>
