package cr.ac.cenfotec.proyecto.controlador;

import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.multis.*;
import cr.ac.cenfotec.proyecto.objetos.*;
import cr.ac.cenfotec.proyecto.objetos.Tarea.TareaBuilder;

public class Controlador {
	static Fabrica fabrica = new Fabrica();
	static MultiEmpleado empleado = fabrica.crearMultiEmpleado();
	static MultiTramite sistema = fabrica.crearMultiTramite();
	static MultiTarea tarea = fabrica.crearMultiTarea();
	static MultiDepartamento area_funcional = fabrica.crearMultiDepartamento();
	static MultiPaso pasos = fabrica.crearMultiPaso();

	public Controlador() {

	}

	public String[] iniciarSesion(String usuario, String clave) {
		return empleado.iniciarSesion(usuario, clave);
	}

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

	public String registrarTramite(String codigo, String nombre, String descripcion) {
		Tramite proceso = fabrica.crearTramite(codigo, nombre, descripcion);
		return sistema.registrarTramite(proceso);
	}

	public String modificarTramite(String codigo, String nombre, String descripcion) {
		Tramite proceso = fabrica.crearTramite(codigo, nombre, descripcion);
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

	public String[] listarProcesosActivos() {
		ArrayList<Tramite> lisPA = sistema.obtenerProcesosActivos();
		String[] listaPA = new String[lisPA.size()];
		for (int i = 0; i < lisPA.size(); i++) {
			listaPA[i] = lisPA.get(i).toString();
		}
		return listaPA;
	}

	public String[] listarProcesosCompletos() {
		ArrayList<Tramite> lisPC = sistema.obtenerProcesosCompletado();
		String[] listaPC = new String[lisPC.size()];
		for (int i = 0; i < lisPC.size(); i++) {
			listaPC[i] = lisPC.get(i).toString();
		}
		return listaPC;
	}

	public String registrarTarea(String codigo, String nombre, String descripcion, String dep, String pro) {
		Departamento area = fabrica.crearDepartamento(dep);

		Tarea as;
		TareaBuilder builder = new Tarea.TareaBuilder(codigo, nombre, descripcion);
		builder = builder.withAreaEncargada(area).withPasos().withEstado("en proceso");
		as = builder.createTarea();

		return tarea.registrarTarea(as, pro);
	}

	public String modificarTarea(String codigo, String nombre, String descripcion, String dep) {

		Departamento area = fabrica.crearDepartamento(dep);

		Tarea as;
		TareaBuilder builder = new Tarea.TareaBuilder(codigo, nombre, descripcion);
		builder = builder.withAreaEncargada(area).withPasos().withEstado("en proceso");
		as = builder.createTarea();

		return tarea.modificarTarea(as);
	}

	public String[] listarTareas(String codigo) {
		ArrayList<Tarea> listaTareas = tarea.listarTareas(codigo);
		String[] listString = new String[listaTareas.size()];

		for (int i = 0; i < listaTareas.size(); i++) {
			listString[i] = listaTareas.get(i).toString();
		}

		return listString;
	}

	public String registrarPaso(String codigo, String nombre, String descripcion, String codTarea) {
		Paso pasoNuevo = fabrica.crearPaso(codigo, nombre, descripcion);
		return pasos.registrarPaso(pasoNuevo, codTarea);
	}

	public String modificarPaso(String codigo, String nombre, String descripcion) {
		Paso pasoNuevo = fabrica.crearPaso(codigo, nombre, descripcion);
		return pasos.modificarPaso(pasoNuevo);
	}

	public String[] listarPaso(String codTarea) {
		ArrayList<Paso> listPasos = pasos.listarPasos(codTarea);
		String[] listString = new String[listPasos.size()];

		for (int i = 0; i < listPasos.size(); i++) {
			listString[i] = listPasos.get(i).toString();
		}

		return listString;
	}

	public String registrarEmpleado(String ced, String nom1, String nom2, String ape1, String ape2, String correo,
			String nomU, String clave, String rol, String codArea) {
		Departamento area = fabrica.crearDepartamento(codArea);
		Empleado usuario = fabrica.crearEmpleado(ced, nom1, nom2, ape1, ape2, correo, nomU, clave, rol);
		usuario.setAreaFuncional(area);
		return empleado.registrarEmpleado(usuario);
	}

	public String modificarEmpleado(String ced, String nom1, String nom2, String ape1, String ape2, String correo,
			String nomU, String clave, String rol, String codArea) {
		Departamento area = fabrica.crearDepartamento(codArea);
		Empleado usuario = fabrica.crearEmpleado(ced, nom1, nom2, ape1, ape2, correo, nomU, clave, rol);
		usuario.setAreaFuncional(area);
		return empleado.modificarEmpleado(usuario);
	}

	public String[] listarEmpleado() throws Exception {
		ArrayList<Empleado> listEmpleado = empleado.listarTodosEmpleados();
		String[] infoEmpleados = new String[listEmpleado.size()];

		for (int i = 0; i < listEmpleado.size(); i++) {
			infoEmpleados[i] = listEmpleado.get(i).toString();
		}

		return infoEmpleados;
	}

	public String registrarArea(String codigo, String nombre, String descripcion) {
		Departamento E = fabrica.crearDepartamento(codigo, nombre, descripcion);
		return area_funcional.registrarDepartamento(E);
	}

	public String modificarArea(String codigo, String nombre, String descripcion) {
		Departamento E = fabrica.crearDepartamento(codigo, nombre, descripcion);
		return area_funcional.modificarDepartamento(E);
	}

	public String[] listarAreas() {
		ArrayList<Departamento> areas = area_funcional.listarAreas();

		String[] infoAreas = new String[areas.size()];
		for (int i = 0; i < areas.size(); i++) {
			infoAreas[i] = areas.get(i).toString();
		}
		return infoAreas;
	}

	public String modificarEstadoDepartamento(String codigo) {
		return area_funcional.modificarEstado(codigo);

	}

	public ArrayList<String> obtenerNombresPasos(String id_area) {
		ArrayList<String> codigoTareas = tarea.obtenerTareasPorArea(id_area);
		ArrayList<Paso> listaPasos;
		ArrayList<String> nombrePasos = new ArrayList<>();
		String codigoTarea;

		for (int indTarea = 0; indTarea < codigoTareas.size(); indTarea++) {
			codigoTarea = codigoTareas.get(indTarea);
			listaPasos = pasos.listarPasos(codigoTarea);

			for (int indPaso = 0; indPaso < listaPasos.size(); indPaso++) {
				nombrePasos.add(listaPasos.get(indPaso).getNombre());
			}
		}

		return nombrePasos;
	}

}
