function redirect(){
    window.location.href = "MainPageManager.html";
}

function createCat(){

    let description = document.querySelector('#description');

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/api/v1/Categorias";

    xhr.open("POST", url, true);

    xhr.setRequestHeader("Content-Type", "application/json");

    var data = JSON.stringify({  "descripcion": description.value }); 
    alert("New category saved");
    xhr.send(data);
}


function showAllCats(){ 

    let xhr = new XMLHttpRequest(); 
    let url = "http://localhost:8080/api/v1/Categorias"; 

    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/json"); 

    xhr.onreadystatechange = function () { 
        if (xhr.readyState === 4 && xhr.status === 200) { 

            let list = JSON.parse(this.responseText);
            let main = "";
            

            for(let i=0; i < list.length ; i ++){
                main += "<tr><th scope='row' style='text-align:center'>" + list[i].id_categorias + "</th><td>" +  list[i].descripcion + "</td></tr>";
            }

            let table_top = "<table class='table' style='background-color:#d4d4d4'>";
            let table_bottom = "</table>";
            let headers = "<thead class='thead-dark'><tr><th scope='col' style='text-align:center'>Category id</th><th style='text-align:center' scope='col'>Description</th></tr></thead>";
            document.getElementById("table").innerHTML = table_top + headers + "<tbody>" + main + "</tbody>" + table_bottom;
        } 
    }; 
    xhr.send();
} 

function searchCatById(){

    let category_id = document.querySelector('#category_id2search').value;

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/api/v1/Categorias/" + category_id;

    xhr.open("GET", url, true);

    xhr.setRequestHeader("Content-Type", "application/json");

    let returned_data;
    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            let resultado = JSON.parse(this.responseText);
            main = "<tr><th scope='row'  style='text-align:center'>" + resultado.id_categorias + "</th><td>" +  resultado.descripcion + "</td></tr>";
            let table_top = "<table class='table' style='background-color:#d4d4d4'>";
            let table_bottom = "</table>";
            let headers = "<thead class='thead-dark'><tr><th scope='col' style='text-align:center'>Category id</th><th style='text-align:center' scope='col'>Description</th></tr></thead>";


            document.getElementById("table2").innerHTML = table_top + headers + "<tbody>" + main + "</tbody>" + table_bottom;
        }
        else{
            document.getElementById("table2").innerHTML = "<h3 style='color:red'>NOT FOUND</h3>";
        }
    };

    xhr.send();
}

function updateCat(){

    let category_id = document.querySelector('#category_id2update').value;
    let description = document.querySelector('#description2update');

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/api/v1/Categorias/" + category_id;

    xhr.open("PUT", url, true);

    xhr.setRequestHeader("Content-Type", "application/json");

    var data = JSON.stringify({"descripcion": description.value });
    alert("Category updated");

    xhr.send(data);

}


function deleteCat(){

    let category_id = document.querySelector('#category_id2delete').value;

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/api/v1/Categorias/" + category_id;

    xhr.open("DELETE", url, true);

    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.send();
    alert("Deleted category with id " + category_id);

}
