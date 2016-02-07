<!DOCTYPE html>
<html>



 <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-animate.js"></script>
    <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.1.2.js"></script>
    <script src="example.js"></script>
        <script src="constants.js"></script>
    
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
  

<body>

<!-- ApartmentType -->

<div ng-app="ui.bootstrap.demo" ng-controller="DropdownCtrl" ng-init="init()">

<form class="form-horizontal">
<fieldset>

<div class="form-group">
  <label class="col-md-4 control-label" for="selectbasic">Apartment Type:</label>
  <div class="col-md-4">
  
    <span uib-dropdown on-toggle="toggled(open)">
    
      <a href id="simple-dropdown" uib-dropdown-toggle>
        {{apartmentType}}

      </a>
      <ul uib-dropdown-menu aria-labelledby="simple-dropdown">
        <li ng-repeat="choice in apartmenTypes">
          <a ng-click="changeApartmentType(choice)" href>{{choice}}</a>
        </li>
      </ul>
    </span>
    

  </div>
</div>

<!-- ApartmentName -->
<div class="form-group">
  <label class="col-md-4 control-label" for="selectbasic">ApartmentName:</label>
  <div class="col-md-4">
     <span uib-dropdown on-toggle="toggled(open)">
    
      <a  href id="simple-dropdown" uib-dropdown-toggle>
        {{defaultApartmentName[0].apartmentName}}

      </a>
      <ul uib-dropdown-menu aria-labelledby="simple-dropdown">
        <li ng-repeat="choice in apartmentNames">
          <a ng-click="changeAptName(choice.apartmentName)" href>{{choice.apartmentName}}</a>
        </li>
      </ul>
    </span>

  </div>
</div>

<!-- NoOfRooms -->
<div class="form-group">
  <label class="col-md-4 control-label" for="selectbasic">No. of Rooms:</label>
  <div class="col-md-4">
     <span uib-dropdown on-toggle="toggled(open)">
    
      <a  href id="simple-dropdown" uib-dropdown-toggle>
        {{default_no_rooms}}

      </a>
      <ul uib-dropdown-menu aria-labelledby="simple-dropdown">
        <li ng-repeat="choice in noOfRooms">
          <a ng-click="changeNoOfRooms(choice)" href>{{choice}}</a>
        </li>
      </ul>
    </span>

  </div>
</div>


<!-- Vacancies -->
<div class="form-group">
  <label class="col-md-4 control-label" for="selectbasic">vacancies:</label>
  <div class="col-md-4">
     <span uib-dropdown on-toggle="toggled(open)">
    
      <a  href id="simple-dropdown" uib-dropdown-toggle>
        {{default_vacancies}}

      </a>
      <ul uib-dropdown-menu aria-labelledby="simple-dropdown">
        <li ng-repeat="choice in vacancies">
          <a ng-click="changeVacancies(choice)" href>{{choice}}</a>
        </li>
      </ul>
    </span>

  </div>
</div>


<!-- Gender -->

<div class="form-group">
  <label class="col-md-4 control-label" for="selectbasic">gender:</label>
  <div class="col-md-4">
     <span uib-dropdown on-toggle="toggled(open)">
    
      <a  href id="simple-dropdown" uib-dropdown-toggle>
        {{default_gender}}

      </a>
      <ul uib-dropdown-menu aria-labelledby="simple-dropdown">
        <li ng-repeat="choice in gender">
          <a ng-click="changeGender(choice)" href>{{choice}}</a>
        </li>
      </ul>
    </span>

  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">Cost/Month</label>  
  <div class="col-md-4">
  <input id="textinput" name="textinput" type="text" placeholder="cost/month" class="form-control input-md">
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">Notes/Comments</label>  
  <div class="col-md-4">
  <input id="textinput" name="textinput" type="text" placeholder="notes/comments" class="form-control input-md">
  <span class="help-block">help</span>  
  </div>

</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">Facebook Id</label>  
  <div class="col-md-4">
  <input id="textinput" name="textinput" type="text" placeholder="Facebook Id" class="form-control input-md">
  <span class="help-block">help</span>  
  </div>

</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="singlebutton"></label>
  <div class="col-md-4">
    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Submit</button>
  </div>
</div>

</fieldset>
</form>
</body>
</html>
