angular.module('webstore-front').controller('checkoutController',
    function ($scope, $http, $location, $localStorage) {
      const contextPath = 'http://localhost:8189/webstore/api/v1/cart/';

      $scope.loadCart = function () {
        $http({
          url: contextPath + $localStorage.webstoreGuestCartId,
          method: 'GET',
        }).then(function (response) {
          $scope.cart = response.data;
        });
      };

      $scope.createOrder = function () {
        $http.post('http://localhost:8189/webstore/api/v1/orders/',
            $scope.orderDetails)
        .then(function successCallback(response) {
          alert('Ordered successfully');
          $location.path('/products');
        }, function failureCallback(response) {
          alert(response.data.messages);
        });
      }

      $scope.loadCart();

    });