package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class HoraDisponible extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fecha = req.getParameter("fecha");
		List<String> h = new ArrayList<String>();
		int mh=0;
		String hh="";
		int i=8;
		while(i<20){
			if(mh==0){
				hh = (i>9)?i+":00:00":"0"+i+":00:00";
				mh=1;
			}else{
				hh = (i>9)?i+":30:00":"0"+i+":30:00";
				mh=0;
				i++;
			}
			h.add(hh);
		}
		req.setAttribute("fecha", fecha);
		req.setAttribute("horasDisponibles", h);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/horasDisponibles.jsp");
		rd.forward(req, resp);
		/*
		PersistenceManager pm = PMF.get().getPersistenceManager();
		resp.setContentType("text/html");
		
		
		Query q = pm.newQuery(Cita.class);
		try{
			q.setFilter("fecha == fechaParam");
			q.declareParameters("String fechaParam");
			List<Cita> personas = (List<Cita>)q.execute(fecha);
			int temp=0;
			int mh=0;
			String hh="";
			int i=8;
			while(i<20){
				if(mh==0){
					hh = (i>9)?i+":00:00":"0"+i+":00:00";
					mh=1;
				}else{
					hh = (i>9)?i+":30:00":"0"+i+":30:00";
					mh=0;
					i++;
				}
				for(int j=0;j<personas.size();j++){
					if(personas.get(j).getHora().equals(hh)){
						temp=1;
					}
				}
				if(temp==0){
					h.add(hh);
				}
				temp=0;
			}
			//out.print(inn);
			req.setAttribute("fecha", fecha);
			req.setAttribute("horasDisponibles", h);
			RequestDispatcher rd = req.getRequestDispatcher("/horasDisponibles.jsp");
			rd.forward(req, resp);
		}catch(Exception e){
			
			
		}finally{
			 q.closeAll();
		}*/
		
	}
}