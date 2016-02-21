angular.module('ui.bootstrap.demo', [ 'ngAnimate', 'ui.bootstrap' ]);

var module = angular.module('ui.bootstrap.demo');

module.service('StudentAssist', function($http, $log) {

	/*this.getApartmentNames = function(leftSpinner) {

		var aptNamesurl = url + "getApartmentNames?apartmentType="
				+ map[leftSpinner];
		
		$log.log(aptNamesurl);

		return $http.get(aptNamesurl);
	};
*/
	
	this.getAllApartmentNames = function()
	{
		var aptNamesurl=url+"getAllApartmentNames";
		
		
		return $http.get(aptNamesurl);
	}
	
	
	this.getSimpleSearchAdds = function(leftSpinner, rightSpinner) {

		
		
		var urlAcc = url + "getSimpleSearchAdds?leftSpinner=" + leftSpinner
				+ "&rightSpinner=" + rightSpinner;

		return $http.get(urlAcc);

	};
	
	

	this.submitAdd = function(url) {
		$http.get(submitUrl).then(function(response) {

		});

	}

	

});

module.controller('DropdownCtrl', function($scope, $log, $http, StudentAssist) {



	$scope.leftSpinnerHeader = APARTMENT_TYPE;
	$scope.rightSpinnerHeader = "";

	$scope.rightSpinnerValues = [];
	$scope.advertisements=[];

	$scope.default_no_rooms = "1 bhk/1 bath";
	$scope.default_vacancies = "1";
	$scope.default_gender = "Male";
	$scope.noOfRooms = noOfRooms;
	$scope.vacancies = vacancies;
	$scope.gender = gender;

	// initilize leftSpinner
	$scope.leftSpinnerValues = leftSpinnerValues;

	$scope.getSimpleSearchAds = function(leftSpinner,rightSpinner) {
		
		
		var queryparam="";
		
		
		queryparam=map[rightSpinner]==null?rightSpinner:map[rightSpinner];
		
		leftSpinner= leftSpinnerCodeMap[leftSpinner];
		rightSpinner = encodeURIComponent(queryparam);
		

		
		StudentAssist.getSimpleSearchAdds(leftSpinner,rightSpinner).success(
						function(response) {

							
							$scope.advertisements.length=0;	
							$scope.advertisements.push.apply($scope.advertisements,response);
							
						
						}).error(function(response) {
				});

	}
	

	/*
	 * 1. changes value of left spinner 2. gets new values for right spinner 3.
	 * gets the advertisements related to left and right spinners.
	 */
	$scope.leftSpinner = function(leftSpinner) {
		
		$scope.leftSpinnerHeader = leftSpinner;
		
		if(leftSpinner==APARTMENT_TYPE)
			{
			
			$scope.rightSpinnerValues.length=0;
			$scope.rightSpinnerValues.push.apply($scope.rightSpinnerValues,apartmentTypes);

			$scope.rightSpinnerHeader = apartmentTypes[0];

			$scope.getSimpleSearchAds($scope.leftSpinnerHeader,$scope.rightSpinnerHeader);

			}
		else if(leftSpinner==APARTMENT_NAME)
			{
			StudentAssist.getAllApartmentNames().success(
					function(response) {
						
						$scope.rightSpinnerValues.length=0;
						
						for(var i=0;i<response.length;i++)
							{
							
		$scope.rightSpinnerValues.push(response[i].apartmentName);

							
							}
						
						
						$scope.rightSpinnerHeader = response[0].apartmentName;
						
						$scope.getSimpleSearchAds($scope.leftSpinnerHeader,$scope.rightSpinnerHeader);
					}).error(function(response) {
			});
			
			}
		else
			{
			
			$log.log(gender);
			
			$scope.rightSpinnerValues.length=0;
			$scope.rightSpinnerValues.push.apply($scope.rightSpinnerValues,gender);

			$scope.rightSpinnerHeader = gender[0];
			$scope.getSimpleSearchAds($scope.leftSpinnerHeader,$scope.rightSpinnerHeader);

			}

		

	}

	
	// calling left spinner to get the deafult values at page load.
	$scope.leftSpinner($scope.leftSpinnerHeader);

	// 1. changes the value of right spinner.
	// 2. ets the advertisements related to left and right spinners.
	$scope.rightSpinner = function(rightSpinner) {

		
		$scope.rightSpinnerHeader = rightSpinner;
		$scope.getSimpleSearchAds($scope.leftSpinnerHeader,$scope.rightSpinnerHeader);


	}

	$scope.submitClicked = function() {

		var apartmentName = $scope.rightSpinnerValues[0].apartmentName;

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
