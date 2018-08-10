package cr.ac.cenfotec.proyecto.test;

import static org.junit.Assert.*;

import java.sql.SQLException;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import cr.ac.cenfotec.proyecto.conexion.Conector;
import cr.ac.cenfotec.proyecto.controlador.Controlador;
import cr.ac.cenfotec.proyecto.multis.MultiTramite;

public class TramiteTest {

	
	Controlador c=new Controlador();
	MultiTramite mt=new MultiTramite();
	@Test
	public void registrarTramite() {
		assertEquals("El proceso se registró correctamente en el sistema.",
				c.registrarTramite("tes-02", "Test 02", "test nuevo "));
	}
	@Test
	public void modificarTramite() throws Exception {
		assertEquals("El proceso se modificó correctamente en el sistema.",
				c.modificarTramite("tes-02", "Test 02", "test modificado"));
	}
	
	@Test
	public void registrarTramiteIncorrecto() {
		assertEquals("No se pudo registrar el proceso, intentelo de nuevo.",
				c.registrarTramite("", "", ""));
	}
	
	 @Test	
	    public void listar() {
			assertEquals(c.listarTramite().length,mt.listarTramites().size());
		}
	 
	@AfterEach
	public void  eliminartest() throws Exception {
		
		eliminarRegistroSQL("tproceso","tes-02");
		
		
	}
 
	private void eliminarRegistroSQL(String tabla, String codigo) throws Exception {
		
		try {
			Conector.getConector().ejecutarSQL("DELETE FROM " + tabla + " WHERE codigo = '" + codigo + "'");
		System.out.println("Se elimino el tramite");
		} catch (SQLException error) {
			System.out.println("No se pudo eliminar el tramite" + error.getMessage());
		}
	}
	
	

}
