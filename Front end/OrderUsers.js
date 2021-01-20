function redirect(){
    window.location.href = "MainPageUser.html";
}

function searchById(){

    let cat_id = document.querySelector('#id_order').value;
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8084/api/v1/DetallesUsuario/" + cat_id;
    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            let list = JSON.parse(this.responseText);
            let main = "";

            for(let i=0; i < list.length ; i ++){
                main += "<tr><th scope='row' style='text-align:center'>" + list[i].id_compra + "</th><td>" +  list[i].producto + "</td>"
                + "<td>" +  list[i].cantidad + "</td>" + "<td>" +  list[i].precioIndividual + "</td>" + "<td>" +  list[i].subTotal + "</td></tr>";
            }

            let table_top = "<table class='table' style='background-color:#d4d4d4'>";
            let table_bottom = "</table>";
            let headers = "<thead class='thead-dark'><tr'><th scope='col' style='text-align:center'>Purchase Id</th>"+
            "<th scope='col' style='text-align:center'>Name</th><th scope='col' style='text-align:center'>Quantity</th><th scope='col' style='text-align:center'>Price per Unit</th><th scope='col' style='text-align:center'>Subtotal</th></tr></thead>";
            document.getElementById("table2").innerHTML = table_top + headers + "<tbody>" + main + "</tbody>" + table_bottom;
        }

        else{
            document.getElementById("table2").innerHTML = "<h3 style='color:red'>NOT FOUND</h3>";
        }
    };

    xhr.send();

}