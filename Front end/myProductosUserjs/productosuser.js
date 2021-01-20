var miCarrito = new Map();
function redirect() {
  window.location.href = "MainPageUser.html";
}

function showAllUsers() {
  let xhr = new XMLHttpRequest();
  let url = "http://localhost:8082/api/v1/Productos";

  xhr.open("GET", url, true);

  xhr.setRequestHeader("Content-Type", "application/json");

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {

      let list = JSON.parse(this.responseText);
      let main = "";

      for (let i = 0; i < list.length; i++) {
        main += "<tr><th scope='row' style='text-align:center'>" + list[i].id_producto + "</th><td>" + list[i].descripccion + "</td>" +
          "<td>" + list[i].precio + "</td>" + "<td>" + list[i].categoria.id_categorias + "</td>" +
          "<td>" + list[i].existencia + "</td>" + "<td> <img src='" +
          list[i].image_url +
          "' width='100' height='128' class='img-thumbnail'></td>" +
          " <td><input type='number' id='quantityList" + list[i].id_producto + "' min='1' max='" + list[i].existencia + "' placeholder='Qty' class='form-control entrada'></td> </td>" +
          "<td> <button class='btn btn-success' onclick='addList(" + list[i].id_producto + ")'>ADD</button></td>" +
          "<td> <button class='btn btn-danger' onclick='resetCar(" + list[i].id_producto + ")'>DELETE</button></td></tr>";
          console.log(list[i].image_url);
      }

      let table_top = "<table class='table' style='background-color:#d4d4d4'>";
      let table_bottom = "</table>";
      let headers = "<thead class='thead-dark'><tr><th scope='col' style='text-align:center'>Product id</th>" +
        "<th scope='col' style='text-align:center'>Name</th><th scope='col' style='text-align:center'>Price</th><th scope='col' style='text-align:center'>Category id</th><th scope='col' style='text-align:center'>Stock</th><th scope='col' style='text-align:center'>Cover</th><th scope='col' style='text-align:center'>Quantity</th><th scope='col' style='text-align:center'>Add to cart</th>" +
        "<th scope='col' style='text-align:center'>Delete From Cart</th></tr></thead>";

      document.getElementById("table").innerHTML = table_top + headers + "<tbody>" + main + "</tbody>" + table_bottom;
    }
  };
  

  xhr.send();
}

window.onload = showAllUsers;

