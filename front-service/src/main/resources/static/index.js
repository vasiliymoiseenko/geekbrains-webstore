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
    .when('/product_info/:productId', {
      templateUrl: 'product_info/product_info.html',
      controller: 'productInfoController'
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
    .when('/checkout', {
      templateUrl: 'checkout/checkout.html',
      controller: 'checkoutController'
    })
    .when('/profile', {
      templateUrl: 'profile/profile.html',
      controller: 'profileController'
    })
    .otherwise({
      redirectTo: '/'
    });
  }

  function run($rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/core';
    if ($localStorage.webstoreUser) {
      $http.defaults.headers.common.Authorization = 'Bearer '
          + $localStorage.webstoreUser.token;
    }
    if (!$localStorage.webstoreGuestCartId) {
      $http.get('http://localhost:5555/cart/api/v1/cart/generate')
      .then(function successCallback(response) {
        $localStorage.webstoreGuestCartId = response.data.value;
      });
    }
  }
})();

angular.module('webstore-front').controller('indexController',
    function ($rootScope, $scope, $http, $localStorage, $location) {
      $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/api/v1/auth', $scope.user)
        .then(function successCallback(response) {
          if (response.data.token) {
            $http.defaults.headers.common.Authorization = 'Bearer '
                + response.data.token;
            $localStorage.webstoreUser = {
              username: $scope.user.username,
              token: response.data.token
            };
            $scope.user.username = null;
            $scope.user.password = null;

            $http.get('http://localhost:5555/cart/api/v1/cart/'
                + $localStorage.webstoreGuestCartId + '/merge')
            .then(function successCallback(response) {
            });
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
        $location.path('/');
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
          for (const role of token.roles) {
            if (role == "ROLE_MANAGER") {
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
          for (const role of token.roles) {
            if (role == "ROLE_ADMIN") {
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