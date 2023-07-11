$(document).ready(function(){
loadProduct();
})



function loadProduct(){

$.ajax({
    url:"http://localhost:8084/api/product",
    type:"GET",
    contentType:"application/json;charset=utf-8",
    dataType:"json",
    success:function(response){
    console.log(response.content);
    var product=response.content;
    var html='';
    for(var i=0;i<product.length;i++){
        html+='<div class="col-md-4 mt-2">';
        html+='<div class="card">';
        html+='<div class="card-body">'
        html+='<div class="card-img-actions">';
        html+='<img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1562074043/234.png" class="card-img img-fluid" width="96" height="350" alt="">';
        html+='</div>';
        html+='</div>';
        html+='<div class="card-body bg-light text-center">';
        html+='<div class="mb-2">';
        html+='<h6 class="font-weight-semibold mb-2">';
        html+='<a href="#" class="text-default mb-2" data-abc="true">'+product[i].name+'</a>';
        html+='</h6>';
        html+='<a href="#" class="text-muted" data-abc="true">'+product[i].category.name+'</a>';
        html+='</div>';
        html+='<h3 class="mb-0 font-weight-semibold">'+product[i].price+'đ'+'</h3>';
        html+='<div>';
        html+='<i class="fa fa-star star"></i>';
        html+='<i class="fa fa-star star"></i>';
        html+='<i class="fa fa-star star"></i>';
        html+='<i class="fa fa-star star"></i>';
        html+='</div>';
        html+='<div class="text-muted mb-3">34 reviews</div>';
        html+='<button type="button" class="btn bg-cart"  onclick="addToCart('+product[i].id+','+1+'); return false;"><i class="fa fa-cart-plus mr-2"></i> Add to cart</button>';
        html+='</div>';
        html+='</div>';
        html+='</div>';

        $('#rowProduct').html(html);
    }
    }
})
}

function addToCart(id,quantity){
var userName=$('#lblUserName').text().trim();
//if(userName == ""){
//    alert('You must log in!');
//}
//else{
//$.ajax({
//    url:'http://localhost:8084/client/cart/add-to-cart/'+id+'/'+quantity+'/'+userName+'',
//    type:'GET',
//    contentType: "application/json;charset=utf-8",
//    dataType: "json",
//    success: function (response){
//    var cart=response.content;
//    if(cart.length != 0){
//    alert('ADDED'+cart.product.name+cart.quantity+'TO CART');
//    }else{
//    alert(response.message);
//    }
//
//    }
//})
//}
$.ajax({
    url:'http://localhost:8084/api/cart/add-to-cart/'+id+'/'+quantity+'/'+userName+'',
    type:'GET',
    contentType: "application/json;charset=utf-8",
    dataType: "json",
    success: function (response){
    var cart=response.content;
    if(cart.length != 0){
    alert('ADDED '+cart.product.name+' '+cart.quantity+' TO CART');
    }else{
    alert(response.message);
    }

    },
    error:function(){
    alert("Error");
    location.replace("http://localhost:8084/admin/login")
    }
})

}