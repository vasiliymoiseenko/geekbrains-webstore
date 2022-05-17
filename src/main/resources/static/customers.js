angular.module('webstore-front', []).controller('customersController',
    function ($scope, $http) {
      const contextPath = 'http://localhost:8189/webstore/api/v1/customers/';
      let currentPageIndex = 1;

      $scope.loadCustomers = function (pageIndex) {
        currentPageIndex = pageIndex;
        $http({
          url: contextPath,
          method: 'GET',
          params: {
            p: pageIndex
          }
        }).then(function (response) {
          console.log(response);
          $scope.customersPage = response.data;
          $scope.paginationArray = $scope.generatePagesIndexes(1,
              $scope.customersage.totalPages);
        });
      };

      $scope.deleteCustomer = function (customer) {
        $http.delete(contextPath + customer.id)
        .then(function (response) {
          $scope.loadCustomers(currentPageIndex);
        });
      };

      $scope.saveOrUpdateCustomer = function () {
        if ($scope.new_customer.id == null) {
          $http.post(contextPath, $scope.new_customer)
          .then(function successCallback(response) {
            $scope.loadCustomers(currentPageIndex);
            $scope.new_customer = null;
          }, function failureCallback(response) {
            alert(response.data.message);
          });
        } else {
          $http.put(contextPath, $scope.new_customer)
          .then(function successCallback(response) {
            $scope.loadCustomers(currentPageIndex);
            $scope.new_customer = null;
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

      $scope.loadCustomers(1);
    });