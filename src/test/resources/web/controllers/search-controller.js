'use strict';

angular.module('demoSite.search', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/search', {
            templateUrl: 'template/search.html',
            controller: 'SearchController'
        });
    }])

    .controller('SearchController', ['$scope', '$http', 'Data', 'Season', function ($scope, $http, Data, Season) {

        $scope.type = 'movie';
        $scope.search = '';
        $scope.season = '';
        $scope.year = '';
        $scope.types = {
            availableTypes: [
                {id: '1', name: 'movies'},
                {id: '2', name: 'series'}
            ],
            selectedType: {id: '1', name: 'movies'}
        };
        var queryString = '';

        $scope.$watchGroup(['search', 'season', 'type', 'year'], function () {
            $scope.fetch();
        });

        $scope.fetch = function() {
            setSearchQuery();
            Data.getData(queryString).then(function (response) {
                $scope.allData = response.data;
            });
        };

        function setSearchQuery() {
            switch ($scope.type) {
                case 'movie':
                    queryString = 'http://www.omdbapi.com/?s=' + $scope.search + '&type=movie&y=' + $scope.year + '&plot=short&r=json';
                    break;
                case 'series':
                    queryString = 'http://www.omdbapi.com/?s=' + $scope.search + '&type=series&season=' + $scope.season + '&plot=short&r=json';
                    break;
            }
        }

    }]);