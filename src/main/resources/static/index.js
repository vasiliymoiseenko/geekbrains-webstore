(function () {
  angular
  .module('webstore-front', ['ngRoute', 'ngStorage'])
  .config(config)
  .run(run);

  function config($routeProvider) {
    $routeProvider
    .when('/', {
      templateUrl: 'welcome/welcome.html',
      controller: 'welcomeController'
    })
    .when('/products', {
      templateUrl: 'products/products.html',
      controller: 'productsController'
    })
    .when('/edit_product/:productId', {
      templateUrl: 'update_product/update_product.html',
      controller: 'updateProductController'
    })
    .when('/add_product', {
      templateUrl: 'add_product/add_product.html',
      controller: 'addProductController'
    })
    .when('/cart', {
      templateUrl: 'cart/cart.html',
      controller: 'cartController'
    })
    .when('/users', {
      templateUrl: 'users/users.html',
      controller: 'usersController'
    })
    .otherwise({
      redirectTo: '/'
    });
  }

  function run($rootScope, $http, $localStorage) {
    if ($localStorage.webMarketUser) {
      $http.defaults.headers.common.Authorization = 'Bearer '
          + $localStorage.webstoreUser.token;
    }
  }
})();

angular.module('webstore-front').controller('indexController',
    function ($rootScope, $scope, $http, $localStorage) {
      const contextPath = 'http://localhost:8189/webstore';

      $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
        .then(function successCallback(response) {
          if (response.data.token) {
            $http.defaults.headers.common.Authorization = 'Bearer '
                + response.data.token;
            $localStorage.webstoreUser = {
              username: $scope.user.username,
              token: response.data.token
            };
            console.log($scope.parseJwt($localStorage.webstoreUser.token));
            $scope.user.username = null;
            $scope.user.password = null;
          }
        }, function errorCallback(response) {
          alert(response.data.messages)
        });
      };

      $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
          $scope.user.username = null;
        }
        if ($scope.user.password) {
          $scope.user.password = null;
        }
      };

      $scope.clearUser = function () {
        delete $localStorage.webstoreUser;
        $http.defaults.headers.common.Authorization = '';
      };

      $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webstoreUser) {
          return true;
        } else {
          return false;
        }
      };

      $rootScope.isManager = function () {
        try {
          token = $scope.parseJwt($localStorage.webstoreUser.token);
          console.log(token);
          for (const role of token.roles) {
            if (role == "ROLE_MANAGER") {
              console.log("true");
              return true;
            }
          }
          return false;
        } catch (err) {
          return false;
        }
      }

      $rootScope.isAdmin = function () {
        try {
          token = $scope.parseJwt($localStorage.webstoreUser.token);
          console.log(token);
          for (const role of token.roles) {
            if (role == "ROLE_ADMIN") {
              console.log("true");
              return true;
            }
          }
          return false;
        } catch (err) {
          return false;
        }
      }

      $scope.parseJwt = function (token) {
        try {
          const base64HeaderUrl = token.split('.')[0];
          const base64Header = base64HeaderUrl.replace('-', '+').replace('_',
              '/');
          const headerData = JSON.parse(window.atob(base64Header));

          const base64Url = token.split('.')[1];
          const base64 = base64Url.replace('-', '+').replace('_', '/');
          const dataJWT = JSON.parse(window.atob(base64));
          dataJWT.header = headerData;
          return dataJWT;
        } catch (err) {
          return false;
        }
      }

    });