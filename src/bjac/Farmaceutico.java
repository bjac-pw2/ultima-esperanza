package bjac;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;
@PersistenceCapable
public class Farmaceutico{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private String dni;
	@Persistent
	private String nombre;
	@Persistent
	private String apellido;
	@Persistent
	private String genero;
	@Persistent
	private String email;
	@Persistent
	private String direccion;
	@Persistent
	private String telefono;
	

	public Farmaceutico(String dni, String nombre, String apellido, String genero,
			String email, String numcelular, String direccion) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.email = email;
		this.telefono = numcelular;
		this.direccion=direccion;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	@Override
	public String toString() {
		return dni + "\t" + nombre + "\t"+ apellido +"\t" + email + "\t" + telefono+ "\t" + direccion + "\t" + genero;
	}
}