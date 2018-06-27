package cr.ac.cenfotec.proyecto.multis;

import java.sql.ResultSet;
import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.conexion.Conector;
import cr.ac.cenfotec.proyecto.objetos.Empleado;

public class MultiEmpleado {
	public MultiEmpleado() {
		
	}
	
	public String[] iniciarSesion (String usuario, String clave){
		String consulta = "{Call dbo.pa_iniciar_sesion ('" + usuario + "', '" + clave + "')}";
		String [] info = new String[5];
		
		try {
			ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);
			
			while(rs.next()) {
				info[0] = rs.getString("cedula");
				info[1] = rs.getString("primer_nombre");
				info[2] = rs.getString("primer_apellido");
				info[3] = rs.getString("id_area_funcional");
				info[4] = "Se inicio sesion con exito";
			}
			
		} catch (Exception ex) {
			info[4] = "El nombre del usuario o clave no coinciden entre si.";
		}
		
		return info;
	}
	
	public String registrarEmpleado(Empleado E){
		String consulta = "{Call dbo.pa_registrar_empleado ('" + E.getCedula() + "', '" + E.getPrimerNombre() + "', '" + E.getSegundoNombre() + "', '"+E.getPrimerApellido()+"', '"+E.getSegundoApellido()+"', '"+E.getCorreo()+"', '"+E.getUsuario()+"', '"+E.getClave()+"', '"+E.getRol()+"', '"+E.getAreaFuncional().getCodigo()+"')}";
		String result;
		
		try {
			Conector.getConector().ejecutarSQL(consulta, true);
			result = "Se ha registrado exitosamente el empleado.";
		} catch (Exception ex) {
			result = "No se pudo registrar el empleado. Vuelve a intentarlo." + ex.getMessage();
		}
		
		return result;
	}
	
	public String modificarEmpleado(Empleado E) {
		String consulta = "{Call dbo.pa_modificar_empleado ('" + E.getCedula() + "', '" + E.getPrimerNombre() + "', '" + E.getSegundoNombre() + "', '"+E.getPrimerApellido()+"', '"+E.getSegundoApellido()+"', '"+E.getCorreo()+"', '"+E.getUsuario()+"', '"+E.getClave()+"', '"+E.getRol()+"', '"+E.getAreaFuncional()+"')}";
		String result;
		
		try {
			Conector.getConector().ejecutarSQL(consulta, true);
			result = "Se ha modificado exitosamente el empleado.";
		} catch (Exception ex) {
			result = "No se pudo modificar el empleado. Vuelve a intentarlo.";
		}
		
		return result;
	}
	
	public ArrayList<Empleado> listarTodosEmpleados() throws Exception{
		ArrayList<Empleado> lista = new ArrayList<>();
        String consulta = "{Call dbo.pa_listar_empleado }";

        try {
        	ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);
        	
        	while(rs.next()) {
        		Empleado Ex = new Empleado(rs.getString("cedula"), rs.getString("primer_nombre"), 
        								 rs.getString("segundo_nombre"), rs.getString("primer_apellido"), 
        								 rs.getString("segundo_apellido"), rs.getString("correo"),
        								 rs.getString("usuario"), rs.getString("clave"), 
        								 rs.getString("rol"));
        		lista.add(Ex);
        	}

        } catch (Exception ex) {
        	throw ex;
        }

        return lista;
	}
	
	public ArrayList<String> obtenerCodigos(){
        String consulta = "{Call dbo.pa_obtener_cedulas_empleados}";
        ArrayList<String> lista = new ArrayList<>();

        try {
                ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);
                
                while(rs.next()) {
                	lista.add(rs.getString("cedula"));
                }

        } catch (Exception ex) {
        }

        return lista;
	}
}
