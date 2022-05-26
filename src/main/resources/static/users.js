angular.module('webstore-front', []).controller('usersController',
    function ($scope, $http) {
      const contextPath = 'http://localhost:8189/webstore/api/v1/users/';
      let currentPageIndex = 1;

      $scope.loadUsers = function (pageIndex) {
        currentPageIndex = pageIndex;
        $http({
          url: contextPath,
          method: 'GET',
          params: {
            p: pageIndex
          }
        }).then(function (response) {
          console.log(response);
          $scope.usersPage = response.data;
          $scope.paginationArray = $scope.generatePagesIndexes(1,
              $scope.usersPage.totalPages);
        });
      };

      $scope.deleteUser = function (user) {
        $http.delete(contextPath + user.id)
        .then(function (response) {
          $scope.loadUsers(currentPageIndex);
        });
      };

      $scope.saveOrUpdateUser = function () {
        if ($scope.new_user.id == null) {
          $http.post(contextPath, $scope.new_user)
          .then(function successCallback(response) {
            $scope.loadUsers(currentPageIndex);
            $scope.new_user = null;
          }, function failureCallback(response) {
            alert(response.data.messages);
          });
        } else {
          $http.put(contextPath, $scope.new_user)
          .then(function successCallback(response) {
            $scope.loadUsers(currentPageIndex);
            $scope.new_user = null;
          }, function failureCallback(response) {
            alert(response.data.messages);
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

      $scope.loadUsers(1);
    });