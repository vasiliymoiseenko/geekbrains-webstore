angular.module('webstore-front').controller('ordersController',
    function ($scope, $http) {
      const contextPath = 'http://localhost:8189/webstore/';
      const apiPath = 'api/v1/orders';

      $scope.loadOrders = function () {
        $http({
          url: contextPath + apiPath,
          method: 'GET'
        }).then(function (response) {
          $scope.orders = response.data;
          console.log($scope.orders);
        });
      };

      $scope.loadOrders();
    });