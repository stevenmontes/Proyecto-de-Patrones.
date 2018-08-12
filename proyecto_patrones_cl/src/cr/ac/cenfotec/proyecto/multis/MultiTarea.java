package cr.ac.cenfotec.proyecto.multis;

import java.sql.ResultSet;
import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.conexion.Conector;
import cr.ac.cenfotec.proyecto.controlador.Fabrica;
import cr.ac.cenfotec.proyecto.objetos.Departamento;
import cr.ac.cenfotec.proyecto.objetos.Tarea;
import cr.ac.cenfotec.proyecto.objetos.Tarea.TareaBuilder;

public class MultiTarea {

	private Fabrica fabrica = new Fabrica();

	public MultiTarea() {

	}

	public String registrarTarea(Tarea nuevo, String proceso) {
		String consulta = "{Call dbo.pa_registrar_tarea ('" + nuevo.getCodigo() + "','" + nuevo.getNombre() + "', '"
				+ nuevo.getDescripcion() + "', '" + nuevo.getAreaEncargada().getCodigo() + "', '" + proceso + "',"
				+ nuevo.getNumeroOrden() + ")}";
		String resultado;

		try {
			Conector.getConector().ejecutarSQL(consulta);
			resultado = "La tarea se registr� correctamente en el sistema.";

		} catch (Exception ex) {
			resultado = "No se pudo registrar la tarea, intentelo de nuevo ";

		}

		return resultado;
	}

	public String modificarTarea(Tarea E) {
		String consulta = "{Call dbo.pa_modificar_tarea ('" + E.getCodigo() + "','" + E.getNombre() + "', '"
				+ E.getDescripcion() + "', '" + E.getAreaEncargada().getCodigo() + "')}";
		String resultado;

		try {
			Conector.getConector().ejecutarSQL(consulta);
			resultado = "La tarea se modifico correctamente en el sistema.";

		} catch (Exception ex) {
			resultado = "No se pudo modificar la tarea, intentelo de nuevo ";

		}

		return resultado;
	}

	public ArrayList<String> obtenerCodigos() {
		String consulta = "{Call dbo.pa_obtener_codigos_tareas}";
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

	public Tarea obtenerTareaPorArea(String idArea) throws Exception {
		String consulta = "{Call dbo.pa_obtener_tareas_por_area ('" + idArea + "')}";
		Tarea nuevaT = fabrica.crearTarea();

		try {
			ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);

			while (rs.next()) {
				TareaBuilder nueva = new Tarea.TareaBuilder(rs.getString("codigo"), rs.getString("nombre"),
						rs.getString("descripcion"));
				nueva.estado(rs.getString("estado")).numeroOrden(Integer.parseInt(rs.getString("numero_orden")));
				nuevaT = nueva.createTarea();
			}

		} catch (Exception ex) {
			throw ex;
		}

		return nuevaT;
	}

	public ArrayList<String> obtenerCodigosTareasPorArea(String id_area) {
		String consulta = "{Call dbo.pa_obtener_codigo_tarea_por_area ('" + id_area + "')}";
		ArrayList<String> relt = new ArrayList<>();

		try {
			ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);

			while (rs.next()) {
				relt.add(rs.getString("codigo"));
			}

		} catch (Exception ex) {
		}

		return relt;
	}

	public ArrayList<Tarea> listarTareas(String codigo) {
		ArrayList<Tarea> lista = new ArrayList<>();
		String consulta = "{Call dbo.pa_listar_tareas ('" + codigo + "')}";

		try {
			ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);

			while (rs.next()) {
				Departamento areaEncargada = fabrica.crearDepartamento(rs.getString("codA"), rs.getString("area"),
						rs.getString("desA"));
				TareaBuilder nuevo = new Tarea.TareaBuilder(rs.getString("codigo"), rs.getString("nombre"),
						rs.getString("descripcion"));
				nuevo.estado(rs.getString("estado")).areaEncargada(areaEncargada)
						.numeroOrden(Integer.parseInt(rs.getString("numero_orden")));
				Tarea ex = nuevo.createTarea();
				lista.add(ex);
			}

		} catch (Exception ex) {

		}

		return lista;
	}

	public String registrarEstadoTarea(String codigo, String estado) {
		String consulta = "{Call dbo.pa_registrar_estado_tarea ('" + codigo + "','" + estado + "')}";

		try {
			Conector.getConector().ejecutarSQL(consulta);
			return "El estado de la tarea se registro exitosamente";
		} catch (Exception ex) {
			return "No se pudo modificar el estado de la tarea";
		}
	}
	
	public int obtenerProceso(String codTarea) throws Exception {
		String consulta = "{Call dbo.pa_obtener_proceso ('" + codTarea + "')}";
		int idProceso = 0;

		try {
			ResultSet conexion = Conector.getConector().ejecutarSQL(consulta, true);
			
			while(conexion.next()) {
				idProceso = Integer.parseInt(conexion.getString("id_proceso"));
			}

		} catch (Exception ex) {
			throw ex;
		}
		
		return idProceso;
	}
}
