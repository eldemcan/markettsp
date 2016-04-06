var myApp = angular.module('mpfront');

myApp.controller("MainCtrl", function ($scope, $timeout, Restangular) {

    var recentTradeMessage = Restangular.one('poolrecent');

    var allTradeMessages = Restangular.all('poolall');

    $scope.allData = function () {
        allTradeMessages.getList().then(function (tradeMessagesList) {
            $scope.tradeMesssageList = tradeMessagesList;
            $timeout($scope.allData, 4000);
        });
    }

    $scope.updateData = function () {
        recentTradeMessage.get().then(function (recentTradeMessage) {
            $scope.recentMessage = recentTradeMessage;
            console.log('Something');
            if (recentTradeMessage != null) {
                var chartData = {};
                chartData.type = "GeoChart";
                chartData.data = [
                    ['Locale', 'amountSell', 'amountBuy']
                ];
                var arr = [];
                arr.push(recentTradeMessage.originatingCountry);
                arr.push(recentTradeMessage.amountSell);
                arr.push(recentTradeMessage.amountBuy);
                chartData.data.push(arr);

                chartData.options = {
                    width: 600,
                    height: 300,
                    chartArea: {left: 10, top: 10, bottom: 0, height: "100%"},
                    colorAxis: {colors: ['#aec7e8', '#1f77b4']},
                    displayMode: 'regions'
                };

                chartData.formatters = {
                    number: [{
                        columnNum: 1,
                        pattern: recentTradeMessage.currencyFrom + " #,##0.00"
                    }, {
                        columnNum: 2,
                        pattern: recentTradeMessage.currencyTo + " #,##0.00"
                    }]
                };

                $scope.chart = chartData;
            }
            $timeout($scope.updateData, 4000);
        });
    }

    $scope.updateData();
    $scope.allData();
});
