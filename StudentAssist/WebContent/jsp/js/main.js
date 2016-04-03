if (navigator.serviceWorker) {
	console.log("ServiceWorkerssupported");

	navigator.serviceWorker.register('sw.js', {
		scope : './'
	}).then(function(reg) {
		console.log("ServiceWorkerstered", reg);
	});

}

function requestNotificationPermission() {
	
	if (Notification.requestPermission) {
		Notification.requestPermission(function(result) {
			console.log("Notificationission : ", result);
		});
	} else {
		console.log("Notificationssupported by this browser.");
	}
}

function registerForPush() {
	if (navigator.serviceWorker.controller) {
		navigator.serviceWorker.ready.then(function(serviceWorkerRegistration) {
			serviceWorkerRegistration.pushManager.subscribe({
				userVisibleOnly : true
			}).then(
					function(subscription) {
						console.log("SubscriptionPush successful: ",
								subscription.endpoint);
					});
		});
	} else {
		console.log("No ServiceWorker");
	}
}