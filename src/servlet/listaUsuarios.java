package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bjac.*;

@SuppressWarnings("serial")
public class listaUsuarios extends HttpServlet{
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		resp.getWriter().print("<!DOCTYPE html>"
								+ "<html>"
								+"<head>"
								+ "<title>Lista de Usuarios</title>"
								+"<link rel='stylesheet' href='style.css'>"
								+ "</head>");
		
		
		
		out.println("<body>"
				 +"<h1>CLINICA NUEVA ESPERANZA</h1>"
				 +"<ul class = 'index'>"
				 +"<li><a href='index.html'>INICIO</a>"
		 		 +"<li><a href='medicamento.jsp'>Agregar Medicamento</a>"
				 +"<li><a href='/listaMedicina'>Listar Medicamentos</a>"
				 + "<li><a href='paciente.jsp'>Agregar Paciente</a>"
				 +"<li><a href='/listaPaciente'>Listar Pacientes</a>"
				 +"<li><a href='medico.jsp'>Agregar Medico</a>"
				 +"<li><a href='/listaMedicos'>Listar Medico</a>"
				 +"<li><a href='receta.jsp'>Receta</a>"
				 +"<li><a href='examen.jsp'>Examen</a>"
				 +"<li><a href='resExamen.jsp'>Resultados del Examen</a>"
				 + "<li><a href='indexCita.html'>Cita</a>"
				 + "<li><a href='horario.html'>Horario</a>"
				 +"</ul>"
				 );
		
		try{
			
			out.println("<h1>Lista de Usuarios</h1><br>");
			Query q = pm.newQuery(Usuario.class);
			List<Usuario> usuarios = (List<Usuario>) q.execute();
			out.println("<ol>");
			for(Usuario a: usuarios){
				out.print("<li>");
				out.println(a+"<br>");
			}
			out.println("</ol>");
		}catch(Exception e){
		}finally{
			out.println("</body>"
					+ "</html>");
		}
	}
}