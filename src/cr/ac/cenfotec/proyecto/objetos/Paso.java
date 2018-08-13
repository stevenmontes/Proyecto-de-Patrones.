package cr.ac.cenfotec.proyecto.objetos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Paso {
	private int id;
	private String codigo;
	private String nombre;
	private String descripcion;
	private char respuesta;
	private String estado;
	private Empleado encargado;
	private String fechaInicio;
	private String fechaFin;
	private int numeroOrden;
	
	public Paso () {
		
	}
	
	public Paso(String codigo, String nombre, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getRespuesta() {
		return respuesta;
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

	public String getEstado() {
		return estado;
	}

	public void activar () {
		this.estado = "En proceso";
	}
	
	public void desactivar() {
		this.estado = "Inactivo";
	}
	
	public void completar() {
		this.estado = "Completado";
	}

	public Empleado getEncargado() {
		return encargado;
	}

	public void setEncargado(Empleado encargado) {
		this.encargado = encargado;
	}
	
	public void iniciarFecha() {
		Date hoy = new Date();
		DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
		this.fechaInicio = formatoFecha.format(hoy);
	}
	
	public void finalizarFecha () {
		Date fin = new Date();
		DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
		this.fechaFin = formatoFecha.format(fin);
	}
	
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}
	
	public String getFechaFin() {
		return fechaFin;
	}

	public void setRespuesta(char respuesta) {
		this.respuesta = respuesta;
	}

	public int getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	@Override
	public String toString() {
		StringBuilder contenido = new StringBuilder();
		contenido.append("----------------------------------------- \n");
		contenido.append("Codigo: " + getCodigo() + "\n");
		contenido.append("Nombre: " + getNombre() + "\n");
		contenido.append("Descripcion: " + getDescripcion() + "\n");
		contenido.append("Numero de orden: " + getNumeroOrden() + "\n");
		contenido.append("----------------------------------------- \n");
		return contenido.toString();
	}
	
	
}
