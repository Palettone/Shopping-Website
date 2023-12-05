var checks = document.querySelectorAll("div.cart-body>div.container>div.cart-checkbox > input");
var price = document.querySelectorAll("div.cart-body > div.price");
var result = document.getElementById("totalprice");
var cart =document.querySelector("#cart")
window.onload = function(){
    let sum = 0;
    for (let i = 0; i < checks.length; i++) {
        if(checks[i].checked == true){
            sum += parseInt(price[i].innerText);
        }
    }
    result.innerText = '总价：￥' + sum + '.00元';
}
cart.onclick = function(){
    let sum = 0;
    for (let i = 0; i < checks.length; i++) {
        if(checks[i].checked == true){
            sum += parseInt(price[i].innerText);
        }
    }
    result.innerText = '总价：￥' + sum + '.00元';
}
