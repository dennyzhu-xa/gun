function MyCtrl($scope) {
$scope.action = function(showit) 
{	
		if(showit=='Q')
		{
		$scope.loadgride = true;
		$scope.loadgrida = false;
		}
		else if(showit=='R')
		{
		$scope.loadgride = false;
		}	
		else if(showit=='A')
		{
		$scope.loadgrida = true;
		$scope.loadgride = false;	
		}	
		else if(showit=='AS')
		{
		alert('储存成功');
		$scope.loadgride = true;
		$scope.loadgrida = false;
		$scope.loadgridme = false;
		}
		else if(showit=='ME')
		{
		$scope.loadgridme = true;
		$scope.loadgride = false;
		$scope.loadgrida = false;
		}
		else
		{
		$scope.loadgride = false;	
		$scope.loadgrida = false;
		$scope.loadgridme = false;
		}		
}
}