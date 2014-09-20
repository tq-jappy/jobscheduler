'use strict';

angular.module('ui.nodes', ['ngRoute', 'ngResource'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/nodes',          { templateUrl: 'nodes/index.html', controller: 'NodeListCtrl' })
    .when('/nodes/new',      { templateUrl: 'nodes/new.html',   controller: 'NodeNewCtrl' })
    .when('/nodes/:id/edit', { templateUrl: 'nodes/edit.html',  controller: 'NodeEditCtrl' });    
}])

.factory("Node", function($resource) {
    var Node = $resource("/api/v1/nodes/:id", {}, {
        'update': {method: 'PUT', params: {id: '@id'}},
        'remove': {method: 'DELETE', params: {id: '@id'}}
    });
    return Node;
})

.controller('NodeListCtrl', function($scope, $modal, $http, $resource, $routeParams, Node, AlertService) {    
    $scope.alerts = AlertService;
    
    $scope.nodes = Node.query(function() {
        console.log("get nodes");
        console.log($scope.nodes);
    });
 
    $scope.deleteNode = function(id) {
        console.log("delete - id : " + id);
        Node.delete({ id: id });
        $scope.nodes = Node.query();           
        AlertService.alerts = [ { type: 'success', msg: 'Deleted node with id: ' + id } ];
    };
})

.controller('NodeNewCtrl', function($scope, $rootScope, $location, $modal, $http, Node, AlertService) {
    $scope.formData = {};
    
    $scope.createNode = function() {
        Node.save($scope.formData, function(node) {
          AlertService.alerts = [ {type: 'success', msg: 'New node created.'} ];
          $location.path("/");
        });
        $scope.formData = {};
    };    
})

.controller('NodeEditCtrl', function($scope, $location, $modal, $http, $routeParams, Node, AlertService) {
    $scope.formData = {};
    
    Node.get({id: $routeParams.id}, function(node) {
        console.log("get node");
        console.log(node);
        $scope.node = node;
    });    

    $scope.updateNode = function() {
        console.log("updating.");
        console.log($scope.node);
        
        $scope.node.$update({}, function() {
           console.log('Success update');
           AlertService.alerts = [ {type: 'success', msg: 'Node updated.'} ];
           $location.path("/");
        });
    };      
});
