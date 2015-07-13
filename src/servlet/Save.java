package servlet;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

import bjac.Cita;
@SuppressWarnings("serial")
public class Save extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String nombreMedico = req.getParameter("nombreMedico");
		String dniPaciente = req.getParameter("dniPaciente");
		String fecha = req.getParameter("fecha");
		String hora = req.getParameter("hora");
		String asunto = req.getParameter("asunto");
		
		Cita p = new Cita(nombreMedico,dniPaciente,fecha,hora,asunto);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(p);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/indexPaciente.jsp");
			rd.forward(req, resp);
		}catch(Exception e){
			System.out.println(e);
			resp.getWriter().println("Ocurrió un error, <a href='index.jsp'>vuelva a intentarlo</a>");
		}finally{
			pm.close();
		}
	}
}