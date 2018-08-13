package cr.ac.cenfotec.proyecto.objetos.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import cr.ac.cenfotec.proyecto.controlador.Controlador;

class DepartamentoTest {

	Controlador c = new Controlador();

	@Test
	public void registrarDptoTest() {
		assertEquals("El departamento se registro correctamente en el sistema.",
				c.registrarArea("FI-01", "Finanzas", "Este es el departamento de finanzas"));
	}

	@Test
	public void modificarDeptoTest() {
		assertEquals("El departamento se modifico correctamente en el sistema.",
				c.modificarArea("FI-01", "Finanzas 2", "Este es el departamento modificado de finanzas"));
	}

	@Test
	public void listarDptosTest() {
		boolean vacio;

		if (c.listarAreas().equals(null)) {
			vacio = true;
		} else {
			vacio = false;
		}

		// No puede estar vacío en ningún caso porque se está registrando un
		// departamento en el test anterior
		assertFalse(vacio);
	}

	@Test
	public void modificarEstadoTest() {
		assertEquals("\nNo existe ningún departamento con ese código", c.modificarEstadoDepartamento("0"));

		// El departamento tiene estado activo porque se acaba de registrar
		assertEquals("\nSe ha modificado el departamento de activo a inactivo", c.modificarEstadoDepartamento("FI-01"));

		assertEquals("\nSe ha modificado el departamento de inactivo a activo", c.modificarEstadoDepartamento("FI-01"));
	}

}
