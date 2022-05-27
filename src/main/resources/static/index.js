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

    });