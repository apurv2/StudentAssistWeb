


angular.module('ui.bootstrap.demo', [ 'ngAnimate', 'ui.bootstrap' ]);

var module = angular.module('ui.bootstrap.demo');



module.directive("mdpagination", function(){
    return {
        restrict: 'A',
        scope: false, //default
        link: function(scope, element, attrs){
            element.on('click', function(event){


   		     

  			  event.preventDefault();
  		      
  		      
  		      var $div = $('<div/>'),
  		          btnOffset = $(this).offset(),
  		      		xPos = event.pageX - btnOffset.left,
  		      		yPos = event.pageY - btnOffset.top;
  		      

  		      
  		      $div.addClass('ripple-effect');
  		      var $ripple = $(".ripple-effect");
  		      
  		      $ripple.css("height", $(this).height());
  		      $ripple.css("width", $(this).height());
  		      $div
  		        .css({
  		          top: yPos - ($ripple.height()/2),
  		          left: xPos - ($ripple.width()/2),
  		          background: $(this).data("ripple-color")
  		        }) 
  		        .appendTo($(this));

  		      window.setTimeout(function(){
  		        $div.remove();
  		      }, 2000);
  		    
            
            
            })
        }
    }
});










module.service('StudentAssist', function($http, $log) {

	this.getApartmentNames = function(apartmentType) {

		var aptNamesurl = url + "getApartmentNames?apartmentType="
				+ map[apartmentType];

		return $http.get(aptNamesurl);
	};

	this.getAllApartmentNames = function() {
		var aptNamesurl = url + "getAllApartmentNames";

		return $http.get(aptNamesurl);
	}

	this.getSimpleSearchAdds = function(leftSpinner, rightSpinner) {

		var urlAcc = url + "getSimpleSearchAdds?leftSpinner=" + leftSpinner
				+ "&rightSpinner=" + rightSpinner;

		return $http.get(urlAcc);

	};

	this.submitAdd = function(submitUrl) {
		$http.get(submitUrl).then(function(response) {

			window.alert(response.data);

		});

	}

	this.advancedSearch = function(apartmentName, gender) {
		// alert("hello");

		var advancedSearchUrl = url
				+ "getAdvancedAdvertisements?apartmentName=" + apartmentName
				+ "&gender=" + gender;

		return $http.get(advancedSearchUrl);

	}

});

module.controller('advancedSearchController', function($scope, $log, $http,
		StudentAssist) {

	
	$scope.apartmentTypeHeader = ON_CAMPUS;
	$scope.apartmentNameHeader = "";
	$scope.genderHeader = MALE;

	$scope.apartmentTypes = apartmentTypes;
	$scope.apartmentNames = [];
	$scope.gender = gender;
	$scope.advertisements = [];

	$scope.adDetails_apartmentName = "appugadu";
	
	
	$scope.fbCallBack = function()
	{
		
		
		console.log("aptName=="+	$scope.adDetails_apartmentName);
		
	}
	
	
	$scope.apartmentType = function(apartmentType) {

		$scope.apartmentTypeHeader = apartmentType;

		StudentAssist.getApartmentNames(apartmentType).success(
				function(response) {

					$scope.apartmentNameHeader = response[0].apartmentName;
					$scope.apartmentNames.length = 0;
					$scope.apartmentNames.push.apply($scope.apartmentNames,
							response);

				}).error(function(response) {
		});

	}
	
	// called when an add on left panel is clicked
	$scope.addClick = function(advertisement) {
		
			$scope.adDetails_apartmentName = advertisement.apartmentName;
			$scope.adDetails_noOfRooms = advertisement.noOfRooms;
			$scope.adDetails_vacancies =advertisement.vacancies;
			$scope.adDetails_cost = advertisement.cost;
			$scope.adDetails_notes = advertisement.notes;
			$scope.adDetails_gender = advertisement.gender;
			$scope.adDetails_userId =advertisement.userId;
			$scope.adDetails_name =advertisement.firstName +" "+advertisement.lastName ;
			
			console.log("vcalue=="+	$scope.adDetails_apartmentName);


	}


	$scope.searchVacancy = function() {

		var apartmentName = encodeURIComponent($scope.apartmentNameHeader);
		var gender = encodeURIComponent($scope.genderHeader);

		StudentAssist.advancedSearch(apartmentName, gender).success(
				function(response) {

					$scope.advertisements.length = 0;
					$scope.advertisements.push.apply($scope.advertisements,
							response);
					
					

					if($scope.advertisements.length>0)
						{
					$scope.adDetails_apartmentName = $scope.advertisements[0].apartmentName;
					$scope.adDetails_noOfRooms = $scope.advertisements[0].noOfRooms;
					$scope.adDetails_vacancies =$scope.advertisements[0].vacancies;
					$scope.adDetails_cost = $scope.advertisements[0].cost;
					$scope.adDetails_notes = $scope.advertisements[0].notes;
					$scope.adDetails_gender = $scope.advertisements[0].gender;
					$scope.adDetails_userId =$scope.advertisements[0].userId;
					$scope.adDetails_name =$scope.advertisements[0].firstName +" "+$scope.advertisements[0].lastName ;

						}

					
					
				}).error(function(response) {
		});

	}

	$scope.apartmentType(ON_CAMPUS);

	$scope.apartmentName = function(apartmentName) {

		$scope.apartmentNameHeader = apartmentName;

	}

	$scope.changeGenderHeader = function(gender) {

		$scope.genderHeader = gender;

	}

});

