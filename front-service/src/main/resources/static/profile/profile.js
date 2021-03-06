angular.module('webstore-front').controller('profileController',
    function ($scope, $http, $rootScope, $location) {
      const contextPath = 'http://localhost:5555/core/';
      const apiPath = 'api/v1/orders';

      if (!$rootScope.isUserLoggedIn()) {
        $location.path('/');
      }

      $scope.loadOrders = function () {
        $http({
          url: contextPath + apiPath,
          method: 'GET'
        }).then(function (response) {
          $scope.orders = response.data;
          console.log($scope.orders);
        });
      };

      $scope.loadMyProfile = function () {
        $http({
          url: 'http://localhost:5555/auth/api/v1/profiles/me',
          method: 'GET'
        }).then(function (response) {
          console.log(response.data)
          $scope.userProfile = response.data;
        });
      };

      $scope.loadOrders();
      $scope.loadMyProfile();
    });