package cr.ac.cenfotec.proyecto.controlador;

import cr.ac.cenfotec.proyecto.multis.*;
import cr.ac.cenfotec.proyecto.objetos.*;

public class Fabrica {
	public Fabrica() {
		
	}
	
	public Departamento crearDepartamento() {
		return new Departamento();
	}
	
	public Departamento crearDepartamento(String codigo) {
		return new Departamento(codigo);
	}
	
	public Departamento crearDepartamento(String codigo, String nombre, String descripcion) {
		return new Departamento(codigo, nombre, descripcion);
	}
	
	public Departamento crearDepartamento(String codigo, String nombre, String descripcion, String estado) {
		return new Departamento(codigo, nombre, descripcion, estado);
	}
	
	public Empleado crearEmpleado() {
		return new Empleado();
	}
	
	public Empleado crearEmpleado(String cedula, String p_nombre, String s_nombre, String p_apellido, String s_apellido, String correo, String usuario, String clave, String rol) {
		return new Empleado(cedula, p_nombre, s_nombre, p_apellido, s_apellido, correo, usuario, clave, rol);
	}
	
	public Paso crearPaso() {
		return new Paso();
	}
	
	public Paso crearPaso(String codigo, String nombre, String descripcion) {
		return new Paso(codigo, nombre, descripcion);
	}
	
	public Tarea crearTarea() {
		return new Tarea();
	}
	
	public Tramite crearTramite() {
		return new Tramite();
	}
	
	public Tramite crearTramite(String codigo, String nombre, String descripcion) {
		return new Tramite(codigo, nombre, descripcion);
	}
	
	public Tramite crearTramite(String codigo, String nombre, String fecha_inicio, String fecha_fin, String descripcion, String estado) {
		return new Tramite(codigo, nombre, fecha_inicio, fecha_fin, descripcion, estado);
	}
	
	public MultiTramite crearMultiTramite() {
		return new MultiTramite();
	}
	
	public MultiTarea crearMultiTarea() {
		return new MultiTarea();
	}
	
	public MultiPaso crearMultiPaso() {
		return new MultiPaso();
	}
	
	public MultiDepartamento crearMultiDepartamento() {
		return new MultiDepartamento();
	}
	
	public MultiEmpleado crearMultiEmpleado() {
		return new MultiEmpleado();
	}
	
	
}
