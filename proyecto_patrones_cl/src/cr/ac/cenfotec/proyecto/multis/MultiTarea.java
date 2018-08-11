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
	
	public String registrarTarea (Tarea E, String proceso) {
        String consulta = "{Call dbo.pa_registrar_tarea ('" + E.getCodigo() + "','" + E.getNombre() + "', '" + E.getDescripcion() + "', '" + E.getAreaEncargada().getCodigo() +"', '"+ proceso +"')}";
        String resultado;

        try {
                Conector.getConector().ejecutarSQL(consulta);
                resultado = "La tarea se registró correctamente en el sistema.";

        } catch (Exception ex) {
                resultado = "No se pudo registrar la tarea, intentelo de nuevo ";

        }

        return resultado;
	}
	
	public String modificarTarea (Tarea E) {
        String consulta = "{Call dbo.pa_modificar_tarea ('" + E.getCodigo() + "','" + E.getNombre() + "', '" + E.getDescripcion() + "', '" + E.getAreaEncargada().getCodigo() +"')}";
        String resultado;

        try {
                Conector.getConector().ejecutarSQL(consulta);
                resultado = "La tarea se modifico correctamente en el sistema.";

        } catch (Exception ex) {
                resultado = "No se pudo modificar la tarea, intentelo de nuevo ";

        }

        return resultado;
	}
	
	public ArrayList<String> obtenerCodigos(){
        String consulta = "{Call dbo.pa_obtener_codigos_tareas}";
        ArrayList<String> lista = new ArrayList<>();

        try {
                ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);
                
                while(rs.next()) {
                	lista.add(rs.getString("codigo"));
                }

        } catch (Exception ex) {
        }

        return lista;
	}
	
	public ArrayList<Tarea> obtenerTareasPorArea(String idArea) throws Exception{
	    String consulta = "{Call dbo.pa_obtener_tareas_por_area ('"+idArea+"')}";
		ArrayList<Tarea> listaTareas = new ArrayList<>();

	        try {
	                ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);
	                
	                while(rs.next()) {
	                	TareaBuilder nueva = new Tarea.TareaBuilder(rs.getString("codigo"),
	                			rs.getString("nombre"), rs.getString("descripcion"));
	                	nueva.withEstado(rs.getString("estado"));
	                	Tarea nuevaT = nueva.createTarea();
	                	listaTareas.add(nuevaT);
	                }

	        } catch (Exception ex) {
	        	throw ex;
	        }

	        return listaTareas;
	}
	
	public ArrayList<String> obtenerCodigosTareasPorArea (String id_area) {
        String consulta = "{Call dbo.pa_obtener_codigo_tarea_por_area ('"+id_area+"')}";
        ArrayList<String> relt = new ArrayList<>();

        try {
                ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);
                
                while(rs.next()) {
                	relt.add(rs.getString("codigo"));
                }

        } catch (Exception ex) {
        }

        return relt;
	}
	
	public ArrayList<Tarea> listarTareas(String codigo){
		ArrayList<Tarea> lista = new ArrayList<>();
        String consulta = "{Call dbo.pa_listar_tareas ('" + codigo + "')}";

        try {
        	ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);
        	
        	while(rs.next()) {
        		Departamento areaEncargada = fabrica.crearDepartamento(rs.getString("codA"),
        				rs.getString("area"), rs.getString("desA")); 
        		Tarea ex = new Tarea(rs.getString("codigo"), rs.getString("nombre"), 
        								 rs.getString("descripcion"), rs.getString("estado"));
        		ex.setAreaEncargada(areaEncargada);
        		lista.add(ex);
        	}

        } catch (Exception ex) {
        	
        }

        return lista;
	}
}
