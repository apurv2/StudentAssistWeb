noOfRooms = [
	                        '1 bhk/1 bath',
	                        '1 bhk/2 bath',
	                        '2 bhk/1 bath',
	                        '2 bhk/2 bath',
	                        '3 bhk/1 bath',
	                        '3 bhk/2 bath',
	                        '3 bhk/3 bath',
	                        'Shared',
	                        'Other',
	                      ];
	
	vacancies = [
	                        '1',
	                        '2',
	                        '3',
	                        '4',
	                        'Other',
	                        'Lease Transfer',
	                      ];
	
	gender = [
	                        'Male',
	                        'Female',
	                         'Doesnt Matter'
	                      ];
	
	
	apartmenTypes = [
	                        'On-Campus',
	                        'Off-Campus',
	                        'Dorms'
	                      ];
	         
	
	
	var map={};
	map["On-Campus"]="on";
	map["Off-Campus"]="off";
	map["Dorms"]="dorms";
	
	var url="http://sassist-9vtjvsmaju.elasticbeanstalk.com/rest/accommodation/";

	
	