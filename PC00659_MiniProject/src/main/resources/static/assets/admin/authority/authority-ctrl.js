/**
 * 
 */
app.controller('authority-ctrl', function ($scope, $http,$location) {
	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities = [];

	$scope.initialize = function () {
		$http.get('/rest/roles').then(resp => {
			$scope.roles = resp.data;
		})

		$http.get('/rest/accounts/getall').then(resp => {
			$scope.admins = resp.data;
			console.log($scope.admins);
		})
		$http.get('/rest/authorities').then(resp => {
			$scope.authorities = resp.data;
			console.log($scope.authorities);
		}).catch(err => {
			console.log(err);
			$location.path("/unauthorized");
		})
	}


	$scope.authority_of= function (acc, role) {
		
		if ($scope.authorities) {
			return $scope.authorities.find(ur => ur.account.idaccounts == acc.idaccounts && ur.role.idrole == role.idrole);
		}
	}

	$scope.authority_changed= function (acc, role) {
		var authority = $scope.authority_of(acc, role);
		if (authority) {
			$scope.revoke_authority(authority);
		} else {
			authority = { account: acc, role: role };
			$scope.grant_authority(authority);
		}
	}

	$scope.grant_authority = function (authority) {
		$http.post(`/rest/authorities`, authority).then(resp => {
			$scope.authorities.push(resp.data);
			alert('Cấp quyền thành công');

		}).catch(error => {
			alert('Cấp quyền thất bại')
			console.log('Error', error);
		})
	}

	$scope.revoke_authority = function (authority) {
		$http.delete(`/rest/authorities/${authority.idauthorities}`).then(resp => {
			var index = $scope.authorities.findIndex(a => a.idauthorities == authority.idauthorities);
			$scope.authorities.slice(index, 1);
			alert('Thu hồi quyền thành công');
			
		}).catch(error => {
			alert('Thu hồi quyền thất bại');
			console.log('Error', error);
		})
	}
	$scope.initialize();
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.admins.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.admins.length / this.size);
		},
		first() {
			this.page = 0;

		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		},
	}
	
	
})