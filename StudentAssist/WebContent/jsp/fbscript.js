var userId;
$(document).ready(function() {

	
	$('.homeScreenIntent').click(function(){
		

		$('link[title=A]')[0].disabled=true;

		$('.center').hide();
		
		/*$(".center").animate({
			width : 'hide'
		}, 350);
*/

		
		$("#homeScreenActivity").animate({
			width : 'show'
		}, 350);


		
		
	});
	
	$('#login').click(function(){
		
		FB.login(function(response) {
			if (response.status === 'connected') {

				loggedIn();

			} else {

				$('#progressBar').hide();
				$('.loginButton').show();

			}

		}, {
			scope : 'email'
		});
		
	});
	
	

});

window.fbAsyncInit = function() {
	FB.init({
		appId : '297115703779135',
		xfbml : true,
		version : 'v2.5'
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




function loggedIn() {
	$("#loginLanding").animate({
		width : 'toggle'
	}, 350);
	$("#homeScreen").animate({
		width : 'toggle'
	}, 350);

	FB.api('/me', 'GET', {
		fields : 'first_name,last_name,id'
	},
			function(response) {

				$(".headerImage").attr(
						"src",
						"http://graph.facebook.com/" + response.id
								+ "/picture?type=normal");
				$(".userName").text(
						response.first_name + " " + response.last_name);

				var createUserUrl = url + "createUser?" + FIRST_NAME + "="
						+ response.first_name + "&" + LAST_NAME + "="
						+ response.last_name + "&" + USER_ID + "="
						+ "123444";

				console.log("url==" + createUserUrl);

				/*
				 * $.get( createUserUrl, function( data ) { $( ".result"
				 * ).html( data );
				 * 
				 * 
				 * });
				 */

			});

}



