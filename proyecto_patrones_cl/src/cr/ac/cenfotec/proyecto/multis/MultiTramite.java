package cr.ac.cenfotec.proyecto.multis;

import cr.ac.cenfotec.proyecto.objetos.Tarea;
import cr.ac.cenfotec.proyecto.objetos.Tarea.TareaBuilder;
import cr.ac.cenfotec.proyecto.objetos.Tramite;

import java.sql.ResultSet;
import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.conexion.Conector;

public class MultiTramite {
	public MultiTramite() {

	}

	public String registrarTramite(Tramite nuevoP) {
		String consulta = "{Call dbo.pa_registrar_proceso ('" + nuevoP.getCodigo() + "','" + nuevoP.getNombre() + "', '"
				+ nuevoP.getDescripcion() + "')}";
		String resultado;

		try {
			Conector.getConector().ejecutarSQL(consulta);
			resultado = "El proceso se registró correctamente en el sistema.";

		} catch (Exception ex) {
			resultado = "No se pudo registrar el proceso, intentelo de nuevo " + ex.getMessage();

		}

		return resultado;
	}

	public String modificarTramite(Tramite E) {
		String consulta = "{Call dbo.pa_modificar_proceso ('" + E.getCodigo() + "','" + E.getNombre() + "', '"
				+ E.getDescripcion() + "')}";
		String resultado;

		try {
			Conector.getConector().ejecutarSQL(consulta);
			resultado = "El proceso se modificó correctamente en el sistema.";

		} catch (Exception ex) {
			resultado = "No se pudo modificar el proceso, intentelo de nuevo " + ex.getMessage();

		}

		return resultado;
	}

	public ArrayList<Tramite> listarTramites() {
		ArrayList<Tramite> lista = new ArrayList<>();
		String consulta = "{Call dbo.pa_listar_procesos }";

		try {
			ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);

			while (rs.next()) {
				Tramite Ex = new Tramite(rs.getString("codigo"), rs.getString("nombre"), rs.getString("fecha_inicio"),
						rs.getString("fecha_fin"), rs.getString("descripcion"), rs.getString("estado"));
				lista.add(Ex);
			}

		} catch (Exception ex) {

		}

		return lista;
	}

	public ArrayList<Tramite> obtenerProcesosActivos() {
		ArrayList<Tramite> lista = new ArrayList<>();
		String consulta = "{Call dbo.pa_listarProcesosActivos }";

		try {
			ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);

			while (rs.next()) {
				Tramite Ex = new Tramite(rs.getString("codigo"), rs.getString("nombre"), rs.getString("fecha_inicio"),
						rs.getString("fecha_fin"), rs.getString("descripcion"), rs.getString("estado"));
				lista.add(Ex);
			}

		} catch (Exception ex) {

		}

		return lista;
	}

	public ArrayList<Tramite> obtenerProcesosCompletado() {
		ArrayList<Tramite> lista = new ArrayList<>();
		String consulta = "{Call dbo.pa_listarProcesosCompleto }";

		try {
			ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);

			while (rs.next()) {
				Tramite Ex = new Tramite(rs.getString("codigo"), rs.getString("nombre"), rs.getString("fecha_inicio"),
						rs.getString("fecha_fin"), rs.getString("descripcion"), rs.getString("estado"));
				lista.add(Ex);
			}

		} catch (Exception ex) {

		}

		return lista;
	}

	public ArrayList<String> obtenerCodigos() {
		String consulta = "{Call dbo.pa_obtener_codigos_procesos}";
		ArrayList<String> lista = new ArrayList<>();

		try {
			ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);

			while (rs.next()) {
				lista.add(rs.getString("codigo"));
			}

		} catch (Exception ex) {
		}

		return lista;
	}

	public ArrayList<Tarea> obtenerTareas(int idProceso) throws Exception {
		String consulta = "{Call dbo.pa_obtener_tareas_de_proceso ('" + idProceso + "')}";
		ArrayList<Tarea> lista = new ArrayList<>();

		try {
			ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);

			while (rs.next()) {
				TareaBuilder nueva = new Tarea.TareaBuilder(rs.getString("codigo"), rs.getString("nombre"),
						rs.getString("descripcion"));
				nueva.numeroOrden(Integer.parseInt(rs.getString("numero_orden"))).estado(rs.getString("estado"));
				Tarea tareaNueva = nueva.createTarea();
				lista.add(tareaNueva);
			}

		} catch (Exception ex) {
			throw ex;
		}

		return lista;
	}

	public String modificarEstado(String codigo, String firma) {
		String consulta = "{Call dbo.pa_modificarEstadoTramite ('" + codigo + "','" + firma + "')}";
		String resultado;

		try {
			Conector.getConector().ejecutarSQL(consulta);
			resultado = "El proceso se activó correctamente en el sistema.";

		} catch (Exception ex) {
			resultado = "El proceso se no se activo correctamente en el sistema, intentelo de nuevo " + ex.getMessage();
		}
		return resultado;
	}

}
