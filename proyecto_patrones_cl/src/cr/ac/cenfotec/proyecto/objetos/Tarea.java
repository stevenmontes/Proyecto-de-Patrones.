package cr.ac.cenfotec.proyecto.objetos;

import java.util.ArrayList;

public class Tarea {
	private String codigo;
	private String nombre;
	private String descripcion;
	private String estado;
	private Departamento areaEncargada;
	private ArrayList<Paso> pasos;
	
	public Tarea() {
		pasos = new ArrayList<>();
	}
	
	public Tarea(String codigo, String nombre, String descripcion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.pasos = new ArrayList<>();
	}
	
	public Tarea(String codigo, String nombre, String descripcion, String estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.pasos = new ArrayList<>();
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
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
	
	public String getEstado() {
		return this.estado;
	}

	public Departamento getAreaEncargada() {
		return areaEncargada;
	}

	public void setAreaEncargada(Departamento encargado) {
		this.areaEncargada = encargado;
	}

	public ArrayList<Paso> getPasos() {
		return pasos;
	}

	public void setPasos(ArrayList<Paso> pasos) {
		this.pasos = pasos;
	}
	
	
}
