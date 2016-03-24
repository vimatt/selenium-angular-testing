'use strict';

angular.module('demoSite.favorites', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/favorites', {
            templateUrl: 'template/favorites.html',
            controller: 'FavoritesController'
        });
    }])

    .controller('FavoritesController', ['$scope', 'Data', function ($scope, Data) {

        $scope.dataLoading = true;

        Data.getData("http://localhost:9090/favorites").then(function (response) {
            $scope.movies = response.data;
        })
            .finally(function () {
                $scope.dataLoading = false;
            });
    }]);