angular.module('webstore-front').controller('productsController',
    function ($scope, $http, $location, $rootScope, $localStorage) {
      const contextPath = 'http://localhost:8189/webstore/api/v1/products/';
      let currentPageIndex = 1;

      $scope.loadProducts = function (pageIndex) {
        currentPageIndex = pageIndex;
        $http({
          url: contextPath,
          method: 'GET',
          params: {
            p: pageIndex,
            title: $scope.filter ? $scope.filter.title : null,
            min_price: $scope.filter ? $scope.filter.min_price : null,
            max_price: $scope.filter ? $scope.filter.max_price : null
          }
        }).then(function (response) {
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

      $scope.goToEditPage = function (productId) {
        $location.path('/edit_product/' + productId);
      }

      $scope.addToCart = function (productId) {
        $http.get('http://localhost:8189/webstore/api/v1/cart/'
            + $localStorage.webstoreGuestCartId + '/add/' + productId)
        .then(function successCallback(response) {
          alert('Product added to cart successfully');
        }, function failureCallback(response) {
          alert(response.data.messages);
        });

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