module.controller('postAccommodationController', function($scope, $log, $http,
		StudentAssist) {

	$scope.apartmentTypeHeader = ON_CAMPUS;
	$scope.apartmentNameHeader = "";
	$scope.genderHeader = MALE;
	$scope.noOfRoomsHeader = noOfRooms[0];
	$scope.vacanciesHeader = vacancies[0];

	$scope.vacancies = vacancies;
	$scope.noOfRooms = noOfRooms;
	$scope.apartmentTypes = apartmentTypes;
	$scope.apartmentNames = [];
	$scope.gender = gender;
	$scope.advertisements = [];

	$scope.apartmentType = function(apartmentType) {

		$scope.apartmentTypeHeader = apartmentType;

		StudentAssist.getApartmentNames(apartmentType).success(
				function(response) {

					$scope.apartmentNameHeader = response[0].apartmentName;
					$scope.apartmentNames.length = 0;
					$scope.apartmentNames.push.apply($scope.apartmentNames,
							response);

				}).error(function(response) {
		});

	}

	$scope.searchVacancy = function() {

		var apartmentName = encodeURIComponent($scope.apartmentNameHeader);
		var gender = encodeURIComponent($scope.genderHeader);

		StudentAssist.advancedSearch(apartmentName, gender).success(
				function(response) {

					$scope.advertisements.length = 0;
					$scope.advertisements.push.apply($scope.advertisements,
							response);

				}).error(function(response) {
		});

	}
	
	

	$scope.apartmentType(ON_CAMPUS);

	$scope.apartmentName = function(apartmentName) {

		$scope.apartmentNameHeader = apartmentName;

	}

	$scope.changeGenderHeader = function(gender) {

		$scope.genderHeader = gender;

	}

	$scope.changeNoOfRoomsHeader = function(noOfRooms) {

		$scope.noOfRoomsHeader = noOfRooms;

	}

	$scope.changeVacanciesHeader = function(vacancies) {
		$scope.vacanciesHeader = vacancies;
	}

	$scope.postVacancy = function(firstName, lastName) {

		var parameters = POST_ACCOMMODATION + "?" + APARTMENT_NAME + "="
				+ encodeURIComponent($scope.apartmentNameHeader) + "&"
				+ NO_OF_ROOMS + "="
				+ encodeURIComponent($scope.noOfRoomsHeader) + "&" + VACANCIES
				+ "=" + encodeURIComponent($scope.vacanciesHeader) + "&" + COST
				+ "=" + encodeURIComponent($scope.cost) + "&" + GENDER + "="
				+ encodeURIComponent($scope.genderHeader) + "&" + FBID + "="
				+ encodeURIComponent($scope.fbId) + "&" + NOTES + "="
				+ encodeURIComponent($scope.notes) + "&" + FIRST_NAME + "="
				+ encodeURIComponent(firstName) + "&" + LAST_NAME + "="
				+ encodeURIComponent(lastName);

		var submitUrl = url + parameters;

		 StudentAssist.submitAdd(submitUrl);

	}

	$scope.submitClicked = function() {

		
		FB.getLoginStatus(function(response) {
			if (response.status === 'connected') {

				FB.api('/' + $scope.fbId, function(response) {
					var split = response.name.split(" ");
					var firstName, lastName;

					if (split.length > 1) {
						firstName = split[0];
						lastName = split[split.length - 1];
						$scope.postVacancy(firstName, lastName);
					}

				});
			}
		});
	}

});

