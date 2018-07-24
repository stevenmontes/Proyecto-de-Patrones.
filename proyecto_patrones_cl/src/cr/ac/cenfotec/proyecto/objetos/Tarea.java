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

	public Tarea(String codigo, String nombre, String descripcion, String estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.pasos = new ArrayList<>();
	}

	public Tarea(String codigo, String nombre, String descripcion, Departamento area, String estado,
			ArrayList<Paso> pasos) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.areaEncargada = area;
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

	@Override
	public String toString() {
		String s = " ";
		s += "Tarea: Codigo: " + codigo + " || Nombre: " + nombre + " || Descripcion: " + descripcion + "|| Estado:"
				+ estado + " || Area Encargada: " + areaEncargada.getNombre();
		s += "PASOS DE TAREA:";
		for (Paso p : pasos) {
			s += p.toString();
		}
		return s;
	}

	public static class TareaBuilder {

		private String nestedCodigo;
		private String nestedNombre;
		private String nestedDescripcion;
		private String nestedEstado;
		private Departamento nestedAreaEncargada;
		private ArrayList<Paso> nestedPasos;

		public TareaBuilder(final String newNestedCodigo, final String newNestedNombre,
				final String newNestedDescripcion) {
			this.nestedCodigo = newNestedCodigo;
			this.nestedNombre = newNestedNombre;
			this.nestedDescripcion = newNestedDescripcion;
			this.nestedPasos = new ArrayList<>();

		}

		public TareaBuilder withCodigo(String newCodigo) {
			this.nestedCodigo = newCodigo;
			return this;
		}

		public TareaBuilder withNombre(String newNombre) {
			this.nestedNombre = newNombre;
			return this;
		}

		public TareaBuilder withDescripcion(String newDescripcion) {
			this.nestedDescripcion = newDescripcion;
			return this;
		}

		public TareaBuilder withEstado(String newEstado) {
			this.nestedEstado = newEstado;
			return this;
		}

		public TareaBuilder withAreaEncargada(Departamento newAreaEncargada) {
			this.nestedAreaEncargada = newAreaEncargada;
			return this;
		}

		public TareaBuilder withPasos() {
			this.nestedPasos = new ArrayList<>();
			return this;
		}

		public Tarea createTarea() {
			return new Tarea(nestedCodigo, nestedNombre, nestedDescripcion, nestedAreaEncargada, nestedEstado,
					nestedPasos);

		}

	}

}
