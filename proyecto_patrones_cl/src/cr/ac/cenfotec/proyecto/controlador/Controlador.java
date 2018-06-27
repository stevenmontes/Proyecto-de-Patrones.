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
		Tramite proceso = new Tramite(codigo, nombre, descripcion);
		return sistema.registrarTramite(proceso);
	}

	public String modificarTramite(String codigo, String nombre, String descripcion) {
		Tramite proceso = new Tramite(codigo, nombre, descripcion);
		return sistema.modificarTramite(proceso);
	}

	public String[][] listarTramite() {
		ArrayList<Tramite> ALT = sistema.listarTramites();
		String[][] MT = new String[ALT.size()][6];

		for (int i = 0; i < ALT.size(); i++) {
			MT[i][0] = ALT.get(i).getCodigo();
			MT[i][1] = ALT.get(i).getNombre();
			MT[i][2] = ALT.get(i).getDescripcion();
			MT[i][3] = ALT.get(i).getFechaInicio();
			MT[i][4] = ALT.get(i).getFechaFin();
			MT[i][5] = ALT.get(i).getEstado();
		}

		return MT;
	}

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

	public String[][] listarTareas(String codigo) {
		ArrayList<Tarea> lt = tarea.listarTareas(codigo);
		String[][] mt = new String[lt.size()][4];
		
		for(int i = 0; i < mt.length; i++) {
			mt[i][0] = lt.get(i).getCodigo();
			mt[i][1] = lt.get(i).getNombre();
			mt[i][2] = lt.get(i).getDescripcion();
			mt[i][3] = lt.get(i).getEstado();
		}
		
		return mt;
	}

	public String registrarPaso(String codigo, String nombre, String descripcion, String codTarea) {
		Paso ps = new Paso(codigo, nombre, descripcion);
		return pasos.registrarPaso(ps, codTarea);
	}

	public String modificarPaso(String codigo, String nombre, String descripcion) {
		Paso ps = new Paso(codigo, nombre, descripcion);
		return pasos.modificarPaso(ps);
	}

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

	public String registrarArea(String codigo, String nombre, String descripcion) {
		Departamento E = new Departamento(codigo, nombre, descripcion);
		return area_funcional.registrarDepartamento(E);
	}

	public String modificarArea(String codigo, String nombre, String descripcion) {
		Departamento E = new Departamento(codigo, nombre, descripcion);
		return area_funcional.modificarDepartamento(E);
	}

	public ArrayList<String> obtenerDescripcionPaso(String id_area) {
		ArrayList<String> ids = tarea.obtenerIdTarea(id_area);
		ArrayList<Paso> lista;
		ArrayList<String> dePasos = new ArrayList<>();
		String id_tarea;

		for (int i = 0; i < ids.size(); i++) {
			id_tarea = ids.get(i);
			lista = pasos.obtenerInfoPasos(id_tarea);

			for (int j = 0; j < lista.size(); j++) {
				dePasos.add(lista.get(j).getNombre());
			}
		}

		return dePasos;
	}
}
