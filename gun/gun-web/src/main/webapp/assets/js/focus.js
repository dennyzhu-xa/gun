function Ctrl($scope) {
  $scope.model = "ahaha"
  $scope.someFocusVariable = true; // If you want to focus initially, set this to true. Else you don't need to define this at all.
}

angular.module('experiement', [])
  .directive('focus', function($timeout, $parse) {
    return {
      restrict: 'A',
      link: function(scope, element, attrs) {
          scope.$watch(attrs.focus, function(newValue, oldValue) {
              if (newValue) { element[0].focus(); }
          });
          element.bind("blur", function(e) {
              $timeout(function() {
                  scope.$apply(attrs.focus + "=false"); 
              }, 0);
          });
          element.bind("focus", function(e) {
              $timeout(function() {
                  scope.$apply(attrs.focus + "=true");
              }, 0);
          })
      }
    }
  });