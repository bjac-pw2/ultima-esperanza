package servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.*;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import bjac.*;

@SuppressWarnings("serial")
public class regPaciente extends HttpServlet {
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
					resp.sendRedirect("controlJSP?option=2");
					return;
				}
			}
			q.closeAll();
			pm1.close();
			
			if(!comprobarDatos(
					DNI,
					req.getParameter("nombre") , 
					req.getParameter("apellidoPat")+" "+req.getParameter("apellidoMat"), 
					req.getParameter("email"), 
					req.getParameter("telefono"), 
					req.getParameter("genero"), 
					req.getParameter("peso"), 
					req.getParameter("talla"), 
					req.getParameter("tSangre") 
			)){
				resp.sendRedirect("index.jsp");
				return;
			}
			
			Paciente pac=new Paciente(
					DNI,
					req.getParameter("nombre") , 
					req.getParameter("apellidoPat")+" "+req.getParameter("apellidoMat"), 
					req.getParameter("email"), 
					req.getParameter("telefono"), 
					req.getParameter("direccion"), 
					req.getParameter("genero"), 
					Double.parseDouble(req.getParameter("peso")), 
					Double.parseDouble(req.getParameter("talla")), 
					req.getParameter("tSangre") 
			);
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.makePersistent(pac);
			
			String password=req.getParameter("nombre").substring(0, 1).toLowerCase()+req.getParameter("apellidoPat").toLowerCase();
			Usuario usu=new Usuario(DNI, password, "Paciente");
			pm.makePersistent(usu);
			//----------------Envio de correo
			Metodos.enviarCorreo(req.getParameter("nombre") , req.getParameter("dni") , req.getParameter("email") , password);
			//------------------
			resp.sendRedirect("listaPaciente");
			
		}catch(Exception e){
			resp.getWriter().println("Imposible Guardar");
			resp.getWriter().println(e.getMessage());
		}
	}
	
	protected boolean comprobarDatos(String DNI, String nombre, String apellido, String correo, String telefono, String genero, String peso, String talla, String tipoSangre){
		/**
		 * Aqui validamos la expresion regular del DNI
		 */
		Pattern patDNI=Pattern.compile("[0-9]{8}");
		Matcher matDNI=patDNI.matcher(DNI);
		
		/**
		 * Aqui validamos la expresion regular de los nombres y apellidos
		 */
		Pattern patNombres=Pattern.compile("([a-zA-ZáéíóúÁÉÍÓÚñÑ]+[\\s]?[a-zA-ZáéíóúÁÉÍÓÚñÑ]*)");
		Matcher matNombres=patNombres.matcher(nombre);
		Matcher matApellido=patNombres.matcher(apellido);
		
		/**
		 * Aqui validamos la expresion regular del DNI
		 */
		Pattern patCorreo=Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$");
		Matcher matCorreo=patCorreo.matcher(correo);
		
		/**
		 * Aqui validamos el telefono
		 */
		Pattern patTelefono=Pattern.compile("[0-9]{6}|[0-9]{9}");
		Matcher matTelefono=patTelefono.matcher(telefono);
		
		/**
		 * Aqui validamos el genero
		 */
		boolean gen=(genero.equals("Masculino") || genero.equals("Femenino"));
		
		/**
		 * Aqui validamos el peso y talla
		 */
		Pattern patDouble=Pattern.compile("[0-9]{1,3}(\\.)[\\d]{1,3}");
		Matcher matPeso=patDouble.matcher(peso);
		Matcher matTalla=patDouble.matcher(talla);
		
		/**
		 * Aqui validamos el tipo de sangre
		 */
		Pattern patSangre=Pattern.compile("(A|B|AB|O){1}(\\+|-){1}");
		Matcher matSangre=patSangre.matcher(tipoSangre);
		
		
		return(matDNI.matches() && 
				matNombres.matches() && 
				matApellido.matches() && 
				matCorreo.matches() && 
				matTelefono.matches() && 
				gen &&
				matPeso.matches() &&
				matTalla.matches() &&
				matSangre.matches()
				);
	}
}