package cr.ac.cenfotec.proyecto.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import cr.ac.cenfotec.proyecto.conexion.Conector;
import cr.ac.cenfotec.proyecto.controlador.Controlador;
import cr.ac.cenfotec.proyecto.multis.MultiEmpleado;
import cr.ac.cenfotec.proyecto.multis.MultiTramite;

class EmpleadoTest {

	Controlador c=new Controlador();
	MultiEmpleado mE=new MultiEmpleado();
	
	
	@Test
	public void  eliminartest() throws Exception {
		eliminarRegistroSQL("templeado","tes-02");
	}
	@Test
	public void registrarEmpleado() {
		assertEquals("Se ha registrado exitosamente el empleado.",
		c.registrarEmpleado("123428925","queso","queso2","queso4","queso3","queso@email.com", "quesito", "quesitoClave", "Cocinero", "CO-01"));
	}
	@Test
	public void modificarTramite() throws Exception {
		assertEquals("Se ha modificado exitosamente el empleado.",
		c.modificarEmpleado("123428925" ,"Queso","Cheddar","amarillo","conNachos","queso@email.com", "quesito", "quesitoClave", "Cocinero", "CO-01"));
	}
	 @Test	
	    public void listar() throws Exception {
			assertEquals(c.listarEmpleado().length,mE.listarTodosEmpleados().size());
		}
	private void eliminarRegistroSQL(String tabla, String cedula) throws Exception {
		
		try {
			Conector.getConector().ejecutarSQL("DELETE FROM " + tabla + " WHERE cedula = '" + cedula + "'");
		System.out.println("Se elimino el empleado");
		} catch (SQLException error) {
			System.out.println("No se pudo eliminar el tramite" + error.getMessage());
		}
		
		

		
	}
}
