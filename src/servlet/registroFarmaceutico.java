package servlet;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import bjac.Farmaceutico;
import bjac.Metodos;
import bjac.Usuario;

@SuppressWarnings("serial")
public class registroFarmaceutico extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		try{
			
			String DNI=req.getParameter("dni");
			PersistenceManager pm1 = PMF.get().getPersistenceManager();
			Query q = pm1.newQuery(Usuario.class);
			@SuppressWarnings("unchecked")
			List<Usuario> usuario = (List<Usuario>) q.execute();
			
			for(Usuario a: usuario){
				if(a.getDNI().equalsIgnoreCase(DNI)){
					resp.sendRedirect("cJAdmin?option=7");
					return;
				}
			}
			q.closeAll();
			pm1.close();
			
			
			Farmaceutico farmac= new Farmaceutico(
					DNI, 
					req.getParameter("nombre"), 
					req.getParameter("apellidoPat")+" "+req.getParameter("apellidoMat"), 
					req.getParameter("genero"), 
					req.getParameter("email"), 
					req.getParameter("telefono"), 
					req.getParameter("direccion")
			);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.makePersistent(farmac);
			
			String password=req.getParameter("nombre").substring(0, 1).toLowerCase()+req.getParameter("apellidoPat").toLowerCase();
			Usuario usu=new Usuario(DNI, password, "Farmaceutico");
			pm.makePersistent(usu);
			//----------------Envio de correo
			Metodos.enviarCorreo(req.getParameter("nombre") , req.getParameter("dni") , req.getParameter("email") , password);
			//------------------
			resp.sendRedirect("listaFarmaceutico");
			
		}catch(Exception e){
			resp.getWriter().println("Imposible Guardar");
			resp.getWriter().println(e.getMessage());
		}
	}
}