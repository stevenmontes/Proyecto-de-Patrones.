package cr.ac.cenfotec.proyecto.objetos;

import java.util.ArrayList;

public class Tarea {
	private String codigo;
	private String nombre;
	private String descripcion;
	private String estado;
	private Departamento areaEncargada;
	private ArrayList<Paso> pasos;
	private int numeroOrden;

	public Tarea() {
		pasos = new ArrayList<>();
	}

	public Tarea(String codigo, String nombre, String descripcion, String estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.pasos = new ArrayList<>();
	}

	public Tarea(String codigo, String nombre, String descripcion, Departamento area, String estado,
			ArrayList<Paso> pasos, int numeroOrden) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.areaEncargada = area;
		this.estado = estado;
		this.pasos = new ArrayList<>();
		this.numeroOrden = numeroOrden;
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

	public void activar() {
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

	public int getNumeroOrden() {
		return numeroOrden;
	}

	@Override
	public String toString() {
		StringBuilder contenido = new StringBuilder();
		contenido.append("----------------------------------------- \n");
		contenido.append("Codigo: " + getCodigo() + "\n");
		contenido.append("Nombre: " + getNombre() + "\n");
		contenido.append("Descripcion: " + getDescripcion() + "\n");
		contenido.append("Estado: " + getEstado() + "\n");
		contenido.append("Area encargada: " + areaEncargada.getNombre() + "\n");
		contenido.append("Numero de orden: " + getNumeroOrden() + "\n");
		contenido.append("-----------------------------------------");
		return contenido.toString();
	}

	public static class TareaBuilder {

		private String nestedCodigo;
		private String nestedNombre;
		private String nestedDescripcion;
		private String nestedEstado;
		private Departamento nestedAreaEncargada;
		private ArrayList<Paso> nestedPasos;
		private int nestedNumeroOrden;

		public TareaBuilder(final String newNestedCodigo, final String newNestedNombre,
				final String newNestedDescripcion) {
			this.nestedCodigo = newNestedCodigo;
			this.nestedNombre = newNestedNombre;
			this.nestedDescripcion = newNestedDescripcion;
			this.nestedPasos = new ArrayList<>();

		}

		public TareaBuilder codigo(String newCodigo) {
			this.nestedCodigo = newCodigo;
			return this;
		}

		public TareaBuilder nombre(String newNombre) {
			this.nestedNombre = newNombre;
			return this;
		}

		public TareaBuilder descripcion(String newDescripcion) {
			this.nestedDescripcion = newDescripcion;
			return this;
		}

		public TareaBuilder estado(String newEstado) {
			this.nestedEstado = newEstado;
			return this;
		}

		public TareaBuilder areaEncargada(Departamento newAreaEncargada) {
			this.nestedAreaEncargada = newAreaEncargada;
			return this;
		}

		public TareaBuilder pasos() {
			this.nestedPasos = new ArrayList<>();
			return this;
		}

		public TareaBuilder numeroOrden(int numeroOrden) {
			this.nestedNumeroOrden = numeroOrden;
			return this;
		}

		public Tarea createTarea() {
			return new Tarea(nestedCodigo, nestedNombre, nestedDescripcion, nestedAreaEncargada, nestedEstado,
					nestedPasos, nestedNumeroOrden);

		}

	}

}
