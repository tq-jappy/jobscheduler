'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ui.bootstrap',
  'ngRoute',
  'ui.menu',
  'ui.jobs',
  'ui.nodes',
  'myApp.version'
]).

config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/nodes'});
}]).

factory("AlertService", function() {
    return {
        alerts: []
    };
});

