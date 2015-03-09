/**
 * Created by ronny.ness on 06/03/15.
 */


var app = angular.module('stock-analyzer',['ngResource']);

/*app.factory("Character", function($resource) {
    return $resource("http://localhost:8080/generate/character");
});*/

app.factory('Character', function($resource) {
    return $resource('/character/:amount'); // Note the full endpoint address
});

app.factory('GenerateForm', function() {
    return { amount: 1};
});

app.controller("FormCtrl", function($scope, GenerateForm) {

//    this.inputvalue = 0;
//    console.log("this.inputvalue is ", this.inputvalue);
//    console.log("$scope.amount is ", $scope.amount);
    $scope.amount = '';
    var args = 'tull';

    $scope.$emit('submitForm', args);
    
    $scope.submitForm = function() {
        GenerateForm.amount = $scope.amount;
        console.log("FormCtrl submit!");
        console.log("GenerateForm.amount is ", GenerateForm.amount);
        $scope.amount = '';
    };
    
});

app.controller("ChargenCtrl", function($scope, Character, GenerateForm) {

//    $scope.amount = 10;

    $scope.$on('submitForm', function (event, args) {
    
        console.log("ChargenCtrl fanget opp et kall og args er " + args);
    });
    
        Character.query({ amount: GenerateForm.amount }, function(data) {
            $scope.characters = data;
//        $scope.characters = JSON.stringify(data, null, 3);
        });

    

    

});
    

