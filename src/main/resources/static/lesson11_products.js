angular.module('webstore-front', []).controller('productsController',
    function ($scope, $http) {
      const contextPath = 'http://localhost:8189/webstore/api/v1/products/';
      let currentPageIndex = 1;

      $scope.loadProducts = function (pageIndex) {
        currentPageIndex = pageIndex;
        $http({
          url: contextPath,
          method: 'GET',
          params: {
            p: pageIndex
          }
        }).then(function (response) {
          console.log(response);
          $scope.productsPage = response.data;
          $scope.paginationArray = $scope.generatePagesIndexes(1,
              $scope.productsPage.totalPages);
        });
      };

      $scope.deleteProduct = function (product) {
        $http.delete(contextPath + product.id)
        .then(function (response) {
          $scope.loadProducts(currentPageIndex);
        });
      };

      $scope.saveOrUpdateProduct = function () {
        if ($scope.new_product.id == null) {
          $http.post(contextPath, $scope.new_product)
          .then(function successCallback(response) {
            $scope.loadProducts(currentPageIndex);
            $scope.new_product = null;
          }, function failureCallback(response) {
            alert(response.data.message);
          });
        } else {
          $http.put(contextPath, $scope.new_product)
          .then(function successCallback(response) {
            $scope.loadProducts(currentPageIndex);
            $scope.new_product = null;
          }, function failureCallback(response) {
            alert(response.data.message);
          });
        }
      }

      $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
          arr.push(i);
        }
        return arr;
      }

      $scope.loadProducts(1);
    });