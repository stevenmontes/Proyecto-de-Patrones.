package cr.ac.cenfotec.proyecto.multis;

import java.sql.ResultSet;
import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.conexion.Conector;
import cr.ac.cenfotec.proyecto.objetos.Departamento;

public class MultiDepartamento {
	public MultiDepartamento() {
		
	}
	
	public String registrarDepartamento(Departamento E) {
        String consulta = "{Call dbo.pa_registrar_departamento ('" + E.getNombre()+ "','" + E.getCodigo() + "', '" + E.getDescripcion() + "')}";
        String resultado;

        try {
                Conector.getConector().ejecutarSQL(consulta);
                resultado = "El departamento se registró correctamente en el sistema.";

        } catch (Exception ex) {
                resultado = "No se pudo registrar el departamento, intentelo de nuevo " + ex.getMessage();

        }

        return resultado;
	}
	
	public String modificarDepartamento(Departamento E) {
        String consulta = "{Call dbo.pa_modificar_departamento ('" + E.getNombre()+ "','" + E.getCodigo() + "', '" + E.getDescripcion() + "')}";
        String resultado;

        try {
                Conector.getConector().ejecutarSQL(consulta);
                resultado = "El departamento se modifico correctamente en el sistema.";

        } catch (Exception ex) {
                resultado = "No se pudo modificar el departamento, intentelo de nuevo " + ex.getMessage();

        }

        return resultado;
	}
	
	public ArrayList<String> obtenerCodigos () {
        String consulta = "{Call dbo.pa_obtener_codigos_area_funcional}";
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
}
