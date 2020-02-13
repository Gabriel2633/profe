<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<style>
td {
	padding-left: 10px;
	padding-right: 10px;
}
</style>
<title>Listado de clientes</title>
</head>
<body>
	<div class="container-fluid"
		style="background-color: #000; margin-bottom: 30px">
		<div class="row">
			<div class="col-12">&nbsp;</div>
		</div>
	</div>
	<div id="contenedor" class="container">
		<div id="body" class="row">
			<div class="col-12">
				<h3>LISTADO DE CLIENTES</h3>
			</div>
			<div class="col-12">
				<!-- Button trigger modal -->
				<p>
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#formCliente" onClick="document.forms[0].reset()">Añadir cliente</button>
				</p>
				<table border="1">
					<thead>
						<tr>
							<th>ID</th>
							<th>NOMBRE</th>
							<th>APPELIDOS</th>
							<th>NACIMIENTO</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cliente" items="${clientes}">
							<tr id="cliente${cliente.id}" data-id="${cliente.id}"
								data-nombre="${cliente.nombre}"
								data-apellidos="${cliente.apellidos}"
								data-fnacimiento="${cliente.fnacimiento}">
								<td>${cliente.id}</td>
								<td>${cliente.nombre}</td>
								<td>${cliente.apellidos}</td>
								<td>${cliente.fnacimiento}</td>
								<td><span 
									onClick="mostrarCliente(${cliente.id})"> 
									<i class="fas fa-user-edit"></i></span></td>
								<td><span 
									onClick="eliminarCliente(${cliente.id})" style="color:red"> <i
										class="fas fa-user-times"></i></span></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!--  VENTANA MODAL BOOTSTRAP  -->
	<div class="modal fade" id="formCliente" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Datos del cliente</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="formCliente" name="formCliente">
						<!--  CULTO -->
						<input type=text name="id" id="id" value="0">
						<p>
							Nombre: <input type="text" name="nombre" id="nombre" value="">
						</p>
						<p>
							Apellidos: <input type="text" name="apellidos" id="apellidos"
								value="">
						</p>
						<p>
							F.Nacimiento: <input type="date" name="fnacimiento"
								id="fnacimiento" value="">
						</p>
						<input type="button" id="btnEnviar"
							class="btn btn-primary" onClick="enviarCliente()" 
							value="Enviar cliente">
					</form>
				</div>
				<div class="modal-footer">* Se agregará/actualizará un cliente
					pero no se comprueba si ya existe.</div>
			</div>
		</div>
	</div>

</body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<script>

function enviarCliente(){
	var xhr = new XMLHttpRequest();
	var cliente=new Object();
	cliente.operacion="enviar";
	cliente.id= document.getElementById("id").value;
	cliente.nombre = $('#nombre').val();
	cliente.apellidos = $('#apellidos').val();
	cliente.fnacimiento = $('#fnacimiento').val();
	console.log(param(cliente));
	xhr.open('POST', '/CrudProfe/clientes?' + param(cliente));
	xhr.onload = function() {
	    if (xhr.status === 200) {
	        window.location.href='/CrudProfe/clientes';
	    }
	    else {
	        alert('Request failed.  Returned status of ' + xhr.status);
	    }
	};
	xhr.send();	

}

function eliminarCliente(id){
	if(!confirm("¿seguro que desea eliminar el cliente " + id + "?")) return false;
	var xhr = new XMLHttpRequest();
	xhr.open('POST', '/CrudProfe/clientes?operacion=eliminar&id=' + id);
	xhr.onload = function() {
	    if (xhr.status === 200) {
	        window.location.href='/CrudProfe/clientes';
	    }
	    else {
	        alert('Request failed.  Returned status of ' + xhr.status);
	    }
	};
	xhr.send();	

}

function mostrarCliente(id){
	var cliente=document.getElementById("cliente" + id);
	console.log(cliente);
	document.getElementById("id").value=cliente.dataset.id;
	document.getElementById("nombre").value=cliente.dataset.nombre;
	document.getElementById("apellidos").value=cliente.dataset.apellidos;
	document.getElementById("fnacimiento").value=cliente.dataset.fnacimiento;
	$('#formCliente').modal('show');
}

function param(object) {
    var encodedString = '';
    for (var prop in object) {
        if (object.hasOwnProperty(prop)) {
            if (encodedString.length > 0) {
                encodedString += '&';
            }
            encodedString += encodeURI(prop + '=' + object[prop]);
        }
    }
    return encodedString;
}



</script>
</html>