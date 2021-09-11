var app = angular.module('admin-app', ['ngRoute']);
app.config(function($routeProvider) {
	$routeProvider.when('/authorize', {
		templateUrl: "/assets/admin/authority/index.html",
		controller: 'authority-ctrl'
	}).when('/user', {
		templateUrl: "/assets/admin/user/index.html",
		controller: 'user-ctrl'
	}).when('/unauthorized', {
		templateUrl: "/assets/admin/authority/unauthorized.html",
		controller: 'authority-ctrl'
	})

		.otherwise({
			template: "<h1 class='text-center'> FPT Polytechnic Administration</h1>"
		});

})


window.addEventListener('DOMContentLoaded', event => {

	// Toggle the side navigation
	const sidebarToggle = document.body.querySelector('#sidebarToggle');
	if (sidebarToggle) {
		// Uncomment Below to persist sidebar toggle between refreshes
		// if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
		//     document.body.classList.toggle('sb-sidenav-toggled');
		// }
		sidebarToggle.addEventListener('click', event => {
			event.preventDefault();
			document.body.classList.toggle('sb-sidenav-toggled');
			localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
		});
	}

});

