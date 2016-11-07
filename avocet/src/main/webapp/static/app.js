/**
 * 
 */

var app = angular.module("kycmodule", ["ngRoute"]);

app.config(["$routeProvider", 
            function($routeProvider){
				$routeProvider
					.when("/home",{
						controller: "userController",
						templateUrl: "user.html"
					})
					.otherwise({
						redirectTo: "/login"
					});
			}         
]);




/*var app = angular.module("kycModule", ["ngRoute", "ngSanitize"])
	.config(["$routeProvider"],
			function($routeProvider){
			$routeProvider
				.when("/login",{
					controller : "userController",
					templateUrl: "user.html"
				}).when("/home",{
					controller : "homeController",
					templateUrl: "home.html"
				}).otherwise({redirectTo:"/login"});
	});
*/

