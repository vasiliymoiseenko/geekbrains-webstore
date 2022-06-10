angular.module('webstore-front').controller('cartController',
    function ($scope, $http, $location, $localStorage) {
      const contextPath = 'http://localhost:8189/webstore/api/v1/cart/';

      $scope.loadCart = function () {
        $http({
          url: contextPath + $localStorage.webstoreGuestCartId,
          method: 'GET',
        }).then(function (response) {
          console.log(response);
          $scope.cart = response.data;
        });
      };

      $scope.incrementItem = function (productId) {
        $http.get(contextPath + $localStorage.webstoreGuestCartId + '/add/'
            + productId)
        .then(function successCallback(response) {
          $scope.loadCart()
        }, function failureCallback(response) {
          alert(response.data.messages);
        });
      };

      $scope.decrementItem = function (productId) {
        $http.get(contextPath + $localStorage.webstoreGuestCartId + '/sub/'
            + productId)
        .then(function successCallback(response) {
          $scope.loadCart()
        }, function failureCallback(response) {
          alert(response.data.messages);
        });
      };

      $scope.removeFromCart = function (productId) {
        $http.get(contextPath + $localStorage.webstoreGuestCartId + '/remove/'
            + productId)
        .then(function successCallback(response) {
          $scope.loadCart()
        }, function failureCallback(response) {
          alert(response.data.messages);
        });
      };

      $scope.checkOut = function () {
        $location.path('/checkout');
      };

      $scope.disabledCheckOut = function () {
        alert('You need to log in')
      };

      $scope.loadCart();

    });