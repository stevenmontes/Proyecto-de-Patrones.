package cr.ac.cenfotec.proyecto.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import cr.ac.cenfotec.proyecto.controlador.Controlador;

public abstract class MenuPrincipal {
	protected static PrintStream imprimir = System.out;
	protected static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
	protected static Controlador controlador = new Controlador();
	
	public abstract void menu();

	public abstract boolean seleccionarOpcion(int opcion) throws Exception;

	public boolean ejecutar() throws Exception {
		boolean salir = false;
		int opcion;

		do {
			menu();
			opcion = leerOpcion();
			salir = seleccionarOpcion(opcion);
		} while (!salir);
		
		return salir;
	}

	public int leerOpcion() throws IOException {
		imprimir.println("Digite la opci\u00f3n.");
		return Integer.parseInt(leer.readLine());
	}
	
	public static String solicitarDatoString(String mensaje) throws IOException {
		imprimir.println(mensaje);
		return leer.readLine();
	}
	
	public static boolean isValidarCodigoProceso(String codigo) {
		return controlador.validarCodigo(codigo, controlador.codidosTramites());
	}

	public static boolean isValidarCodigoTarea(String codigoTarea) {
		return controlador.validarCodigo(codigoTarea, controlador.codidosTareas());
	}

	public static boolean isValidarCodigoAreaFuncional(String codArea) {
		return controlador.validarCodigo(codArea, controlador.codidosAreas());
	}

	public static boolean isValidarCodigoEmpleado(String cedula) {
		return controlador.validarCodigo(cedula, controlador.codidosEmpleados());
	}
	
}
