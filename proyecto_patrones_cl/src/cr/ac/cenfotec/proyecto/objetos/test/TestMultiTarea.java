/**
 * 
 */
package cr.ac.cenfotec.proyecto.objetos.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cr.ac.cenfotec.proyecto.controlador.Controlador;

/**
 * @author enzoq
 *
 */
class TestMultiTarea {

	static Controlador controlador = new Controlador();

	@Test
	public void testModificarTarea() throws Exception {

		controlador.registrarTarea("TestTarea", "TEST", "TAREA", "TestArea", "TestProceso");
		assertEquals("La tarea se modifico correctamente en el sistema.",
				controlador.modificarTarea("TestTarea2", "TEST2", "TestArea2", "TestProceso2"));
	}

	@Test
	void testListarTareas() throws Exception {
		controlador.registrarTarea("TestTarea", "TEST", "TAREA", "TestArea", "tes-02");
		controlador.registrarTarea("TestTarea2", "TEST2", "TAREA2", "TestArea2", "TestProceso2");
		controlador.registrarTarea("TestTarea3", "TEST3", "TAREA", "TestArea3", "TestProceso3");
		assertEquals(1, controlador.listarTareas("tes-02").length);

	}
}
