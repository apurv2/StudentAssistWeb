<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">


<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-animate.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9//angular-route.js"></script>


<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.1.2.js"></script>


<link href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="./SAstyles.css">
<link rel="stylesheet" type="text/css" href="./foundation.css">


<script async src="fbscript.js"></script>
<script src="example.js"></script>
<script src="constants.js"></script>

<!-- drawer.css -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.1.0/css/drawer.min.css">
<!-- jquery & iScroll -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/iScroll/5.1.3/iscroll.min.js"></script>
<!-- drawer.js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.1.0/js/drawer.min.js"></script>




<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>


<div ng-app="ui.bootstrap.demo">

  <body class="drawer drawer--left">

    <header role="banner">
      <button type="button" class="drawer-toggle drawer-hamburger">
        <span class="sr-only">toggle navigation</span>
        <span class="drawer-hamburger-icon"></span>
      </button>
      <nav class="drawer-nav" role="navigation">

        <div class="navHeader">
          <img class="headerImage" />

          <label class="userName"></label>
        </div>

        <div class="drawer-menu">

        <ul id="staggeredList">
    
	<li class="istyle">    
     <a  class="headerCollapse" style="padding:inherit;"href="#SimpleSearch">  <div  class="  navRow"> 
      <i class="fa fa-search istyle"></i> &nbsp&nbsp&nbsp&nbsp&nbsp&nbspSimple Search</div></a></li>
    
    <li class="istyle">
    <a  class="headerCollapse" style="padding:inherit;"href="#AdvancedSearch"> <div class=" navRow">  
    <i class="fa fa-search-plus istyle"></i> &nbsp&nbsp&nbsp&nbsp&nbspAdvanced Search</div></a></li>
     
     
     <li class="istyle">
    <a  class="headerCollapse" style="padding:inherit;" href="#PostAccommodation"> <div class=" navRow">  
    <i class="fa fa-paper-plane istyle"></i> &nbsp&nbsp&nbsp&nbsp&nbspPost Accommodation</div></a></li>
     
     <li class="istyle">
    <a  class="headerCollapse" style="padding:inherit;" href="#AdvancedSearch"> <div class=" navRow">  
    <i class="fa fa-user istyle"></i> &nbsp&nbsp&nbsp&nbsp&nbspYour Posts</div></a></li>
    
    <li class="istyle">
    <a  class="headerCollapse" style="padding:inherit;" href="#AdvancedSearch"> <div class=" navRow">  
    <i class="fa fa-cogs istyle"></i> &nbsp&nbsp&nbsp&nbsp&nbspSettings</div></a></li>
    
    
    
    
     </ul>

        </div>
      </nav>
    </header>
    <main role="main">
      <!-- Page content -->
    </main>

    <div>
      <ng-view>

      </ng-view>
    </div>
  </body>

  <script>
    $(document).ready(function() {

      $('#staggeredList').hide();

      $('.drawer').drawer({
        iscroll: {
          // Configuring the iScroll
          // https://github.com/cubiq/iscroll#configuring-the-iscroll
          mouseWheel: true,
          preventDefault: false
        },
        showOverlay: true
      });

      
      
      
    
      $('.drawer').on('drawer.closed', function() {

        $('#staggeredList').hide();

      });


      
      $('.drawer-hamburger').click(function() {


          $('#staggeredList').toggle();

          Materialize.showStaggeredList('#staggeredList');

        });


      $('.headerCollapse').click(function() {

        $('.drawer').drawer('close');

      });


    });

  </script>


</div>
