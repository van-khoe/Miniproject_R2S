/**
 * 
 */
const app = angular.module("cart-app",[]);
app.controller("cart-ctrl",function($scope,$http){

$scope.cart={
	items:[],
	
	add(idproducts){
		var item = this.items.find(item => item.idproducts == idproducts);
		if(item){
			item.qty++;
			this.saveToLocalStorage();
		}
		else{
			$http.get(`/rest/products/${idproducts}`).then(resp =>{
					resp.data.qty =1;
				this.items.push(resp.data);
				this.saveToLocalStorage();
			})
		}
	},
	remove(id){
		var index = this.items.findIndex(item => item.id == id);
		this.items.splice(index,1);
		this.saveToLocalStorage();
	},
	clear(){
		this.items =[];
		this.saveToLocalStorage();
	},
	atm_of(item){
		
	},
	get count(){
		return this.items
		.map(item => item.qty)
		.reduce((total,qty) => total += qty,0);
	},
	get amount(){
		return this.items
		.map(item => item.qty * item.price)
		.reduce((total,qty) => total += qty,0);
	},
	saveToLocalStorage(){
		
		var json = JSON.stringify(angular.copy(this.items));
		localStorage.setItem("cart", json);
	},
	loadFromLocalStorage(){
		var json = localStorage.getItem("cart");
		this.items = json ? JSON.parse(json) :  [];
	}
}
$scope.cart.loadFromLocalStorage();

$scope.order ={
	createdate: new Date(),
	address: "",
	account: {idaccounts: $("#account_idaccount").text()},
	get orderDetails(){
		return $scope.cart.items.map(item =>{
			return {
				product: {idproducts: item.idproducts},
				price: item.price,
				quantity: item.qty
			}
		});
	},
	purchase(){
		var order = angular.copy(this);
		$http.post("/rest/orders", order).then(resp =>{
			alert("Đặt hàng thành công!");
			$scope.cart.clear();
			location.href = "/order/detail/"+ resp.data.idorders;
		}).catch(error =>{
			alert("Đặt hàng lỗi!");
			console.log(error)
		})
	}
}
})