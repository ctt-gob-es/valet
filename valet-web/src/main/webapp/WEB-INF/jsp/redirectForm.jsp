<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Redirección</title>
    <script type="text/javascript" defer="defer">
  
		function submitRedirectFormAction() {
			 setTimeout(function() {
                document.getElementById('redirectForm').submit();
            }, 2000); // Añade un retraso de 2 segundo
		}
		
		// Asegúrate de que la función se ejecute cuando la página haya cargado
        window.onload = function() {
            submitRedirectFormAction();
        };

    </script>
</head>
<body>
    <form id="redirectForm" name="redirectForm" action="${urlRedireccion}" method="post">
        <h1>Redirigiendo a Cl@ve...</h1>
        <input type="hidden" id="relayState" name="RelayState" value="${RelayState}">
        <input type="hidden" id="SAMLRequest" name="SAMLRequest" value="${peticionSAML}"/>
    </form>
    
</body>
</html>