function addList(id_producto) {
  let id = "#quantityList" + id_producto;
  quantity = parseInt(document.querySelector(id).value);

  let product_id = id_producto;
  let xhr = new XMLHttpRequest();
  let url = "http://localhost:8082/api/v1/Productos/" + product_id;

  xhr.open("GET", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {

    if (xhr.readyState === 4 && xhr.status === 200) {


      let resultado = JSON.parse(this.responseText);
      var resultado2;
      if (sessionStorage.getItem(id_producto) === null) {
        if (quantity > resultado.existencia) {
          quantity = parseInt(resultado.existencia);
        }
        resultado2 = {
          descripccion: resultado.descripccion, precio: resultado.precio,
          categoria: resultado.categoria.descripcion, image_url: resultado.image_url,
          Quantity: quantity
        };
      } else {
        var previous = parseInt(JSON.parse(sessionStorage.getItem(id_producto)).Quantity);
        previous += quantity;
        if (previous > resultado.existencia) {
          previous = parseInt(resultado.existencia);
        }
        resultado2 = {
          descripccion: resultado.descripccion, precio: resultado.precio,
          categoria: resultado.categoria.descripcion, image_url: resultado.image_url,
          Quantity: previous
        };
      }


      sessionStorage.setItem(id_producto, JSON.stringify(resultado2));

    }
  };

  xhr.send();
  alert("Product added");

}
function addList2(id_producto) {
  let id = "#quantityList2" + id_producto;
  quantity = parseInt(document.querySelector(id).value);

  let product_id = id_producto;
  let xhr = new XMLHttpRequest();
  let url = "http://localhost:8082/api/v1/Productos/" + product_id;

  xhr.open("GET", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {

    if (xhr.readyState === 4 && xhr.status === 200) {


      let resultado = JSON.parse(this.responseText);
      var resultado2;
      if (sessionStorage.getItem(id_producto) === null) {
        if (quantity > resultado.existencia) {
          quantity = parseInt(resultado.existencia);
        }
        resultado2 = {
          descripcion: resultado.descripcion, precio: resultado.precio,
          categoria: resultado.categoria.descripcion, image_url: resultado.image_url,
          Quantity: quantity
        };
      } else {
        var previous = parseInt(JSON.parse(sessionStorage.getItem(id_producto)).Quantity);
        previous += quantity;
        if (previous > resultado.existencia) {
          previous = parseInt(resultado.existencia);
        }
        resultado2 = {
          descripcion: resultado.descripcion, precio: resultado.precio,
          categoria: resultado.categoria.descripcion, image_url: resultado.image_url,
          Quantity: previous
        };
      }


      sessionStorage.setItem(id_producto, JSON.stringify(resultado2));

    }
  };

  xhr.send();

}

function addByName(id_producto) {
  let id = "#quantityByName" + id_producto;
  quantity = parseInt(document.querySelector(id).value);

  let product_id = id_producto;
  let xhr = new XMLHttpRequest();
  let url = "http://localhost:8082/api/v1/Productos/" + product_id;

  xhr.open("GET", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {

    if (xhr.readyState === 4 && xhr.status === 200) {


      let resultado = JSON.parse(this.responseText);
      var resultado2;
      if (sessionStorage.getItem(id_producto) === null) {
        if (quantity > resultado.existencia) {
          quantity = parseInt(resultado.existencia);
        }
        resultado2 = {
          descripcion: resultado.descripcion, precio: resultado.precio,
          categoria: resultado.categoria.descripcion, image_url: resultado.image_url,
          Quantity: quantity
        };
      } else {
        var previous = parseInt(JSON.parse(sessionStorage.getItem(id_producto)).Quantity);
        previous += quantity;
        if (previous > resultado.existencia) {
          previous = parseInt(resultado.existencia);
        }
        resultado2 = {
          descripcion: resultado.descripcion, precio: resultado.precio,
          categoria: resultado.categoria.descripcion, image_url: resultado.image_url,
          Quantity: previous
        };
      }


      sessionStorage.setItem(id_producto, JSON.stringify(resultado2));

    }
  };

  xhr.send();
}

function resetCar(id_producto) {

  sessionStorage.removeItem(id_producto);

}
function resetCarPage(id_producto) {

  sessionStorage.removeItem(id_producto);
  fillShoppingCart();

}

function fillShoppingCart() {

  var main = '';

  for (var i = 0; i < sessionStorage.length; i++) {
    product_id = sessionStorage.key(i);

    resultado = JSON.parse(sessionStorage.getItem(product_id));
    main += "<tr><th scope='row' style='text-align:center'>" + resultado.descripccion + "</th>" + "<td>" + resultado.precio + "</td>"
      + "<td>" + resultado.categoria + "</td>"
      + "<td> <img src='" + resultado.image_url + "' width='100' height='128' class='img-thumbnail'></td>" +
      " <td>" + resultado.Quantity + "</td>" + "<td>" + parseFloat(resultado.Quantity) * parseFloat(resultado.precio) +
      "<td> <button onclick='resetCarPage(" + product_id + ")' class='btn btn-danger'>DELETE</button></tr>";
      

  }
  let table_top = "<table class='table' style='background-color:#d4d4d4'>";
  let table_bottom = "</table>";
  let headers = "<thead class='thead-dark'><tr><th scope='col' style='text-align:center'>Product Name</th>" +
    "<th scope='col' style='text-align:center'>Price</th><th scope='col' style='text-align:center'>Category</th><th scope='col' style='text-align:center'>Cover</th><th scope='col' style='text-align:center'>Quantity</th><th scope='col' style='text-align:center'>Subtotal</th>" +
    "<th scope='col' style='text-align:center'>Delete From cart</th></tr></thead>";

    
  document.getElementById("table3").innerHTML = table_top + headers + "<tbody>" + main + "</tbody>" + table_bottom;


}



function searchProductByName() {

  let product_id = document.querySelector('#product_id2search').value;
  let xhr = new XMLHttpRequest();
  let url = "http://localhost:8082/api/v1/ProductosUser/" + product_id;

  xhr.open("GET", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {

    if (xhr.readyState === 4 && xhr.status === 200) {

      let resultado = JSON.parse(this.responseText);
            main = "<tr><th scope='row' style='text-align:center'>" + resultado.id_producto + "</th><td>" +  resultado.descripccion + "</td>"
            + "<td>" +  resultado.precio + "</td>" + "<td>" +  resultado.categoria.id_categorias + "</td>"
            + "<td>" +  resultado.existencia + "</td>" + "<td> <img src='" +  resultado.image_url+"' width='100' height='128' class='img-thumbnail'></td>"+
            " <td><input type='number' id='quantityList" + resultado.id_producto + "' min='1' max='" + resultado.existencia + "' placeholder='Qty' class='form-control entrada'></td> </td>" +
          "<td> <button class='btn btn-success' onclick='addList(" + resultado.id_producto + ")'>ADD</button></td>" +
          "<td> <button class='btn btn-danger' onclick='resetCar(" + resultado.id_producto + ")'>DELETE</button></td></tr>";

        let table_top = "<table class='table' style='background-color:#d4d4d4'>";
        let table_bottom = "</table>";
        let headers = "<thead class='thead-dark'><tr><th scope='col' style='text-align:center'>Product id</th>" +
          "<th scope='col' style='text-align:center'>Name</th><th scope='col' style='text-align:center'>Price</th><th scope='col' style='text-align:center'>Category id</th><th scope='col' style='text-align:center'>Stock</th><th scope='col' style='text-align:center'>Cover</th><th scope='col' style='text-align:center'>Quantity</th><th scope='col' style='text-align:center'>Add to cart</th>" +
          "<th scope='col' style='text-align:center'>Delete From Cart</th></tr></thead>";
        document.getElementById("table1").innerHTML = table_top + headers + "<tbody>" + main + "</tbody>" + table_bottom;

      
            
    }

    else {
      document.getElementById("table1").innerHTML = "<h3 style='color:red'>NOT FOUND</h3>";
    }
  };

  xhr.send();

}

function searchProductByCat() {

  let cat_id = document.querySelector('#choose').value;
  let xhr = new XMLHttpRequest();
  let url = "http://localhost:8082/api/v1/ProductosCategoria/" + cat_id;
  xhr.open("GET", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {

      let list = JSON.parse(this.responseText);
      let main = "";

      for (let i = 0; i < list.length; i++) {
        main += "<tr><th scope='row' style='text-align:center'>" + list[i].id_producto + "</th><td>" + list[i].descripccion + "</td>"
          + "<td>" + list[i].precio + "</td>" + "<td>" + list[i].categoria.id_categorias + "</td>"
          + "<td>" + list[i].existencia + "</td>" + "<td> <img src='" + list[i].image_url + "' width='100' height='128' class='img-thumbnail'></td>" +
          " <td><input type='number' id='quantityList" + list[i].id_producto + "' min='1' max='" + list[i].existencia + "' placeholder='Qty' class='form-control entrada'></td> </td>" +
          "<td> <button class='btn btn-success' onclick='addList(" + list[i].id_producto + ")'>ADD</button></td>" +
          "<td> <button class='btn btn-danger' onclick='resetCar(" + list[i].id_producto + ")'>DELETE</button></td></tr>";
      }

      let table_top = "<table class='table' style='background-color:#d4d4d4'>";
      let table_bottom = "</table>";
      let headers = "<thead class='thead-dark'><tr><th scope='col' style='text-align:center'>Product id</th>" +
        "<th scope='col' style='text-align:center'>Name</th><th scope='col' style='text-align:center'>Price</th><th scope='col' style='text-align:center'>Category id</th><th scope='col' style='text-align:center'>Stock</th><th scope='col' style='text-align:center'>Cover</th><th scope='col' style='text-align:center'>Quantity</th><th scope='col' style='text-align:center'>Add to cart</th>" +
        "<th scope='col' style='text-align:center'>Delete From Cart</th></tr></thead>";
      document.getElementById("table2").innerHTML = table_top + headers + "<tbody>" + main + "</tbody>" + table_bottom;
    }

    else {
      document.getElementById("table2").innerHTML = "<h3 style='color:red'>NOT FOUND</h3>";
    }
  };

  xhr.send();

}

function subtotales() {

  var subtotal = 0;

  for (var i = 0; i < sessionStorage.length; i++) {
    product_id = sessionStorage.key(i);

    resultado = JSON.parse(sessionStorage.getItem(product_id));
    subtotal += parseFloat(resultado.precio) * parseFloat(resultado.Quantity);
  }

  return subtotal;
}

function monedero() {


  let xhr = new XMLHttpRequest();
  let url = "http://localhost:8081/api/v1/infoUsuario/" + JSON.parse(localStorage.getItem("mail_user"));
  //console.log();
  console.log(url);
  xhr.open("GET", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {

      var object = JSON.parse(this.responseText);
      var monedero = object.monedero;
      localStorage.setItem("monedero", JSON.stringify(monedero));


    }
  };
  xhr.send();
}

function updateWallet(wallet) {

  let xhr = new XMLHttpRequest();
  let url = "http://localhost:8081/api/v1/usuario/" + JSON.parse(localStorage.getItem("id_user"));


  xhr.open("GET", url, true);

  xhr.setRequestHeader("Content-Type", "application/json");

  var flag = 0;
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {

      var object = JSON.parse(this.responseText);

      ///////////Sentencia put
      let xhr_1 = new XMLHttpRequest();

      let url_1 = "http://localhost:8081/api/v1/usuario/" + JSON.parse(localStorage.getItem("id_user"));
      //console.log(url_1);

      xhr_1.open("PUT", url_1, true);
      xhr_1.setRequestHeader("Content-Type", "application/json");

      //console.log("f1");

      xhr_1.onreadystatechange = function () {
        // console.log("f2");

        if (xhr_1.readyState === 4 && xhr_1.status === 200) {
          console.log("f3");
          let resultado = JSON.parse(this.responseText);
          console.log(resultado);



        }

      };
      var data = JSON.stringify({
        "id_usuario": object.id_usuario, "name": object.name,
        "lastName": object.lastName,
        "mail": object.mail,
        "password": object.password,
        "monedero": wallet, "usuario_tipo": object.usuario_tipo
      });
      xhr_1.send(data);



    } else if (xhr.status === 404 && flag == 0) {
      flag += 1;
      //alert("Invalid credentials");
    }
  };

  xhr.send();


  //console.log(idUsuario1);
}

function buy() {
  var subtotal = subtotales();
  var promocion1 = 0; //500 pesos
  var promocion2 = 0; //Hora
  var today = new Date();
  var current_time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
  var total = 0;
  //console.log("current "+current_time);

  if (subtotal > 500) {
    promocion1 = subtotal * 0.1;
  }
  if (localStorage.getItem("hora_promo") >= current_time) {
    promocion2 = subtotal * 0.1;
  }
  monedero();
  //console.log(subtotal);
  //console.log("promo1"+ promocion1);
  //console.log("promo2"+ promocion2);

  total = subtotal - promocion1 - promocion2;
  var wallet = 0;

  if (localStorage.getItem("monedero") >= total) {
    alert("Purchase succesful");
    wallet = parseFloat(localStorage.getItem("monedero"));
    wallet -= total;
    console.log(wallet);
    updateWallet(wallet);
    createPurchase();

    for (var i = 0; i < sessionStorage.length; i++) {
      let product_id = sessionStorage.key(i);
      let cantidad = JSON.parse(sessionStorage.getItem(product_id));
      console.log("product" + product_id);
      console.log("cantidad" + cantidad);

      createDetalles(product_id, cantidad.Quantity);
      updateStock(product_id, cantidad.Quantity);
    }
    createTicket(promocion1, promocion2, subtotal, total);
    sessionStorage.clear();
    fillShoppingCart();


  } else {
    alert("Insufficient funds");
  }

}

function createPurchase() {

  let xhr = new XMLHttpRequest();
  let url = "http://localhost:8083/api/v1/Compras";

  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let response = JSON.parse(this.responseText);
      localStorage.setItem("id_compra", JSON.stringify(response.id_compras));
    }
  };
  var data = JSON.stringify({ "usuario": { "id_usuario": JSON.parse(localStorage.getItem("id_user")) } });
  xhr.send(data);
}

function createDetalles(id_producto, cantidad) {
  let xhr = new XMLHttpRequest();
  let url = "http://localhost:8084/api/v1/Detalles";

  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      // let response = JSON.parse(this.responseText);
    }
  };
  var data = JSON.stringify({
    "compra": { "id_compras": JSON.parse(localStorage.getItem("id_compra")) },
    "producto": { id_producto },
    "cantidad": cantidad
  });
  xhr.send(data);

}


