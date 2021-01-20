function showAllCats(){

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/api/v1/Categorias";

    xhr.open("GET", url, true);

    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            let list = JSON.parse(this.responseText);
            let main = "";
            let table_top = "<table class='table' style='background-color:#d4d4d4'>";
            let table_bottom = "</table>";

            for(let i=0; i < list.length ; i ++){
                main += "<tr><th scope='row' style='text-align:center'>" + list[i].id_categorias + "</th><td>" +  list[i].descripcion + "</td></tr>";
            }

            let headers = "<thead class='thead-dark'><tr><th scope='col' style='text-align:center'>Category id</th><th scope='col' style='text-align:center'>Description</th></tr></thead>";
            document.getElementById("table").innerHTML = table_top + headers + "<tbody>" + main + "</tbody>" + table_bottom;
        }
    };

    xhr.send();

}
