angular.module('ui.bootstrap.demo', ['ngAnimate', 'ui.bootstrap']);

var module = angular.module('ui.bootstrap.demo');



module.service('StudentAssist', function ($http,$log) {
	

	var apartmentNames =[];
	var apartmentNames2 =[{apartmentName:'apartmentNames'}];

	
	
	this.getAllapartmentsList=function(){		
		
		return apartmentNames;
	}
	
this.getAllapartmentsList2=function(){		
		
		return apartmentNames2;
	}
	
	
		
	this.getApartmentNames=function(apartmentType){
		
		 var aptNamesurl = url + "getApartmentNames?apartmentType="+map[apartmentType];

		  		  $http.get(aptNamesurl)
		  .then(function (response) {
			  
			  apartmentNames2.length=0;
			  apartmentNames.length=0;
			  
			  
			  var temp = response.data;
			  apartmentNames.push.apply(apartmentNames, temp);
			  apartmentNames.push.apply(apartmentNames2, temp);
			  


		  });
		
	}		
	
	
	
	
	
});



module.controller('DropdownCtrl', function ($scope, $log,$http,StudentAssist) {
  
$scope.default_no_rooms="1 bhk/1 bath";
$scope.default_vacancies="1";
$scope.default_gender="Male";


$scope.noOfRooms=noOfRooms;
$scope.vacancies=vacancies;
$scope.gender=gender;




	
	//initilize apartmentTypes
	$scope.apartmenTypes=apartmenTypes;
	
	$scope.defaultApartmentName = StudentAssist.getAllapartmentsList2();
	


	$scope.apartmentNames=StudentAssist.getAllapartmentsList();
	
	

	StudentAssist.getApartmentNames("On-Campus");
		
		
	
	$scope.submitClicked= function()
	{
		
		var apartmentName= $scope.apartmentNames[0].apartmentName;
		
		var parameters = "createAccommodationAdd?"+APARTMENT_NAME+"="+encodeURIComponent(apartmentName)+"&"
						+NO_OF_ROOMS+"="+ encodeURIComponent($scope.default_no_rooms)+"&"
						+VACANCIES+"="+encodeURIComponent($scope.default_vacancies)+"&"
						+COST+"="+encodeURIComponent($scope.cost)+"&"
						+GENDER+"="+encodeURIComponent($scope.default_gender)+"&"
						+FBID+"="+encodeURIComponent($scope.fbId)+"&"
						+NOTES+"="+encodeURIComponent($scope.notes);
		
		var submitUrl = url+ parameters;
		
		$log.log(submitUrl);
						
						
		
		
	}

  
  $scope.changeAptName=function(apartmentName){
	  
		var temp =[{apartmentName:apartmentName}];
		
		$scope.defaultApartmentName.length=0;
		
		$scope.defaultApartmentName.push.apply($scope.defaultApartmentName,temp);
	  
	  
  }
  
$scope.changeVacancies=function(vacancies){
	  
	  $scope.default_vacancies=vacancies;
  }
  
$scope.changeGender=function(gender){
	  
	  $scope.default_gender=gender;
}

$scope.changeNoOfRooms=function(noOfRooms){
	  
	  $scope.default_no_rooms=noOfRooms;
}

		$scope.changeApartmentType=function(apartmentType){
			
			$scope.apartmentType=apartmentType;
			
			StudentAssist.getApartmentNames(apartmentType);

		}
  
  
  $scope.init = function() {
	    $scope.apartmentType = "apartmentTypes";
	    $scope.aptName = "apartmentNames";
	}


  $scope.status = {
    isopen: false
  };


  $scope.toggleDropdown = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.status.isopen = !$scope.status.isopen;
  };

  $scope.appendToEl = angular.element(document.querySelector('#dropdown-long-content'));
});



