angular.module('dailydoseApp', [])
    .controller('DailydoseController', function ($scope, $http) {
        var dailydose = this;

        dailydose.buttontext = "Sign me up!";
        dailydose.subscribed = false;

        dailydose.API_ROOT = 'https://timostaudinger.com/webapps/DailyDose/api';

        dailydose.subscribe = function () {
            if (!dailydose.subscribed) {
                $http.get(dailydose.API_ROOT + '/subscribe/' + dailydose.subscriber).
                    success(function (data, status, headers, config) {
                        console.log(data);
                    }).
                    error(function (data, status, headers, config) {
                        console.log("error");
                    });
            } else {
                $http.get(dailydose.API_ROOT + '/unsubscribe/' + dailydose.subscriber).
                    success(function (data, status, headers, config) {
                        console.log(data);
                    }).
                    error(function (data, status, headers, config) {
                        console.log("error");
                    });
            }
        };

        dailydose.change = function () {
            if (dailydose.subscriber != undefined && dailydose.subscriber.length > 0) {
                dailydose.buttontext = "loading...";
                $http.get(dailydose.API_ROOT + '/find/' + dailydose.subscriber).
                    success(function (data, status, headers, config) {
                        if (data === "1") {
                            dailydose.buttontext = "Unsubscribe";
                            dailydose.subscribed = true;
                        } else {
                            dailydose.buttontext = "Sign me up!";
                            dailydose.subscribed = false;

                        }
                    }).
                    error(function (data, status, headers, config) {
                        dailydose.buttontext = "Sign me up!";
                        dailydose.subscribed = false;
                    });
                console.log("checking");
            } else {
                dailydose.buttontext = "Sign me up!";
                dailydose.subscribed = false;
            }
        }

    });
