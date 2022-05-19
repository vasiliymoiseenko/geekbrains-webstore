(function () {
  angular
  .module('webstore-front', ['ngRoute'])
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
    .otherwise({
      redirectTo: '/'
    });
  }

  function run($rootScope, $http) {
  }
})();

angular.module('webstore-front').controller('indexController',
    function ($rootScope, $scope, $http) {
      const contextPath = 'http://localhost:8189/webstore';

    });