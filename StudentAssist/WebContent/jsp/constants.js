noOfRooms = [ '1 bhk/1 bath', '1 bhk/2 bath', '2 bhk/1 bath', '2 bhk/2 bath',
		'3 bhk/1 bath', '3 bhk/2 bath', '3 bhk/3 bath', 'Shared', 'Other', ];

vacancies = [ '1', '2', '3', '4', 'Other', 'Lease Transfer'];

gender = [ 'Male', 'Female', 'Doesnt Matter' ];

apartmentTypes = [ 'On-Campus', 'Off-Campus', 'Dorms' ];

leftSpinnerValues = [ 'Apartment Type', 'Apartment Name', 'Gender' ];



var map = {};
map["On-Campus"] = "on";
map["Off-Campus"] = "off";
map["Dorms"] = "dorms";

var leftSpinnerCodeMap=[];

leftSpinnerCodeMap["Apartment Type"]= "apartmentType";
leftSpinnerCodeMap["Apartment Name"]= "apartmentName";
leftSpinnerCodeMap["Gender"]= "gender";


var url = "http://studentassist.elasticbeanstalk.com/rest/accommodation/";

var APARTMENT_NAME = "apartmentName";
var NO_OF_ROOMS = "noOfRooms";
var VACANCIES = "vacancies";
var COST = "cost";
var GENDER = "gender";
var FBID = "fbId";
var NOTES = "notes";
var USER_ID = "userId";
var POST_ACCOMMODATION = "createAccommodationAddFromFacebook";
var ON_CAMPUS = "On-Campus";
var APARTMENT_TYPE='Apartment Type';
var APARTMENT_NAME='Apartment Name';
var GENDER_SPINNER='Gender';