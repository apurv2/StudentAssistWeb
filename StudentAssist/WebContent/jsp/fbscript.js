var userId;
var permissionResult = false;
var createUserUrl = "";
$(document)
  .ready(
    function() {

      $('.homeScreenIntent')
        .click(
          function() {

            // user denied notification permission
            // so sending user information to server
            if (permissionResult == false) {
              console.log("user denied notification permission so sending user information to server");
              createUser(createUserUrl);
            }

         
            $('link[title=A]')[0].disabled = true;
            $('.center').hide();
            $("#homeScreenActivity").animate({
              width: 'show'
            }, 350);

          });

      $('#login').click(function() {

        FB.login(function(response) {
          if (response.status === 'connected') {

            loggedIn();

          } else {

            $('#progressBar').hide();
            $('.loginButton').show();

          }

        }, {
          scope: 'email'
        });

      });

    });
//931333680308184 - fb id for aws
window.fbAsyncInit = function() {
  FB.init({
    appId: '297115703779135',
    xfbml: true,
    version: 'v2.5'
  });

  FB.getLoginStatus(function(response) {
    if (response.status === 'connected') {

      loggedIn();

    } else {

      $('#progressBar').hide();
      $('.loginButton').show();

    }
  });
};
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) {
    return;
  }
  js = d.createElement(s);
  js.id = id;
  js.async = true;
  js.src = "//connect.facebook.net/en_US/sdk.js";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function adClick(clickedAdvertisement)
{
	
	$(clickedAdvertisement).siblings().css('background-color','white');
	$(clickedAdvertisement).css('background-color','#FAFAFA');


}

function loggedIn() {
  $("#loginLanding").animate({
    width: 'toggle'
  }, 350);
  $("#homeScreen").animate({
    width: 'toggle'
  }, 350);

  FB.api('/me', 'GET', {
    fields: 'first_name,last_name,id'
  }, function(response) {

    $(".headerImage").attr(
      "src",
      "http://graph.facebook.com/" + response.id + "/picture?type=normal");
    $(".userName").text(response.first_name + " " + response.last_name);

    createUserUrl = url + "createUser?" + FIRST_NAME + "=" + response.first_name + "&" + LAST_NAME + "=" + response.last_name + "&" + USER_ID + "=" + response.id;

    userId = response.id;

    console.log("url==" + createUserUrl);

    askPushNotificationPermission(createUserUrl);

  });

}

function askPushNotificationPermission(createUserUrl) {

  if (Notification.requestPermission) {
    Notification
      .requestPermission(function(result) {

        permissionResult = true;
        console.log("Notification permission : ", result);

        if (navigator.serviceWorker) {
        	console.log("ServiceWorkerssupported");

        	navigator.serviceWorker.register('sw.js', {
        		scope : './'
        	}).then(function(reg) {
        		console.log("ServiceWorkerstered", reg);
        	});

        }
        
        
        
        if (result == 'granted') {

          var gcmId;

          if (navigator.serviceWorker.controller) {
            navigator.serviceWorker.ready
              .then(function(serviceWorkerRegistration) {
                serviceWorkerRegistration.pushManager
                  .subscribe({
                    userVisibleOnly: true
                  })
                  .then(
                    function(subscription) {
                      console
                        .log(
                          "SubscriptionPush successful: ",
                          subscription.endpoint);

                      gcmId = subscription.endpoint
                        .substring(40);

                      console
                        .log("gcmId=" + gcmId);
                      createUserUrl = createUserUrl + "&deviceId=" + "chrome" + "&gcmId" + "=" + gcmId;

                      createUser(createUserUrl);

                    });
              });
          } else {
            console.log("No ServiceWorker");
          }

        } else {

          console.log("permission not granted");

          createUser(createUserUrl);

        }

      });
  }
}

function createUser(createUserUrl) {

  console.log("create user url==" + createUserUrl);

  $.get(createUserUrl, function(data) {

	  	console.log(data);
	  
  });

}
