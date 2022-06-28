angular.module('webstore-front').controller('checkoutController',
    function ($scope, $http, $location, $localStorage) {
      const contextPath = 'http://localhost:5555/cart/api/v1/cart/';

      $scope.loadCart = function () {
        $http({
          url: contextPath + $localStorage.webstoreGuestCartId,
          method: 'GET',
        }).then(function (response) {
          $scope.cart = response.data;
        });
      };

      $scope.createOrder = function () {
        $http.post('http://localhost:5555/core/api/v1/orders/',
            $scope.orderDetails)
        .then(function successCallback(response) {
          alert('Ordered successfully');
          var orderId = response.data.id;
          $location.path('/order_pay/' + orderId);
        }, function failureCallback(response) {
          alert(response.data.messages);
        });
      }

      $scope.loadCart();

    });