function createTicket(promocion1, promocion2, subtotal, total) {
  let xhr = new XMLHttpRequest();
  let url = "http://localhost:8085/api/v1/Ticket";

  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      // let response = JSON.parse(this.responseText);
    }
  };
  var data = JSON.stringify({
    "id_compras": { "id_compras": JSON.parse(localStorage.getItem("id_compra")) },
    "subtotal": subtotal,
    "promocion1": promocion1,
    "promocion2": promocion2,
    "total": total
  });
  xhr.send(data);
  alert("Order ID: " + JSON.parse(localStorage.getItem("id_compra")) + "Save your Order ID");
}

function updateStock(product_id, quantity) {

  let xhr = new XMLHttpRequest();
  let url = "http://localhost:8082/api/v1/Productos/" + product_id;

  xhr.open("PUT", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");

  let xhr_1 = new XMLHttpRequest();
  let url_1 = "http://localhost:8082/api/v1/Productos/" + product_id;

  xhr_1.open("GET", url_1, true);
  xhr_1.setRequestHeader("Content-Type", "application/json");
  xhr_1.onreadystatechange = function () {

    if (xhr_1.readyState === 4 && xhr_1.status === 200) {

      let resultado = JSON.parse(this.responseText);
      let stock = parseInt(resultado.existencia) - quantity;
      var data = JSON.stringify({
        "id_productos": resultado.id_productos, "descripcion": resultado.descripccion,
        "precio": resultado.precio,
        "categoria": {
          "id_categorias": resultado.categoria.id_categorias,
          "descripcion": resultado.categoria.descripcion
        },
        "existencia": stock, "image_url": resultado.image_url
      });
      xhr.send(data);
    }
  };

  xhr_1.send();
}