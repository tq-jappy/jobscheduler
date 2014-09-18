'use strict';

var menu = angular.module('ui.menu', [ 
  'ui.bootstrap',
  'ngRoute'
]);

menu.controller('MenuCtrl', function($scope, $location, Menu) {
	$scope.menu = Menu;
	
	$scope.getClass = function(item) {
		if ($location.path() === item.href) {
			return "active";
		} else {
			return "";
		}
	};
});

menu.directive('menu', function() {
	return {
		restrict: "A",
		template: '<ul class="menu">' +
			'<li ng-repeat="item in menu.items" ng-class="getClass(item)"><a href="{{ item.href }}">{{ item.name }}</a></li>' +
			'</ul>'
	};
});

menu.factory('Menu', function() {
	var Menu = {};
	Menu.items = [
	    {
	    	href: "#/nodes",
	    	name: "Node"
	    }
	];
	return Menu;
});