function redirect(){
    window.location.href = "MainPageManager.html";
}

function timeF(){

    //Discount time
    let horapromo = "01/01/2011 " + document.getElementById("horapromo").value + ":00";
    let horapromo_print = document.getElementById("horapromo").value + ":00";

    //Current time
    var today = new Date();
    var current_time = "01/01/2011 " + today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();

    //compare discount time with current time
    let comparison = Date.parse(horapromo) > Date.parse(current_time);

    if(comparison){
        localStorage.setItem("hora_promo",horapromo_print);
        document.getElementById("msg").innerHTML = "Discount will be applied until " + horapromo_print;
    }
    
    else{
        document.getElementById("msg").innerHTML = "Discount is not valid anymore";
    }
    
    let time_promo = localStorage.getItem("hora_promo");
    console.log("Esta es la variable de local storage    " + time_promo);

}