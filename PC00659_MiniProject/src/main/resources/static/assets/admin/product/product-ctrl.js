/**
 * 
 */
app.controller("product-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	$scope.cates = [];
	$scope.initialize = function() {
		$http.get('/rest/products').then(response => {
			$scope.items = response.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate);
			})
		});
	}

	$http.get('/rest/categories').then(response => {
		$scope.cates = response.data;
	});
	$scope.initialize();

	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			image: 'cloud-upload.jpg',
			available: true,
		};
		
	}
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-tabs button:eq(0)").tab('show');
	}
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post('/rest/products', item).then(response => {
			response.data.createDate = new Date(response.data.createDate);
			$scope.items.push(response.data);
			$scope.reset();
			alert('Thêm mới thành công');

		}).catch(error => {
			alert('Lỗi thêm mới sản phẩm');
			console.log("Erorr", error);
		})
	}
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(response => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert('Cập nhật thành công');
		}).catch(function(err) {
			alert('Lỗi cập nhật sản phẩm');
			console.log("Erorr", err);
		})
	}
	$scope.delete = function(item) {
		$http.delete(`/rest/products/${item.id}`).then(response => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert('Xóa thành công');

		}).catch(function(err) {
			alert('Sản phẩm đã có trong đơn hàng không thể xóa!');
			console.log("Erorr", err);
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
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
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