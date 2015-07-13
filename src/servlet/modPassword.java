package servlet;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;
import bjac.*;

@SuppressWarnings("serial")
public class modPassword extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		try{

			String dni=req.getParameter("dni");
			String pActual=req.getParameter("pActual");
			String pNuevo=req.getParameter("pNuevo");
			
			PersistenceManager pm1 = PMF.get().getPersistenceManager();
			Query q = pm1.newQuery(Usuario.class);
			@SuppressWarnings("unchecked")
			List<Usuario> usuario = (List<Usuario>) q.execute();
			
			for(Usuario a: usuario){
				if(a.getDNI().equals(dni)){
					if(a.getPassword().equals(pActual)){
						a.setPassword(pNuevo);
						resp.sendRedirect("controlJSP?option=7");
						return;
					}
				}
			}
			q.closeAll();
			pm1.close();

			resp.sendRedirect("controlJSP?option=18");
			return;
			
		}catch(Exception e){
			resp.getWriter().println("Imposible Guardar");
			resp.getWriter().println(e.getMessage());
		}
	}
}