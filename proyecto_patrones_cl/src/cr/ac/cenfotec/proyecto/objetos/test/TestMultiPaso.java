package cr.ac.cenfotec.proyecto.objetos.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cr.ac.cenfotec.proyecto.conexion.Conector;
import cr.ac.cenfotec.proyecto.controlador.Controlador;

class TestMultiPaso {

	static Controlador controlador = new Controlador();
	
	@BeforeEach
	void registrarRegistros() {
		controlador.registrarTramite("TestProceso", "TEST", "FUN WITH TEST");
		controlador.registrarArea("TestArea", "AREAS", "AREASDES");
		controlador.registrarTarea("TestTarea", "TEST", "TAREA", "TestArea", "TestProceso");
	}
	
	@AfterEach
	void eliminarRegistros() throws Exception {
		eliminarRegistroSQL("tpaso", "TestPaso");
		eliminarRegistroSQL("ttarea", "TestTarea");
		eliminarRegistroSQL("tarea_funcional", "TestArea");
		eliminarRegistroSQL("tproceso", "TestProceso");
	}


	@Test
	void testRegistrarPaso() throws Exception {
		assertEquals("El paso se registró correctamente en el sistema.",
				controlador.registrarPaso("TestPaso", "TEST", "testPasoDes", "TestTarea"));
	}
	
	@Test
	void testRegistrarPasoIncorrecto() throws Exception {
		assertEquals("No se pudo registrar el paso, intentelo de nuevo", 
				controlador.registrarPaso("", "", "", ""));
	}

	@Test
	public void testModificarPaso() throws Exception {
		controlador.registrarPaso("TestPaso", "TEST", "testPasoDes", "TestTarea");

		assertEquals("El paso se modifico correctamente en el sistema.",
				controlador.modificarPaso("TestPaso", "TESTModificar", "testPasoModificar"));
	}
	
	@Test
	void testListarPasos() throws Exception {
		controlador.registrarPaso("TestPaso", "TEST", "testPasoDes", "TestTarea");
		assertEquals(1, controlador.listarPaso("TestTarea").length);
	}
	
	@Test
	void testListarPasos3() throws Exception {
		controlador.registrarPaso("TestPaso", "TEST", "testPasoDes", "TestTarea");
		controlador.registrarPaso("TestPaso2", "TEST", "testPasoDes", "TestTarea");
		controlador.registrarPaso("TestPaso3", "TEST", "testPasoDes", "TestTarea");
		assertEquals(3, controlador.listarPaso("TestTarea").length);
		eliminarRegistroSQL("tpaso", "TestPaso2");
		eliminarRegistroSQL("tpaso", "TestPaso3");
	}
	
	private void eliminarRegistroSQL(String tabla, String codigo) throws Exception {
		try {
			Conector.getConector().ejecutarSQL("DELETE FROM " + tabla + " WHERE codigo = '" + codigo + "'");
		} catch (SQLException error) {
			System.out.println("No se pudo eliminar el paso" + error.getMessage());
		}
	}
}
