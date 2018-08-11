package cr.ac.cenfotec.proyecto.multis;

import java.sql.ResultSet;
import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.conexion.Conector;
import cr.ac.cenfotec.proyecto.objetos.Paso;

public class MultiPaso {

	public MultiPaso() {
		
	}
	
	public String registrarPaso(Paso nuevoPaso, String codTarea) {
        String consulta = "{Call dbo.pa_registrar_paso ('" + nuevoPaso.getCodigo() +
        		"','" + nuevoPaso.getNombre() + "','" + nuevoPaso.getDescripcion() +
        		"','" + codTarea + "','" + nuevoPaso.getNumeroOrden() + "')}";
        String resultado;

        try {
                Conector.getConector().ejecutarSQL(consulta);
                resultado = "El paso se registró correctamente en el sistema.";

        } catch (Exception error) {
                resultado = "No se pudo registrar el paso, intentelo de nuevo";

        }

        return resultado;
	}
	
	public String modificarPaso(String consulta) {
        String resultado;

        try {
                Conector.getConector().ejecutarSQL(consulta);
                resultado = "El paso se modifico correctamente en el sistema.";

        } catch (Exception error) {
                resultado = "No se pudo modificar el paso, intentelo de nuevo";

        }

        return resultado;
	}
	
	public ArrayList<String> obtenerCodigos(){
        String consulta = "{Call dbo.pa_obtener_codigos_pasos}";
        ArrayList<String> lista = new ArrayList<>();

        try {
                ResultSet conexion = Conector.getConector().ejecutarSQL(consulta, true);
                
                while(conexion.next()) {
                	lista.add(conexion.getString("codigo"));
                }

        } catch (Exception error) {
        }

        return lista;
	}
	
	public ArrayList<Paso> obtenerInfoPasos(String idTarea){
        String consulta = "{Call dbo.pa_obtener_paso ('" + idTarea + "')}";
        ArrayList<Paso> lista = new ArrayList<>();

        try {
                ResultSet conexion = Conector.getConector().ejecutarSQL(consulta, true);
                
                while(conexion.next()) {                	
                	Paso nuevoPaso = new Paso();
                	nuevoPaso.setNombre(conexion.getString("nombre"));
                	nuevoPaso.setDescripcion(conexion.getString("descripcion"));
                	lista.add(nuevoPaso);
                }

        } catch (Exception error) {
        	lista = new ArrayList<>();
        }

        return lista;
	}

	public ArrayList<Paso> listarPasos(String codTarea) {
		ArrayList<Paso> listPasos = new ArrayList<>();
        String consulta = "{Call dbo.pa_listar_pasos ('" + codTarea + "')}";

        try {
        	ResultSet conexion = Conector.getConector().ejecutarSQL(consulta, true);
        	
        	while(conexion.next()) {
        		Paso nuevoPaso = new Paso();
        		nuevoPaso.setId(Integer.parseInt(conexion.getString("id")));
        		nuevoPaso.setCodigo(conexion.getString("codigo"));
        		nuevoPaso.setNombre(conexion.getString("nombre"));
        		nuevoPaso.setDescripcion(conexion.getString("descripcion"));
        		nuevoPaso.setFechaInicio(conexion.getString("fecha_inicio"));
        		nuevoPaso.setFechaFin(conexion.getString("fecha_fin"));
        		nuevoPaso.setEstado(conexion.getString("estado"));
        		nuevoPaso.setNumeroOrden(Integer.parseInt(conexion.getString("numero_orden")));
        		listPasos.add(nuevoPaso);
        	}

        } catch (Exception error) {
        }

        return listPasos;
	}
	
}
