package cr.ac.cenfotec.proyecto.objetos;

public class Departamento {
	private String codigo;
	private String nombre;
	private String descripcion;
	private String estado;
	
	public Departamento() {
		
	}
	
	public Departamento(String codigo, String nombre, String descripcion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Departamento(String codigo, String nombre, String descripcion, String estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	public Departamento(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Departamento: \nCodigo: " + codigo + "\nNombre: " + nombre + "\nDescripcion: " + descripcion + "\n";
	}
	
}
