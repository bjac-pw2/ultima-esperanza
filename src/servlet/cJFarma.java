package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class cJFarma extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			
			String option=req.getParameter("option");
			HttpSession sesion= req.getSession();
			String tipo = (String) sesion.getAttribute("tipo");
			
			if(tipo.equalsIgnoreCase("Farmaceutico")){
				
				if(option.equals("1")){
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/indexFarmaceutico.jsp");
					rd.forward(req, resp);
				}
				
				if(option.equals("2")){
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/listarMedicamento.jsp");
					rd.forward(req, resp);
				}
				
				if(option.equals("14")){
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/medicamento.jsp");
					rd.forward(req, resp);
				}
				if(option.equals("10")){
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/agregarStock.jsp");
					rd.forward(req, resp);
				}
				
			}else{
				
				if(tipo.equalsIgnoreCase("Medico")){
					resp.sendRedirect("cJMedic?option=6");
				}
				if(tipo.equalsIgnoreCase("Paciente")){
					resp.sendRedirect("cJPacient?option=1");
				}
				if(tipo.equalsIgnoreCase("Administrador")){
					resp.sendRedirect("cJAdmin?option=7");
				}
				if(tipo!=null){
					resp.sendRedirect("cJAdmin?option=7");
				}
			}
			
		}catch (ServletException e) {
			System.out.println(e.getMessage());
		}
	}
}