'use strict';


angular.module('demoSite', [
    'ngRoute',
    'demoSite.search',
    'demoSite.movie',
    'demoSite.series',
    'demoSite.season',
    'demoSite.favorites',
    'demoSite.databaseService',
    'demoSite.seasonService'
]).config(['$routeProvider', function ($routeProvider) {
    $routeProvider.otherwise({redirectTo: '/search'});
}]);



