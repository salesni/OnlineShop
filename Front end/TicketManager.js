// function createTicket(){
//
//
//     let id_compras = document.querySelector('#id_compras').value;
//     let subtotal = document.querySelector('#subtotal').value;
//     let promocion1 = document.querySelector('#promocion1').value;
//     let promocion2 = document.querySelector('#promocion2').value;
//     let total = document.querySelector('#total').value;
//
//
//
//     let xhr = new XMLHttpRequest();
//     let url = "http://localhost:8085/api/v1/Ticket";
//
//     xhr.open("POST", url, true);
//
//     xhr.setRequestHeader("Content-Type", "application/json");
//
//     var data = JSON.stringify({  "id_compras": { "id_compras": id_compras},
//   "subtotal":subtotal, "promocion1":promocion1, "promocion2":promocion2,
// "total":total});
//     alert("New Ticket saved");
//     console.log(data);
//     xhr.send(data);
// }

function redirect(){
    window.location.href = "MainPageManager.html";
}



function searchTicketById(){

    let ticket_id = document.querySelector('#ticket_id2search').value;
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8085/api/v1/Ticket/" + ticket_id;

    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {

        if (xhr.readyState === 4 && xhr.status === 200) {

            let resultado = JSON.parse(this.responseText);
            main = "<tr><th scope='row' style='text-align:center'>" + resultado.id_ticket + "</th><td>" +  resultado.id_compras + "</td>"
            + "<td>" +  resultado.subtotal + "</td><td>" +  resultado.promocion1 + "</td>"+"</td><td>"
            +  resultado.promocion2 + "</td>"+"</td><td>" +  resultado.total + "</td>"+"</tr>";

            let table_top = "<table class='table' style='background-color:#d4d4d4'>";
            let table_bottom = "</table>";
            let headers = "<thead class='thead-dark'><tr><th scope='col' style='text-align:center'>ID Ticket id</th><th scope='col' style='text-align:center'>ID ORDER</th><th scope='col' style='text-align:center'>Subtotal</th>"+
            "<th scope='col' style='text-align:center'>Promo1</th><th scope='col' style='text-align:center'>Promo2</th><th scope='col' style='text-align:center'>Total</th>"+"</tr></thead>";

            document.getElementById("table1").innerHTML = table_top + headers + "<tbody>" + main + "</tbody>" + table_bottom;
        }

        else{
            document.getElementById("table1").innerHTML = "<h3 style='color:red'>NOT FOUND</h3>";
        }
    };

    xhr.send();

}
