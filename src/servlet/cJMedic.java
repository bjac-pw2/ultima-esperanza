package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class cJMedic extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String option=req.getParameter("option");

			if(option.equals("1")){
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/indexMedico.jsp");
				rd.forward(req, resp);
			}

			if(option.equals("18")){
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/modPassword.jsp");
				rd.forward(req, resp);
			}

		}catch (ServletException e) {
			System.out.println(e.getMessage());
		}
	}
}