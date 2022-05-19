angular.module('webstore-front').controller('editProductController',
    function ($scope, $http, $location, $routeParams) {
      const contextPath = 'http://localhost:8189/webstore/api/v1/products/';

      $scope.getProductById = function () {
        $http.get(contextPath + $routeParams.productId)
        .then(function successCallback(response) {
          $scope.updated_product = response.data;
        }), function failureCallback(response) {
          console.log(response);
          alert(response.data.message)
          $locale.path('/webstore');
        }
      }

      $scope.updateProduct = function () {
        $http.put(contextPath, $scope.updated_product)
        .then(function successCallback(response) {
          $scope.updated_product = null;
          alert('Продукт успешно обновлен');
          $location.path('/webstore');
        }, function failureCallback(response) {
          alert(response.data.messages);
        });
      }

      $scope.getProductById();
    });