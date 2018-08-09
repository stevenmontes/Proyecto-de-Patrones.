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
                resultado = "El departamento se registro correctamente en el sistema.";

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

	public ArrayList<Departamento> listarAreas() {
		ArrayList<Departamento> listaAreas = new ArrayList<>();
        String consulta = "{Call dbo.pa_listar_areas_funcionales}";

        try {
        	ResultSet conexion = Conector.getConector().ejecutarSQL(consulta, true);
        	
        	while(conexion.next()) {
        		Departamento miArea = new Departamento();
        		miArea.setCodigo(conexion.getString("codigo"));
        		miArea.setNombre(conexion.getString("nombre"));
        		miArea.setDescripcion(conexion.getString("descripcion"));
        		
        		listaAreas.add(miArea);
        	}

        } catch (Exception error) {
 
        }

        return listaAreas;
	}

	public String modificarEstado(String codigo) {
		String estado = null, mensaje = null;
		
		try {
			ResultSet conexion = Conector.getConector().ejecutarSQL("SELECT * FROM tarea_funcional WHERE codigo = '" + codigo + "';", true);
			while (conexion.next()) {
				estado = conexion.getString("estado");
			}
		} catch (Exception e) {
			
		}
		
		if (estado == null) {
			return "\nNo existe ningún departamento con ese código";
		}
		
		if (estado.equals("0")) {
			try {
				Conector.getConector().ejecutarSQL("UPDATE tarea_funcional SET estado = 1 WHERE codigo = '" + codigo + "';");
				mensaje = "\nSe ha modificado el departamento de inactivo a activo";
			} catch (Exception e) {
				return e.getMessage();
			}
			
		}
		
		if (estado.equals("1")) {
			try {
				Conector.getConector().ejecutarSQL("UPDATE tarea_funcional SET estado = 0 WHERE codigo = '" + codigo + "';");
				mensaje = "\nSe ha modificado el departamento de activo a inactivo";
			} catch (Exception e) {
				return e.getMessage();
			}
			
		}
		
		return mensaje;
	}
}
