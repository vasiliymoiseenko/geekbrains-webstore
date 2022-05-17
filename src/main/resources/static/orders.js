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

      $scope.deleteOrder = function (order) {
        $http.delete(contextPath + order.id)
        .then(function (response) {
          $scope.loadOrders(currentPageIndex);
        });
      };

      $scope.saveOrUpdateOrder = function () {
        if ($scope.new_order.id == null) {
          $http.post(contextPath, $scope.new_order)
          .then(function successCallback(response) {
            $scope.loadOrders(currentPageIndex);
            $scope.new_order = null;
          }, function failureCallback(response) {
            alert(response.data.message);
          });
        } else {
          $http.put(contextPath, $scope.new_order)
          .then(function successCallback(response) {
            $scope.loadOrders(currentPageIndex);
            $scope.new_order = null;
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

      $scope.loadOrders(1);
    });