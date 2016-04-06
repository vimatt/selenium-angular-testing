'use strict';

angular.module('demoSite.favorites', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/favorites/:type', {
            templateUrl: 'template/favorites.html',
            controller: 'FavoritesController'
        });
    }])

    .controller('FavoritesController', ['$scope', 'Data', '$routeParams', function ($scope, Data, $routeParams) {

        $scope.type = $routeParams.type;
        $scope.dataLoading = true;

        if ($scope.type === 'movies') {
            Data.getData("http://localhost:9090/favoritemovies").then(function (response) {
                    $scope.movies = response.data;
                })
                .finally(function () {
                    $scope.dataLoading = false;
                });
        } else if ($scope.type === 'series') {
            Data.getData("http://localhost:9090/favoriteseries").then(function (response) {
                    $scope.movies = response.data;
                })
                .finally(function () {
                    $scope.dataLoading = false;
                });
        }
    }]);