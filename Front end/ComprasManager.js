// function createCompra(){
//
//
//     let id_usiarios = document.querySelector('#id_usuarios');
//
//
//
//     let xhr = new XMLHttpRequest();
//     let url = "http://localhost:8083/api/v1/Compras";
//
//     xhr.open("POST", url, true);
//
//     xhr.setRequestHeader("Content-Type", "application/json");
//
//     var data = JSON.stringify({  "usuario": { "id_usuario": id_usiarios.value} });
//     alert("New category saved");
//     console.log(data);
//     xhr.send(data);

function redirect(){
    window.location.href = "MainPageManager.html";
}

function showAllOrders(){

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8083/api/v1/Compras";

    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            let list = JSON.parse(this.responseText);
            let main = "";

            for(let i=0; i < list.length ; i ++){
                main += "<tr><th scope='row'>" + list[i].id_compras + "</th><td>" +  list[i].id_usuario + "</td>"
                + "<td>" +  list[i].usuarioMail + "</td>" +"</tr>";
            }

            let table_top = "<table class='table'>";
            let table_bottom = "</table>";
            let headers = "<thead class='thead-dark'><tr><th scope='col'>Order id</th><th scope='col'>User id</th>"+
                            "<th scope='col'>E-mail</th></tr></thead>";

            document.getElementById("table").innerHTML = table_top + headers + "<tbody>" + main + "</tbody>" + table_bottom;
        }
    };

    xhr.send();

}

function searchOrderById(){

    let order_id = document.querySelector('#order_id2search').value;
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8083/api/v1/Compras/" + order_id;

    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {

        if (xhr.readyState === 4 && xhr.status === 200) {

            let resultado = JSON.parse(this.responseText);
            main = "<tr><th scope='row'>" + resultado.id_compras + "</th><td>" +  resultado.id_usuario + "</td>"
            + "<td>" +  resultado.usuarioMail + "</td>" +"</tr>";

            let table_top = "<table style='margin-left: auto; margin-right: auto;' border='1'>";
            let table_bottom = "</table>";
            let headers = "<thead class='thead-dark'><tr><th scope='col'>Order id</th><th scope='col'>User id</th>"+
                            "<th scope='col'>E-mail</th></tr></thead>";

            document.getElementById("table1").innerHTML = table_top + headers + "<tbody>" + main + "</tbody>" + table_bottom;
        }

        else{
            document.getElementById("table1").innerHTML = "<h3 style='color:red'>NOT FOUND</h3>";
        }
    };

    xhr.send();

}
