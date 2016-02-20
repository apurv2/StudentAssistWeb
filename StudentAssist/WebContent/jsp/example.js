angular.module('ui.bootstrap.demo', [ 'ngAnimate', 'ui.bootstrap' ]);

var module = angular.module('ui.bootstrap.demo');

module.service('StudentAssist', function($http, $log) {

	var advertisements = [];

	var apartmentNames = [];

	var apartmentNamesTitle =[ {
		apartmentName : 'apartmentNames'
	}]
	
	
	//returns the apartment names Title
	this.getApartmentsTitle = function() {

		return apartmentNamesTitle;
	}
	
	
	//returns all apartmentNames list
	this.getAllapartmentsList = function() {

		return apartmentNames;
	}

	// returns all advertisements
	this.getSimpleSearchAddsList = function() {

		return advertisements;

	}

	this.getApartmentNames = function(apartmentType) {

		var aptNamesurl = url + "getApartmentNames?apartmentType="
				+ map[apartmentType];

		$http.get(aptNamesurl).then(function(response) {

			// push apartment names to populate drop-down 
			apartmentNames.length = 0;
			apartmentNames.push.apply(apartmentNames, response.data);
			
			//push apartment names to change the title of the drop-down
			apartmentNamesTitle.length=0;
			apartmentNamesTitle.push.apply(apartmentNamesTitle,response.data);

		});

	}

	this.submitAdd = function(url) {
		$http.get(submitUrl).then(function(response) {

		});

	}

	this.getSimpleSearchAdds = function(leftSpinner, rightSpinner) {
		var urlAcc = url + "getSimpleSearchAdds?leftSpinner=" + leftSpinner
				+ "&rightSpinner=" + rightSpinner;

		$http.get(urlAcc).then(function(response) {

			advertisements.length = 0;
			advertisements.push.apply(advertisements, response.data);

		});

	}

});

module.controller('DropdownCtrl', function($scope, $log, $http, StudentAssist) {

	// get the apartment names list
	$scope.apartmentNames = StudentAssist.getAllapartmentsList();

	$scope.default_no_rooms = "1 bhk/1 bath";
	$scope.default_vacancies = "1";
	$scope.default_gender = "Male";
	$scope.apartmentNamesTitle = StudentAssist.getApartmentsTitle();

	$scope.noOfRooms = noOfRooms;
	$scope.vacancies = vacancies;
	$scope.gender = gender;

	// initilize apartmentTypes
	$scope.apartmenTypes = apartmenTypes;

	// initialize simple Search advertisements
	$scope.advertisements = StudentAssist.getSimpleSearchAdds();

	// populate default apartment names when application starts.
	StudentAssist.getApartmentNames(ON_CAMPUS);
	
	// gets simple serarch adds
	$scope.getAccommodationAdds = function(apartmentName) {
		
		var temp = [ {
			apartmentName : apartmentName
		} ];

		$scope.apartmentNamesTitle.length = 0;

		$scope.apartmentNamesTitle.push.apply($scope.apartmentNamesTitle,
				temp);
		
		
		//StudentAssist.getSimpleSearchAdds();

	}

	$scope.submitClicked = function() {

		var apartmentName = $scope.apartmentNames[0].apartmentName;

		var parameters = POST_ACCOMMODATION + "?" + APARTMENT_NAME + "="
				+ encodeURIComponent(apartmentName) + "&" + NO_OF_ROOMS + "="
				+ encodeURIComponent($scope.default_no_rooms) + "&" + VACANCIES
				+ "=" + encodeURIComponent($scope.default_vacancies) + "&"
				+ COST + "=" + encodeURIComponent($scope.cost) + "&" + GENDER
				+ "=" + encodeURIComponent($scope.default_gender) + "&" + FBID
				+ "=" + encodeURIComponent($scope.fbId) + "&" + NOTES + "="
				+ encodeURIComponent($scope.notes);

		var submitUrl = url + parameters;
		StudentAssist.submitAdd(submitUrl);

		$log.log(submitUrl);

	}

	
	
	$scope.changeAptName = function(apartmentName) {

		var temp = [ {
			apartmentName : apartmentName
		} ];

		$scope.apartmentNamesTitle.length = 0;

		$scope.apartmentNamesTitle.push.apply($scope.apartmentNamesTitle,
				temp);
		
		

	}

	$scope.changeVacancies = function(vacancies) {

		$scope.default_vacancies = vacancies;
	}

	$scope.changeGender = function(gender) {

		$scope.default_gender = gender;
	}

	$scope.changeNoOfRooms = function(noOfRooms) {

		$scope.default_no_rooms = noOfRooms;
	}

	$scope.changeApartmentType = function(apartmentType) {

		$scope.apartmentType = apartmentType;

		StudentAssist.getApartmentNames(apartmentType);

	}

	$scope.init = function() {
		$scope.apartmentType = apartmenTypes[0];
	}

	$scope.status = {
		isopen : false
	};

	$scope.toggleDropdown = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.status.isopen = !$scope.status.isopen;
	};

	$scope.appendToEl = angular.element(document
			.querySelector('#dropdown-long-content'));
});
