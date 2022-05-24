angular.module('webstore-front').controller('cartController',
    function ($scope, $http) {
      const contextPath = 'http://localhost:8189/webstore/api/v1/cart/';

      $scope.loadCart = function () {
        $http({
          url: contextPath,
          method: 'GET',
        }).then(function (response) {
          console.log(response);
          $scope.productList = response.data.productDtoList;
          $scope.totalPrice = response.data.totalPrice;
        });
      };

      $scope.removeFromCart = function (productId) {
        $http.get(contextPath + 'remove/' + productId)
        .then(function successCallback(response) {
          $scope.loadCart()
        }, function failureCallback(response) {
          alert(response.data.messages);
        });
      };

      $scope.loadCart();
    });