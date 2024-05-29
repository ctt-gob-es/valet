<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Redirecci�n</title>
    <script type="text/javascript" defer="defer">
  
		function submitRedirectFormAction() {
			 setTimeout(function() {
                document.getElementById('redirectForm').submit();
            }, 2000); // A�ade un retraso de 2 segundo
		}
		
		// Aseg�rate de que la funci�n se ejecute cuando la p�gina haya cargado
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
