package servlet;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bjac.*;

@SuppressWarnings("serial")
public class listaPaciente extends HttpServlet{
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Paciente.class);
		List<Paciente> pacientes = (List<Paciente>) q.execute();
		req.setAttribute("pacientes", pacientes);
		for(Paciente f:pacientes){
			System.out.println(f);
		}
		RequestDispatcher rd = req.getRequestDispatcher("controlJSP?option=3");
		rd.forward(req, resp);
		
	}
}