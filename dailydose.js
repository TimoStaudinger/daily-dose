angular.module('dailydoseApp', [])
    .controller('DailydoseController', function ($scope, $http) {
        var dailydose = this;

        dailydose.subscribe = function () {
            $http.get('http://localhost:8080/DailyDose/api/subscribe/' + dailydose.subscriber).
                success(function (data, status, headers, config) {
                    console.log(data);
                }).
                error(function (data, status, headers, config) {
                    console.log("error");
                });
        };

    });
