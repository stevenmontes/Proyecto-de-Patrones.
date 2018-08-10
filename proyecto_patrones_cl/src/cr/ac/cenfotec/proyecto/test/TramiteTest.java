package cr.ac.cenfotec.proyecto.test;

import static org.junit.Assert.*;

import org.junit.Test;

import cr.ac.cenfotec.proyecto.controlador.Controlador;
import cr.ac.cenfotec.proyecto.multis.MultiTramite;
import cr.ac.cenfotec.proyecto.objetos.Tramite;

public class TramiteTest {

	
	Controlador c=new Controlador() ;
	MultiTramite mt=new MultiTramite();
	@Test
	public void registrarTramite() {
		assertEquals("El proceso se registró correctamente en el sistema.",
				c.registrarTramite("tes-02", "Test 02", "test nuevo "));
	}
	@Test
	public void listar() {
			assertEquals(c.listarTramite().length,mt.listarTramites().size());
	}
	
	@Test
	public void modificarTramite() {
		
		assertEquals("El proceso se modificó correctamente en el sistema.",
				c.modificarTramite("tes-02", "Test 02", "test modificado"));
		
	}

}
