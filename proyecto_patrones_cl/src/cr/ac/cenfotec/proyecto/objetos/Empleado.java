package cr.ac.cenfotec.proyecto.objetos;

public class Empleado {
	private String cedula;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String correo;
	private String usuario;
	private String clave;
	private String rol;
	private Departamento areaFuncional;
	
	public Empleado() {
		
	}
	
	public Empleado(String cedula, String p_nombre, String s_nombre, String p_apellido, String s_apellido, String correo, String usuario, String clave, String rol) {
		this.cedula = cedula;
		this.primerNombre = p_nombre;
		this.segundoNombre = s_nombre;
		this.primerApellido = p_apellido;
		this.segundoApellido = s_apellido;
		this.correo = correo;
		this.usuario = usuario;
		this.clave = clave;
		this.rol = rol;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Departamento getAreaFuncional() {
		return areaFuncional;
	}

	public void setAreaFuncional(Departamento areaFuncional) {
		this.areaFuncional = areaFuncional;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String toString() {
		return "----------------------------------------- \n" + 
				"Cedula: " + getCedula() + "\n" +
				"Primer nombre: " + getPrimerNombre() + "\n" + 
				"Segundo nombre: " + getSegundoNombre() + "\n" +
				"Primer apellido: " + getPrimerApellido() + "\n" + 
				"Segundo apellido: " + getSegundoApellido() + "\n" + 
				"Correo: " + getCorreo() + "\n" + 
				"Usuario: " + getUsuario() + "\n" + 
				"Rol: " + getRol() + "\n" + 
				"Area funcional: " + getAreaFuncional().getNombre() + "\n" +
				"----------------------------------------- \n";
	}
	
	
}
