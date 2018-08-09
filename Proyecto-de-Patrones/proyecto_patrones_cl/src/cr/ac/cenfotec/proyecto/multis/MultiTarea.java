package cr.ac.cenfotec.proyecto.multis;

import java.sql.ResultSet;
import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.conexion.Conector;
import cr.ac.cenfotec.proyecto.objetos.Tarea;

public class MultiTarea {
	
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
	
	public ArrayList<String> obtenerTareasPorArea (String id_area) {
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
        		Tarea ex = new Tarea(rs.getString("codigo"), rs.getString("nombre"), 
        								 rs.getString("descripcion"), rs.getString("estado"));
        		lista.add(ex);
        	}

        } catch (Exception ex) {
        	
        }

        return lista;
	}
}
