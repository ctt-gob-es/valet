var getTsls =/*[[@{/tsldatatable}]]*/;

var tbl = $('#tslTable').DataTable({
	dom: 'Bfrtip',
	select: 'single'
	responsive : true,
	altEditor: true,
	  buttons: [{text: 'Agregar',name: 'add'}, {extend: 'selected',text: 'Editar',name: 'edit'}, {extend: 'selected',text: 'Eliminar',name: 'delete'}],
	  "iTotalRecords": "totalElements",
      "iTotalDisplayRecords": "numberOfElements",
  	"processing": true,
    "serverSide": true,
	"ajax":  getTsls,
	"language": {
        "url": "js/datatables/i18n/spanish.json",
        select: {
            rows: {
                _: "%d filas seleccionadas",
                1: "1 fila seleccionada"
            }
          }
        },
	"columns": [
        { "data": "idTslValet",
          "visible": false},
        { "data": "sequenceNumber" },
        { "data": "issueDate" },
        { "data": "expirationDate"}
        ]
      
	
}).on('crudaction', function(e, accion, idTslValet, data, rowindex) {
	// e          Evento Jquery
    // accion     [add|edit|delete]
    // pkid       Primer campo en la data [id]                ... en add,    retorna null
    // data       Los campos adicionales  [campo_1, campo_n]  ... en delete, retorna null
    // rowindex   El index de la fila para el dataTable       ... en add,    retorna null
	 $('#altEditor-modal .modal-body .alert').remove();
	    // Se muestra la capa 'cargando...'
	    loading();
	    
	    switch(accion){
	        case 'add':
	            break;
	        case 'edit':       	
         	break;
	        case 'delete':
	       break;
	    }
});
});