function redirect(){
    window.location.href = "MainPageUser.html";
}


function searchById(){
    let product_id = document.querySelector('#id_ticket').value;
    let xhr = new XMLHttpRequest(); 
    let url = "http://localhost:8085/api/v1/Ticket/" + product_id; 

    xhr.open("GET", url, true); 
    xhr.setRequestHeader("Content-Type", "application/json"); 
    xhr.onreadystatechange = function () { 
       
        if (xhr.readyState === 4 && xhr.status === 200) { 

            let resultado = JSON.parse(this.responseText);
            main = "<tr><th scope='row' style='text-align:center'>" + resultado.id_ticket + "</th><td>" +  resultado.id_compras + "</td>"
                + "<td>" +  resultado.subtotal + "</td>" + "<td>" +  resultado.promocion1 + "</td>"
                + "<td>" +  resultado.promocion2 + "</td>" + "<td>" +  resultado.total + "</td></tr>";

            let table_top = "<table class='table' style='background-color:#d4d4d4'>";
            let table_bottom = "</table>";
            let headers = "<thead class='thead-dark'><tr><th scope='col' style='text-align:center'>Ticket Id</th><th scope='col' style='text-align:center'>Order Id</th><th scope='col' style='text-align:center'>Subtotal</th><th scope='col' style='text-align:center'>Discount 1</th><th scope='col' style='text-align:center'>Discount 2</th><th scope='col' style='text-align:center'>Total</th></tr></thead>";
            document.getElementById("table1").innerHTML = table_top + headers + "<tbody>" + main + "</tbody>" + table_bottom;
        }

        else{
            document.getElementById("table1").innerHTML = "<h3 style='color:red'>NOT FOUND</h3>";
        }
    }; 
    
    xhr.send();
}