module.controller('simpleSearchController', function($scope, $log, $http,
		StudentAssist) {
	
	$scope.leftSpinnerHeader = APARTMENT_TYPE;
	$scope.rightSpinnerHeader = "";

	$scope.rightSpinnerValues = [];
	$scope.advertisements = [];

	$scope.default_no_rooms = "1 bhk/1 bath";
	$scope.default_vacancies = "1";
	$scope.default_gender = "Male";
	$scope.noOfRooms = noOfRooms;
	$scope.vacancies = vacancies;
	$scope.gender = gender;

	// initilize leftSpinner
	$scope.leftSpinnerValues = leftSpinnerValues;

	$scope.getSimpleSearchAds = function(leftSpinner, rightSpinner) {

		var queryparam = "";

		queryparam = map[rightSpinner] == null ? rightSpinner
				: map[rightSpinner];

		leftSpinner = leftSpinnerCodeMap[leftSpinner];
		rightSpinner = encodeURIComponent(queryparam);

		// get simple serarch adds
		StudentAssist.getSimpleSearchAdds(leftSpinner, rightSpinner).success(
				function(response) {

					$scope.advertisements.length = 0;
					$scope.advertisements.push.apply($scope.advertisements,
							response);
					
					if($scope.advertisements.length>0)
						{
					
					$scope.adDetails_apartmentName = $scope.advertisements[0].apartmentName;
					$scope.adDetails_noOfRooms = $scope.advertisements[0].noOfRooms;
					$scope.adDetails_vacancies =$scope.advertisements[0].vacancies;
					$scope.adDetails_cost = $scope.advertisements[0].cost;
					$scope.adDetails_notes = $scope.advertisements[0].notes;
					$scope.adDetails_gender = $scope.advertisements[0].gender;
					$scope.adDetails_userId =$scope.advertisements[0].userId;
					$scope.adDetails_name =$scope.advertisements[0].firstName +" "+$scope.advertisements[0].lastName ;

						}


				}).error(function(response) {
		});

	}

	
	/*
	 * 1. changes value of left spinner 2. gets new values for right spinner 3.
	 * gets the advertisements related to left and right spinners.
	 */
	$scope.leftSpinner = function(leftSpinner) {

		$scope.leftSpinnerHeader = leftSpinner;

		if (leftSpinner == APARTMENT_TYPE) {

			$scope.rightSpinnerValues.length = 0;
			$scope.rightSpinnerValues.push.apply($scope.rightSpinnerValues,
					apartmentTypes);

			$scope.rightSpinnerHeader = apartmentTypes[0];

			$scope.getSimpleSearchAds($scope.leftSpinnerHeader,
					$scope.rightSpinnerHeader);

		} else if (leftSpinner == APARTMENT_NAME2) {
			StudentAssist.getAllApartmentNames().success(
					function(response) {

						$scope.rightSpinnerValues.length = 0;

						for (var i = 0; i < response.length; i++) {

							$scope.rightSpinnerValues
									.push(response[i].apartmentName);

						}

						$scope.rightSpinnerHeader = response[0].apartmentName;

						$scope.getSimpleSearchAds($scope.leftSpinnerHeader,
								$scope.rightSpinnerHeader);
					}).error(function(response) {
			});

		} else {

			$scope.rightSpinnerValues.length = 0;
			$scope.rightSpinnerValues.push.apply($scope.rightSpinnerValues,
					gender);

			$scope.rightSpinnerHeader = gender[0];
			$scope.getSimpleSearchAds($scope.leftSpinnerHeader,
					$scope.rightSpinnerHeader);

		}

	}

	// calling left spinner to get the deafult values at page load.
	$scope.leftSpinner($scope.leftSpinnerHeader);

	// 1. changes the value of right spinner.
	// 2. ets the advertisements related to left and right spinners.
	$scope.rightSpinner = function(rightSpinner) {

		$scope.rightSpinnerHeader = rightSpinner;
		$scope.getSimpleSearchAds($scope.leftSpinnerHeader,
				$scope.rightSpinnerHeader);

	}
	
	
	// called when an add on left panel is clicked
	$scope.addClick = function(advertisement) {
		
		
			$scope.adDetails_apartmentName = advertisement.apartmentName;
			$scope.adDetails_noOfRooms = advertisement.noOfRooms;
			$scope.adDetails_vacancies =advertisement.vacancies;
			$scope.adDetails_cost = advertisement.cost;
			$scope.adDetails_notes = advertisement.notes;
			$scope.adDetails_gender = advertisement.gender;
			$scope.adDetails_userId =advertisement.userId;
			$scope.adDetails_name =advertisement.firstName +" "+advertisement.lastName ;

			
			console.log("apt nddddame=="+$scope.adDetails_apartmentName);


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
