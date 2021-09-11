/**
 * 
 */
app.controller("user-ctrl", function($scope, $http) {
	$scope.users = [];
	$scope.form = {};
	$scope.roles = [];
	$scope.orders = [];
	$scope.initialize = function() {
		$http.get('/rest/accounts/getall').then(response => {
			$scope.users = response.data;
			$scope.users.forEach(item => {
				item.birthday = new Date(item.birthday)
			})
		});
	}
	$http.get('/rest/roles').then(resp => {
		$scope.roles = resp.data;

	})

	$scope.initialize();

	$scope.reset = function() {
		$scope.form = {
			image: 'cloud-upload.jpg',
		};
	}

	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-tabs button:eq(0)").tab('show');
	}

	$scope.create = function() {
		var item = angular.copy($scope.form);

		$http.post('/rest/accounts', item).then(response => {
			$scope.users.push(response.data);

			var role = $scope.roles.find(r => r.idrole === 'USER');

			var authority = { account: item, role: role };
			$http.post(`/rest/authorities`, authority).then(response => {
				console.log('Autho : ' + response.data);
			}).catch(error => {
				console.log('Error', error);
			});

			$scope.reset();

			alert('Thêm mới thành công');

		}).catch(error => {
			alert('Lỗi thêm mới tài khoản');
			console.log("Erorr", error);
		})
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/accounts/${item.idaccounts}`, item).then(response => {
			var index = $scope.users.findIndex(a => a.idaccounts == item.idaccounts);
			$scope.users[index] = item;
			alert('Cập nhật thành công');
		}).catch(function(err) {
			alert('Lỗi cập nhật tài khoản');
			console.log("Erorr", err);
		})
	}

	$scope.delete = function(item) {

		$http.get(`/rest/orders/${item.idaccounts}`).then(resp => {
		
			if (resp.data.length == 0) {
				$http.delete(`/rest/authorities/pri/${item.idaccounts}`).then(resp => {
					console.log("Message", "xóa thành công");
				})

				$http.delete(`/rest/accounts/${item.idaccounts}`).then(response => {
					var index = $scope.users.findIndex(a => a.idaccounts == item.idaccounts);
					$scope.users.splice(index, 1);
					$scope.reset();
					alert('Xóa thành công');

				}).catch(function(err) {
					alert('Lỗi xóa tài khoản');
					console.log("Erorr", err);
				})
			}
			else {
				alert('Tài khoản đã mua hàng không được xóa');
			};
		})

	}
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.users.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.users.length / this.size);
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