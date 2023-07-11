$(document).ready(function(){
listCart();
})

function listCart(){
var userName=$('#lblUserName').text().trim();
$.ajax({
    url:'http://localhost:8084/api/cart/'+userName+'',
    type:'GET',
    contentType:'application/json;charset=utf-8',
    dataType:'json',
    success:function(response){
    var listCart=response.content;
    var html='';
    var total=0;
    for(var i=0;i<listCart.length;i++){

        html+='<div class="d-sm-flex justify-content-between my-4 pb-4 border-bottom">';
        html+='<div class="media d-block d-sm-flex text-center text-sm-left">';
        html+='<a class="cart-item-thumb mx-auto mr-sm-4" href="#"><img src="https://www.bootdey.com/image/240x240/FF0000/000000" alt="Product"></a>';
        html+='<div class="media-body pt-3">';
        html+='<h3 class="product-card-title font-weight-semibold border-0 pb-0"><a href="#">'+listCart[i].product.name+'</a></h3>';
        html+='<div class="font-size-sm"><span class="text-muted mr-2">Brand:</span>'+listCart[i].product.brand.name+'</div>';
        html+='<div class="font-size-sm"><span class="text-muted mr-2">Category:</span>'+listCart[i].product.category.name+'</div>';
        html+='<div class="font-size-lg text-primary pt-2">'+listCart[i].price+'VNĐ</div>';
        html+='</div>';
        html+='</div>';
        html+='<div class="pt-2 pt-sm-0 pl-sm-3 mx-auto mx-sm-0 text-center text-sm-left" style="max-width: 10rem;">';
        html+='<div class="form-group mb-2">';
        html+='<label for="quantity1">Quantity</label>';
        html+='<input class="form-control form-control-sm" min=1 max=20 type="number" id="quantity'+listCart[i].product.id+'" onchange="myFunction('+listCart[i].product.id+','+listCart[i].quantity+')" value="'+listCart[i].quantity+'">';
        html+='</div>';
        html+='<button class="btn btn-outline-secondary btn-sm btn-block mb-2" type="button" onclick="updateCart('+listCart[i].product.id+'); return false;">';
       html+='<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-refresh-cw mr-1">';
                                        html+='<polyline points="23 4 23 10 17 10"></polyline>';
                                        html+='<polyline points="1 20 1 14 7 14"></polyline>';
                                        html+='<path d="M3.51 9a9 9 0 0 1 14.85-3.36L23 10M1 14l4.64 4.36A9 9 0 0 0 20.49 15"></path>';
                                    html+='</svg>Update cart';
                                html+='</button>';
                                html+='<button class="btn btn-outline-danger btn-sm btn-block mb-2" type="button">';
                                    html+='<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trash-2 mr-1">';
                                        html+='<polyline points="3 6 5 6 21 6"></polyline>';
                                        html+='<path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>';
                                        html+='<line x1="10" y1="11" x2="10" y2="17"></line>';
                                        html+='<line x1="14" y1="11" x2="14" y2="17"></line>';
                                    html+='</svg>Remove</button>';
                            html+='</div>';
                        html+='</div>';
                        total+=listCart[i].price;
                        $('#list-cart').html(html);
    }
    $('#total-price').html(total+'VND');
    }
})
}

function myFunction(id,quantity) {
  var x = document.getElementById("quantity"+id);

  if(Number(x.value) < 1 || Number(x.value) > 20){
      x.value=quantity;
    }
    else{
    x.value = x.value;
    }
}

function updateCart(id){
var userName=$('#lblUserName').text().trim();
 var x = document.getElementById("quantity"+id);
  x.value = x.value;
  if(Number(x.value) < 1 || Number(x.value) > 20){
    alert("Nhập lại số lượng");
  }
  else{
    $.ajax({
        url:'http://localhost:8084/api/cart/update-cart/'+id+'/'+Number(x.value)+'/'+userName+'',
        type:'GET',
        contentType:'application/json;charset=utf-8',
        dataType:'json',
        success:function(response){
        listCart();
        }
    })
  }

}

