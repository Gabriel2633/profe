/**
 * 
 */


	function eliminar(id) {
		var nombrecli=document.getElementById("cliente" + id).parentElement.children[0].dataset.nombre;
		if(confirm("Seguro que desea eliminar el cliente " + nombrecli + "?"));
		document.location.href="clientes?operacion=eliminar&id=" + id;
	
	}

