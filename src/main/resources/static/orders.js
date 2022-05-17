angular.module('webstore-front', []).controller('ordersController',
    function ($scope, $http) {
      const contextPath = 'http://localhost:8189/webstore/api/v1/orders/';
      let currentPageIndex = 1;

      $scope.loadOrders = function (pageIndex) {
        currentPageIndex = pageIndex;
        $http({
          url: contextPath,
          method: 'GET',
          params: {
            p: pageIndex
          }
        }).then(function (response) {
          console.log(response);
          $scope.ordersPage = response.data;
          $scope.paginationArray = $scope.generatePagesIndexes(1,
              $scope.ordersPage.totalPages);
        });
      };

      $scope.deleteProduct = function (order) {
        $http.delete(contextPath + order.id)
        .then(function (response) {
          $scope.loadOrders(currentPageIndex);
        });
      };

      $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
          arr.push(i);
        }
        return arr;
      }

      $scope.loadOrders(1);
    });