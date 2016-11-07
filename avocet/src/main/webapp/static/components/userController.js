/**
 * 
 */



app.controller("userController", [
   "$scope",
   "$http",
   function($scope, $http){
	   var res = $http.get("/users/list");
	   	   res.success(function(data, status, headers, config){
	   			$scope.test = data;
	   		});
   }
                                  
]);

app.controller("loginController",[
    "$scope",
    "$http",
    function($scope, $http){
    	$scope.checklogin = function(){
    		alert("----");
    		var userModel ={
        			userName: $scope.user.username,
        	    	password: $scope.user.password,	
        	};
        	var res = $http.post("/security_login", userModel);
        	res.success(function(data, status, headers, config){
        		
        	});
        	res.error(function(data, status, headers, config){
        		alert("[ Error ]");
        	});
    	}
    	
    }
]);
