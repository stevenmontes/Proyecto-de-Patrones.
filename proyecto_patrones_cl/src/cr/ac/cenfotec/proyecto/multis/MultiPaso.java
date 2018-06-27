package cr.ac.cenfotec.proyecto.multis;

import java.sql.ResultSet;
import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.conexion.Conector;
import cr.ac.cenfotec.proyecto.objetos.Paso;

public class MultiPaso {

	public MultiPaso() {
		
	}
	
	public String registrarPaso(Paso E, String codTarea) {
        String consulta = "{Call dbo.pa_registrar_paso ('" + E.getCodigo() + "','" + E.getNombre() + "','" + E.getDescripcion() + "', '" + codTarea + "')}";
        String resultado;

        try {
                Conector.getConector().ejecutarSQL(consulta);
                resultado = "El paso se registró correctamente en el sistema.";

        } catch (Exception ex) {
                resultado = "No se pudo registrar el paso, intentelo de nuevo " + ex.getMessage();

        }

        return resultado;
	}
	
	public String modificarPaso(Paso E) {
        String consulta = "{Call dbo.pa_modificar_paso ('" + E.getCodigo() + "', '" + E.getNombre() + "','" + E.getDescripcion() + "')}";
        String resultado;

        try {
                Conector.getConector().ejecutarSQL(consulta);
                resultado = "El paso se modifico correctamente en el sistema.";

        } catch (Exception ex) {
                resultado = "No se pudo modificar el paso, intentelo de nuevo " + ex.getMessage();

        }

        return resultado;
	}
	
	public ArrayList<String> obtenerCodigos(){
        String consulta = "{Call dbo.pa_obtener_codigos_pasos}";
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
	
	public ArrayList<Paso> obtenerInfoPasos(String idTarea){
        String consulta = "{Call dbo.pa_obtener_paso ('" + idTarea + "')}";
        ArrayList<Paso> lista = new ArrayList<>();

        try {
                ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);
                
                while(rs.next()) {                	
                	Paso np = new Paso();
                	np.setNombre(rs.getString("nombre"));
                	np.setDescripcion(rs.getString("descripcion"));
                	lista.add(np);
                }

        } catch (Exception ex) {
        	lista = new ArrayList<>();
        }

        return lista;
	}
}
