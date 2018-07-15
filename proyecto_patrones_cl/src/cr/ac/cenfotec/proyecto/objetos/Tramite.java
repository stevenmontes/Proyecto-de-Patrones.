package cr.ac.cenfotec.proyecto.objetos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Tramite {
	private String codigo;
	private String nombre;
	private String fecha_inicio;
	private String fecha_fin;
	private String descripcion;
	private String estado;
	private ArrayList<Tarea> listaTareas;
		
	public Tramite () {
		listaTareas = new ArrayList<>();
	}	
	
	public Tramite(String codigo, String nombre, String descripcion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		listaTareas = new ArrayList<>();
	}
	
	public Tramite(String codigo, String nombre, String fecha_inicio, String fecha_fin, String descripcion, String estado ) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.descripcion = descripcion;
		this.estado = estado;
		listaTareas = new ArrayList<>();
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
		return this.nombre;
	}
	
	public void iniciarFecha() {
		Date hoy = new Date();
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		this.fecha_inicio = formatoFecha.format(hoy);
	}
	
	public String getFechaInicio() {
		return this.fecha_inicio;
	}
	
	public void finalizarFecha() {
		Date fin = new Date();
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		this.fecha_fin = formatoFecha.format(fin);
	}
	
	public String getFechaFin() {
		return this.fecha_fin;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
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
		return estado;
	}
	
	public ArrayList<Tarea> getlistaTareas() {
		return listaTareas;
	}

	public void setlistaTareas(ArrayList<Tarea> tareas) {
		this.listaTareas = tareas;
	}
	
	public void agregarTarea(Tarea nueva) {
		listaTareas.add(nueva);
	}
	
	public String toString() {
		return "----------------------------------------- \n" + 
				"Codigo: " + getCodigo() + "\n" + 
				"Nombre: " + getNombre() + "\n" +
				"Descripcion: " + getDescripcion() + "\n" + 
				"Fecha inicio: " + getFechaInicio() + "\n" + 
				"Fecha fin: " + getFechaFin() + "\n" +
				"Estado: " + getEstado() + "\n" + 
				"----------------------------------------- \n";
	}
	
}
