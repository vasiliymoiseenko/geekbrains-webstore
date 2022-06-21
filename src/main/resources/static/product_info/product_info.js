angular.module('webstore-front').controller('productInfoController',
    function ($scope, $http, $routeParams) {

      const contextPath = 'http://localhost:8189/webstore/';
      const apiPath = 'api/v1/products/';

      $scope.loadProduct = function () {
        $http({
          url: contextPath + apiPath + $routeParams.productId,
          method: 'GET'
        }).then(function (response) {
          $scope.product = response.data;
          console.log($scope.product);
        });
      };

      $scope.checkComments = function () {
        $http({
          url: contextPath + 'api/v1/order_items/check/'
              + $routeParams.productId,
          method: 'GET'
        }).then(function (response) {
          console.log(response.data);
          $scope.orderItem = response.data;
          $scope.isCommentEnable = $scope.orderItem.id != null;
          console.log($scope.orderItem.id);
        });
      };

      $scope.writeComment = function () {
        $scope.comment.orderItemId = $scope.orderItem.id;
        $http.put(contextPath + 'api/v1/order_items', $scope.comment)
        .then(function (response) {
          console.log(response.data);
          $scope.loadProduct();
          $scope.checkComments();
        });
      };

      $scope.loadProduct();
      $scope.checkComments();

    });