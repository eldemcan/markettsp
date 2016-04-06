
angular.module('mpfront', ['restangular','googlechart']).config(function (RestangularProvider) {
    RestangularProvider.setBaseUrl('http://localhost:8080/consumer/');
});

