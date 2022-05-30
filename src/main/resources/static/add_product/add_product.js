angular.module('webstore-front').controller('addProductController',
    function ($scope, $http, $location, $rootScope) {
      const contextPath = 'http://localhost:8189/webstore/api/v1/products/';

      $scope.addProduct = function () {
        if ($scope.new_product == null) {
          alert('Form is empty');
          return;
        }
        $http.post(contextPath, $scope.new_product)
        .then(function successCallback(response) {
          $scope.new_product = null;
          alert('Product added successfully');
          $location.path('/products');
        }, function failureCallback(response) {
          alert(response.data.messages);
        });
      }
    });