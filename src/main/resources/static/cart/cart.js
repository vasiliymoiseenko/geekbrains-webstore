angular.module('webstore-front').controller('cartController',
    function ($scope, $http) {
      const contextPath = 'http://localhost:8189/webstore/api/v1/cart/';

      $scope.loadCart = function () {
        $http({
          url: contextPath,
          method: 'GET',
        }).then(function (response) {
          console.log(response);
          $scope.cartPage = response.data.productDtoList;
        });
      };

      $scope.removeFromCart = function (productId) {
        $http.get(contextPath + 'remove/' + productId)
        .then(function (response) {
          $scope.loadCart();
        });
      };

      $scope.loadCart();
    });