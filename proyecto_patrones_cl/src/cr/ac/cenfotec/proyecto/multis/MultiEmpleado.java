package cr.ac.cenfotec.proyecto.multis;

import java.sql.ResultSet;
import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.conexion.Conector;
import cr.ac.cenfotec.proyecto.objetos.Departamento;
import cr.ac.cenfotec.proyecto.objetos.Empleado;

public class MultiEmpleado {
	public MultiEmpleado() {
		
	}
	
	public String[] iniciarSesion (String usuario, String clave){
		String consulta = "{Call dbo.pa_iniciar_sesion ('" + usuario + "', '" + clave + "')}";
		String [] info = new String[4];
		
		try {
			ResultSet conexion = Conector.getConector().ejecutarSQL(consulta, true);
			
			while(conexion.next()) {
				info[0] = conexion.getString("cedula");
				info[1] = conexion.getString("primer_nombre");
				info[2] = conexion.getString("primer_apellido");
				info[3] = conexion.getString("id_area_funcional");
			}
			
		} catch (Exception error) {
			info[0] = "El nombre del usuario o clave no coinciden entre si.";
		}
		
		return info;
	}
	
	public String registrarEmpleado(Empleado E){
		String consulta = "{Call dbo.pa_registrar_empleado ('" + E.getCedula() + "', '" + E.getPrimerNombre() + "', '" + E.getSegundoNombre() + "', '"+E.getPrimerApellido()+"', '"+E.getSegundoApellido()+"', '"+E.getCorreo()+"', '"+E.getUsuario()+"', '"+E.getClave()+"', '"+E.getRol()+"', '"+E.getAreaFuncional().getCodigo()+"')}";
		String result;
		
		try {
			Conector.getConector().ejecutarSQL(consulta, true);
			result = "Se ha registrado exitosamente el empleado.";
		} catch (Exception error) {
			result = "No se pudo registrar el empleado. Vuelve a intentarlo." + error.getMessage();
		}
		
		return result;
	}
	
	public String modificarEmpleado(Empleado E) {
		String consulta = "{Call dbo.pa_modificar_empleado ('" + E.getCedula() + "', '" + E.getPrimerNombre() + "', '" + E.getSegundoNombre() + "', '"+E.getPrimerApellido()+"', '"+E.getSegundoApellido()+"', '"+E.getCorreo()+"', '"+E.getUsuario()+"', '"+E.getClave()+"', '"+E.getRol()+"', '"+E.getAreaFuncional()+"')}";
		String result;
		
		try {
			Conector.getConector().ejecutarSQL(consulta, true);
			result = "Se ha modificado exitosamente el empleado.";
		} catch (Exception error) {
			result = "No se pudo modificar el empleado. Vuelve a intentarlo.";
		}
		
		return result;
	}
	
	public ArrayList<Empleado> listarTodosEmpleados() throws Exception{
		ArrayList<Empleado> lista = new ArrayList<>();
        String consulta = "{Call dbo.pa_listar_empleados}";

        try {
        	ResultSet conexion = Conector.getConector().ejecutarSQL(consulta, true);
        	
        	while(conexion.next()) {
        		Empleado nuevoEmpleado = new Empleado(conexion.getString("cedula"), conexion.getString("primer_nombre"), 
        								 conexion.getString("segundo_nombre"), conexion.getString("primer_apellido"), 
        								 conexion.getString("segundo_apellido"), conexion.getString("correo"),
        								 conexion.getString("usuario"), conexion.getString("clave"), 
        								 conexion.getString("rol"));
        		Departamento area = new Departamento();
        		area.setNombre(conexion.getString("nombre"));
        		nuevoEmpleado.setAreaFuncional(area);
        		lista.add(nuevoEmpleado);
        	}

        } catch (Exception error) {
        	throw error;
        }

        return lista;
	}
	
	public ArrayList<String> obtenerCodigos(){
        String consulta = "{Call dbo.pa_obtener_cedulas_empleados}";
        ArrayList<String> lista = new ArrayList<>();

        try {
                ResultSet conexion = Conector.getConector().ejecutarSQL(consulta, true);
                
                while(conexion.next()) {
                	lista.add(conexion.getString("cedula"));
                }

        } catch (Exception error) {
        }

        return lista;
	}
}
