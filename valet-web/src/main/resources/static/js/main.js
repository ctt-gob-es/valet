
(function ($) {
    "use strict";


    /*==================================================================
    [ Focus input ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })
  
  
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    /*==================================================================
    [ Show pass ]*/
    var showPass = 0;
    $('.btn-show-pass').on('click', function(){
        if(showPass == 0) {
            $(this).next('input').attr('type','text');
            $(this).find('i').removeClass('zmdi-eye');
            $(this).find('i').addClass('zmdi-eye-off');
            showPass = 1;
        }
        else {
            $(this).next('input').attr('type','password');
            $(this).find('i').addClass('zmdi-eye');
            $(this).find('i').removeClass('zmdi-eye-off');
            showPass = 0;
        }
        
    });
    
})(jQuery);

/*==================================================================
[ Submit ]*/

function submit(form) {
	form.submit();
}

function logout() {
	$("#logoutForm").submit();
}

/**
 * Modifica la acción de un formulario.
 * 
 * @param form
 *            Formulario.
 */
function changeSubmitInto(idform, idTarget, action) {

	/* se modifica el action del formulario */
	$("#" + idform).action = action;
	/* NOTA: se mantiene el namespace del formulario */
	
	loading();

	$.post($(this).attr("action"), $(this).serialize(), function(html) {
		$("#" + idTarget).html(html);
		hide();
	});

	/* se cancela la recarga del href */
	return false;
	
}

/*==================================================================
[ Ajax ]*/

/**
 * Carga la url indicada dentro de un elemento determinado con peticiones AJAX.
 * 
 * @param formTarget
 *            Identificador del formulario.
 * @param url
 *            URL.
 * @param idTarget
 *            Identificador del elemento de la pagina a recargar.
 * @param funcion
 *            Funcion opcional a ejecutar.
 */
function loadIntoAjax(formTarget, url, idTarget, type, funcion, doLoad) {
	var data = null;

	if (isNaN(doLoad) || doLoad) {
		/* se añade la capa "cargando" */
		loading();
	}

	if (formTarget != null) {
		data = $("#" + formTarget).serialize();
	}

	if (url.indexOf("?") == -1) {
		url = url.concat("?");
	} else {
		url = url + "&";
	}

	/* se realiza la carga */
	$.ajax({
		url : url + "idPanel=" + idTarget,
		type : type,
		data : data,
		cache : false,
		success : function(data) {
			$("#" + idTarget).html(data);
			hide();
			if (funcion) {
				funcion();
			}
		},
		error : function(xhr, ajaxOptions, thrownError) {
			if (isNaN(doLoad) || doLoad) {
				/* se oculta la capa "cargando" */
				hide();
			}
			//showGlobalError("Error en la petici\u00f3n de " + url + "\nidPanel=" + idTarget + "TE: " + thrownError);
		}

	});

	return false;
}

function ajaxCall(action, data, contentType, formId) {
	$.ajax(actionSave, {
		data : formData,
    	contentType: false,
        type:'POST',
        success: function(data){
          
    		// Se oculta la capa 'cargando...'
    		hide();
            tbl.row.add($(data.data)).draw(false);
            		                    
            $('#altEditor-modal .modal-body .alert').remove();
            $('#altEditor-modal').modal('hide');
            		                    
        },
        error:function(data){
        	
        	// Se oculta la capa 'cargando...'
        	hide();
        	// Se eliminan los posibles errores anteriores...
        	cleanValidationResult('tslForm');
        	// Se obtiene el JSON de los campos con errores de validación
        	// y se modifican los estilos/añaden mensajes
        	var validation = $(data)["0"].responseJSON;
        	drawValidationResult(validation.fieldErrors);
      
        }
    });
}

function openModal(idModal) {

		var newButton = jQuery('<button>', {

		"data-toggle" : "modal",
		"data-target" : idModal

	});

	newButton.appendTo('body').click();
}

/**
 * Pone el elemento indicado en "cargando".
 * 
 * @param idTarget
 *            Identificador del elemento de la página a recargar.
 */
var showingCount = 0; // flag para evitar el efecto de muestra-oculta +
// muestra-oculta + muestra-oculta, etc
function loading() {

	showingCount++;
	if (showingCount > 1) { // tan solo debe mostrarse una vez
		return;
	}

	var layer = $("#fragment-loading-layer");

	layer.height($(document).height());

	var Browser = navigator.appName;

	var Micro = Browser.indexOf("Microsoft");

	if (Micro >= 0) {
		layer.width($(document).width() - 20);
	} else {
		layer.width($(document).width());
	}

	layer.css("position", "absolute");
	layer.css("top", "0");
	layer.css("left", "0");

	layer.show("fade", 500);

}

/**
 * Quita el elemento "cargando".
 * 
 */
function hide() {
	setTimeout(function() {
		if (showingCount > 1) {
			showingCount--;
		} else if (showingCount === 1) { // cuando quede uno solo se debe
			// ocultar
			showingCount--;
			var layer = $("#fragment-loading-layer");
			layer.hide('fade');
		} else if (showingCount === 0) { // y cuando no quede ninguno,
			// significa que viene de un submit
			var layer = $("#fragment-loading-layer");
			layer.hide('fade');
		}
	}, 400); // tiempo de margen en esparar a ver si la pantalla recien
	// cargada lanza un loading()

}

// Client side validation
function validate(obj, msg)
{
  if(!obj.checkValidity())
  {
	 $("#invalid-" + $(obj).attr('id')).html(msg);
	 $("#" + $(obj).attr('id')).addClass("is-invalid");
  } else {
	 $("#" + $(obj).attr('id')).removeClass("is-invalid");
  }
}

function drawValidationResult(jsonFieldErrors) {
	
	for(var v in jsonFieldErrors) {
	    if(jsonFieldErrors.hasOwnProperty(v)) {
	    	$("#invalid-" + jsonFieldErrors[v].field).html(jsonFieldErrors[v].rejectedValue);
	    	$("#" + jsonFieldErrors[v].field).addClass("is-invalid");
	    	
	    }
	}
}

function cleanValidationResult(formId) {
	$('#' + formId +' *').filter(':input').each(function(){
	    $(this).removeClass("is-invalid");
	});
}

function closeButton(btnId){
	$('#' + btnId).modal('hide');
	$('#' + btnId).remove();
}


function cleanSpan(obj){
$("#"+ obj).removeClass('badge bgc-red-50 c-red-700 p-10 lh-0 badge-pill');
var msg = '';
$("#"+ obj).html(msg);
}

//Client side validation
function validateField(obj, msg)
{
  if(!obj.checkValidity())
  {
	 $("#" + $(obj).attr('id')+"_span").html(msg);
	 $("#" + $(obj).attr('id')+"_span").addClass("badge bgc-red-50 c-red-700 p-10 lh-0 badge-pill");
  } else {
  $("#" + $(obj).attr('id')+"_span").text('');
	 $("#" + $(obj).attr('id')).removeClass("badge bgc-red-50 c-red-700 p-10 lh-0 badge-pill");
  }
}

///funcion para cerrar los modales
function closeModalButton(modalId, nameForm){
	//se limpia posibles mensajes error de span
	$('#'+nameForm+' *').filter('span').each(function(){
		cleanSpan($(this).attr('id'));
	});
		//se limpia valores del formulario
	$('#' + modalId).modal('hide');	
}