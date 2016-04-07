<% int a = 10; String hiddenField="12"; %>
<%String url3="http://studentassist.elasticbeanstalk.com/rest/accommodation/";%>



<div class="panel panel-default" style=" background-image: url('../images/green-background.jpg'); overflow-y:hidden !important;height:100%;" >
 
 <div class="panel-body" style="margin-bottom:100px;margin-top:10px">

<div ng-app="studentAssistWeb">

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
     <a  class="headerCollapse ripple rippleAddList" style="padding:inherit;"href="#SimpleSearch">  <div  class="  navRow"> 
      <i class="fa fa-search istyle"></i> &nbsp&nbsp&nbsp&nbsp&nbsp&nbspSimple Search</div></a></li>
    
    <li class="istyle">
    <a  class="headerCollapse ripple rippleAddList" style="padding:inherit;"href="#AdvancedSearch"> <div class=" navRow">  
    <i class="fa fa-search-plus istyle"></i> &nbsp&nbsp&nbsp&nbsp&nbspAdvanced Search</div></a></li>
     
     
     <li class="istyle">
    <a  class="headerCollapse ripple rippleAddList" style="padding:inherit;" href="#PostAccommodation"> <div class=" navRow">  
    <i class="fa fa-paper-plane istyle"></i> &nbsp&nbsp&nbsp&nbsp&nbspPost Accommodation</div></a></li>
     
     <li class="istyle">
    <a  class="headerCollapse ripple rippleAddList" style="padding:inherit;" href="#UsersPosts"> <div class=" navRow">  
    <i class="fa fa-user istyle"></i> &nbsp&nbsp&nbsp&nbsp&nbspYour Posts</div></a></li>
    
    <li class="istyle">
    <a  class="headerCollapse ripple rippleAddList" style="padding:inherit;" href="#AdvancedSearch"> <div class=" navRow">  
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

      console.log("url hidden=="+<%=hiddenField%>+"end url");
      
      
    
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

</div>
