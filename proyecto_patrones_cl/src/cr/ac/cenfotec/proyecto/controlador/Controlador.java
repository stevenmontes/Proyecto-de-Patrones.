	package cr.ac.cenfotec.proyecto.controlador;

import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.multis.*;
import cr.ac.cenfotec.proyecto.objetos.*;

public class Controlador {
	static MultiEmpleado empleado = new MultiEmpleado();
	static MultiTramite sistema = new MultiTramite();
	static MultiTarea tarea = new MultiTarea();
	static MultiDepartamento area_funcional = new MultiDepartamento();
	static MultiPaso pasos = new MultiPaso();

	public Controlador() {

	}

	public String[] iniciarSesion(String usuario, String clave) {
		return empleado.iniciarSesion(usuario, clave);
	}

	//Códigos
	public ArrayList<String> codidosEmpleados() {
		return empleado.obtenerCodigos();
	}

	public ArrayList<String> codidosTramites() {
		return sistema.obtenerCodigos();
	}

	public ArrayList<String> codidosTareas() {
		return tarea.obtenerCodigos();
	}

	public ArrayList<String> codidosAreas() {
		return area_funcional.obtenerCodigos();
	}

	public ArrayList<String> codidosPasos() {
		return pasos.obtenerCodigos();
	}

	public boolean validarCodigo(String codigo, ArrayList<String> EE) {
		boolean Ex = false;

		for (String var : EE) {
			if (var.equals(codigo)) {
				Ex = true;
				break;
			}
		}

		return Ex;
	}

	//Trámites
	public String registrarTramite(String codigo, String nombre, String descripcion) {
		Tramite proceso = new Tramite(codigo, nombre, descripcion);
		return sistema.registrarTramite(proceso);
	}

	public String modificarTramite(String codigo, String nombre, String descripcion) {
		Tramite proceso = new Tramite(codigo, nombre, descripcion);
		return sistema.modificarTramite(proceso);
	}

	public String[] listarTramite() {
		ArrayList<Tramite> listProcesos = sistema.listarTramites();
		String[] infoProcesos = new String[listProcesos.size()];

		for (int i = 0; i < listProcesos.size(); i++) {
			infoProcesos[i] = listProcesos.get(i).toString();
		}

		return infoProcesos;
	}

	//Tareas
	public String registrarTarea(String codigo, String nombre, String descripcion, String dep, String pro) {
		Departamento area = new Departamento(dep);
		Tarea as = new Tarea(codigo, nombre, descripcion);
		as.setAreaEncargada(area);
		return tarea.registrarTarea(as, pro);
	}

	public String modificarTarea(String codigo, String nombre, String descripcion, String dep) {
		Departamento area = new Departamento(dep);
		Tarea as = new Tarea(codigo, nombre, descripcion);
		as.setAreaEncargada(area);
		return tarea.modificarTarea(as);
	}

	public ArrayList<Tarea> listarTareas(String codigo) {
		ArrayList<Tarea> listaTareas = tarea.listarTareas(codigo);
		return listaTareas;
	}

	//Pasos
	public String registrarPaso(String codigo, String nombre, String descripcion, String codTarea) {
		Paso pasoNuevo = new Paso(codigo, nombre, descripcion);
		return pasos.registrarPaso(pasoNuevo, codTarea);
	}

	public String modificarPaso(String codigo, String nombre, String descripcion) {
		Paso pasoNuevo = new Paso(codigo, nombre, descripcion);
		return pasos.modificarPaso(pasoNuevo);
	}
	
	public String[] listarPaso(String codTarea) {
		ArrayList<Paso> listPasos = pasos.listarPasos(codTarea);
		String [] listString = new String[listPasos.size()];
		
		for(int i = 0; i < listPasos.size(); i++) {
			listString[i] = listPasos.get(i).toString();
		}
		
		return listString;
	}

	//Empleados
	public String registrarEmpleado(String ced, String nom1, String nom2, String ape1, String ape2, String correo,
			String nomU, String clave, String rol, String codArea) {
		Departamento area = new Departamento(codArea);
		Empleado usuario = new Empleado(ced, nom1, nom2, ape1, ape2, correo, nomU, clave, rol);
		usuario.setAreaFuncional(area);
		return empleado.registrarEmpleado(usuario);
	}

	public String modificarEmpleado(String ced, String nom1, String nom2, String ape1, String ape2, String correo,
			String nomU, String clave, String rol, String codArea) {
		Departamento area = new Departamento(codArea);
		Empleado usuario = new Empleado(ced, nom1, nom2, ape1, ape2, correo, nomU, clave, rol);
		usuario.setAreaFuncional(area);
		return empleado.modificarEmpleado(usuario);
	}
	
	public String[] listarEmpleado() throws Exception{
		ArrayList<Empleado> listEmpleado = empleado.listarTodosEmpleados();
		String[] infoEmpleados = new String[listEmpleado.size()];
		
		for(int i = 0; i < listEmpleado.size(); i++) {
			infoEmpleados[i] = listEmpleado.get(i).toString();
		}
		
		return infoEmpleados;
	}

	//Áreas Funcionales
	public String registrarArea(String codigo, String nombre, String descripcion) {
		Departamento E = new Departamento(codigo, nombre, descripcion);
		return area_funcional.registrarDepartamento(E);
	}

	public String modificarArea(String codigo, String nombre, String descripcion) {
		Departamento E = new Departamento(codigo, nombre, descripcion);
		return area_funcional.modificarDepartamento(E);
	}
	
	public ArrayList<Departamento> listarAreas() {
		ArrayList<Departamento> areas = area_funcional.listarAreas();
		
		return areas;
	}
	
	public String modificarEstadoDepartamento(String codigo) {
		return area_funcional.modificarEstado(codigo);
		
	}
	

	public ArrayList<String> obtenerDescripcionPaso(String id_area) {
		ArrayList<String> idsTareas = tarea.obtenerIdTarea(id_area);
		ArrayList<Paso> listaPasos;
		ArrayList<String> nombrePasos = new ArrayList<>();
		String idTarea;

		for (int indTarea = 0; indTarea < idsTareas.size(); indTarea++) {
			idTarea = idsTareas.get(indTarea);
			listaPasos = pasos.obtenerInfoPasos(idTarea);

			for (int indPaso = 0; indPaso < listaPasos.size(); indPaso++) {
				nombrePasos.add(listaPasos.get(indPaso).getNombre());
			}
		}

		return nombrePasos;
	}

}
