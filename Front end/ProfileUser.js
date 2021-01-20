document.getElementById("myDIV").style.display = "none";
function redirect(){
    window.location.href = "MainPageUser.html";
}

function inicia(){
    
    let mail = document.querySelector('#email');
    let password = document.querySelector('#password');
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8081/api/v1/monedero";

  
    xhr.open("POST", url, true);
  
    xhr.setRequestHeader("Content-Type", "application/json");

        var flag=0;
        xhr.onreadystatechange = function () { 
            if (xhr.readyState === 4 && xhr.status === 200) {

                var object = JSON.parse(this.responseText);
                var monedero = object.monedero;
                let  idUsuario2 = object.id_usuario;
                let sumar = document.getElementById("numero").value;
                let sumar2 = parseInt(sumar);
                let total = parseInt(monedero)  + sumar2;
                let xhr_1 = new XMLHttpRequest(); 
                let url_1 = "http://localhost:8081/api/v1/usuario/" + idUsuario2; 
            
                xhr_1.open("PUT", url_1, true); 
                xhr_1.setRequestHeader("Content-Type", "application/json");
            
            
                xhr_1.onreadystatechange = function () { 
                    if (xhr_1.readyState === 4 && xhr_1.status === 200) { 
                        let resultado = JSON.parse(this.responseText);
                    } 
            
                }; 
                var data = JSON.stringify({ "id_usuario": object.id_usuario, "name": object.name,
                "lastName": object.lastName, 
                "mail":object.mail,
                "password": object.password,
                "monedero": total, "usuario_tipo" : object.usuario_tipo });
                xhr_1.send(data); 

                document.getElementById("resultado").innerHTML="Your current wallet has: "+monedero + " dollars";
                
                    if(object.usuario_tipo==1){

                    }else{

                    }
        
            } else if(xhr.status===404 && flag==0){
                    flag+=1;
                }       
        }; 

    var data = JSON.stringify({ "mail": mail.value, "password": password.value });       
    xhr.send(data);
   alert("Your wallet have been updated");

}


function confirma(){

    let mail = document.querySelector('#email');
    let password = document.querySelector('#password');
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8081/api/v1/monedero";

  
    xhr.open("POST", url, true);
  
    xhr.setRequestHeader("Content-Type", "application/json");

        var flag=0;
        xhr.onreadystatechange = function () { 
            if (xhr.readyState === 4 && xhr.status === 200) {

                var object = JSON.parse(this.responseText);
                var monedero = object.monedero;
                let  idUsuario2 = object.id_usuario;

                
                document.getElementById("resultado").innerHTML="Your current wallet has: "+monedero + " dollars";
                

                    if(object.usuario_tipo==1){ 
                        alert("Valid credentials"); 

                    }else{ 
                        alert("Valid credentials"); 

                    }
        
            } else if(xhr.status===404 && flag==0){
                    flag+=1;
                    alert("Invalid credentials");
                }       
        }; 

    var data = JSON.stringify({ "mail": mail.value, "password": password.value });       
    xhr.send(data);
    document.getElementById("myDIV").style.display = "block";
}