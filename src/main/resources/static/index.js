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
    .when('/webstore', {
      templateUrl: 'products/products.html',
      controller: 'productsController'
    })
    .when('/edit_product/:productId', {
      templateUrl: 'edit_product/edit_product.html',
      controller: 'editProductController'
    })/*
    .when('/create_product', {
        templateUrl: 'create_product/create_product.html',
        controller: 'createProductController'
    })*/
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