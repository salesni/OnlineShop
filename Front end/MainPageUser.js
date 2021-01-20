function redirectO(){
    window.location.href = "OrderUsers.html";
}
function redirectP(){
    window.location.href = "ProductosUser.html";
}
function redirectT(){
    window.location.href = "TicketUser.html";
}
function redirectProfile(){
    window.location.href = "ProfileUser.html";
}
function redirectS(){
    localStorage.removeItem('id_user');
    localStorage.removeItem("id_compra");
    localStorage.removeItem("mail_user");
    localStorage.removeItem("monedero");
    window.location.href = "Login.html";

}

document.getElementById("promocion").innerHTML= "BUY BEFORE " + localStorage.getItem("hora_promo")+ " AND GET 10% OFF";
