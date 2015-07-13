<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List"%>
    <%@ page import="bjac.Cita;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
@SuppressWarnings("unchecked")
List<Cita> citas = (List<Cita>)request.getAttribute("citas");
%>
</head>
<body>
	<div class="tabla">
		<div class="tituloTabla">Mis citas
		</div>
		<div class="cabeza">
			<div class="cell">DNI del Usuario</div>
			<div class="cell">Fecha</div>
			<div class="cell">Hora</div>
			<div class="cell">Asunto</div>
		</div>
		<%for( Cita p : citas ) {%>
			<div class="row">
				<div class="cell"><p><%= p.getDniPaciente() %></p></div>
				<div class="cell"><p><%= p.getfecha() %></p></div>
				<div class="cell"><p><%= p.getHora() %></p></div>
				<div class="cell"><p><%= p.getAsunto() %></p></div>
			</div>
		<%}%>
	</div>	
</body>
</html>