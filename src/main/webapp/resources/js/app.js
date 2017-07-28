$(document).ready(function() {
	$('.popdown').popdown({
		width : 700
	});
});

function initMap() {
	var directionsService = new google.maps.DirectionsService;
	var directionsDisplay = new google.maps.DirectionsRenderer({
		map : map,
		panel : document.getElementById('right-panel')
	});
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 13,
		center : {
			lat : -5.10562,
			lng : -38.3671
		}
	});
	directionsDisplay.setMap(map);

	document.getElementById('submit').addEventListener('click', function() {
		calculateAndDisplayRoute(directionsService, directionsDisplay);
	});
}

function calculateAndDisplayRoute(directionsService, directionsDisplay) {
	var waypts = [];
	var checkboxArray = document.getElementById('waypoints');
	for (var i = 0; i < checkboxArray.length; i++) {
		if (checkboxArray.options[i].selected) {
			waypts.push({
				location : checkboxArray[i].value,
				stopover : true
			});
		}
	}

	directionsService.route({
		origin : '-5.108643, -38.382069',// document.getElementById('start').value,
		destination : '-5.108134, -38.373137',// document.getElementById('end').value,
		waypoints : waypts,
		optimizeWaypoints : true,
		travelMode : 'DRIVING'
	}, function(response, status) {
		if (status === 'OK') {
			directionsDisplay.setDirections(response);
			// computeTotalDistance(directionsDisplay.getDirections());
		} else {
			window.alert('A solicitação de instruções falhou devido a: '
					+ status);
		}
	});

	function computeTotalDistance(result) {
		var total = 0;
		var myroute = result.routes[0];
		for (var i = 0; i < myroute.legs.length; i++) {
			total += myroute.legs[i].distance.value;
		}
		// total = total / 1000;
		document.getElementById('total').innerHTML = total + ' m';
	}
}