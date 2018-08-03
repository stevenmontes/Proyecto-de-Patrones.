package cr.ac.cenfotec.proyecto.ui;

import java.io.*;

import cr.ac.cenfotec.proyecto.controlador.Controlador;

public abstract class Main {

	protected static PrintStream imprimir = System.out;
	protected static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
	protected static String[] usuario;
	protected static Controlador controlador = new Controlador();

	public static void main(String[] args) throws Exception {
		solicitarInicioSesion();
		Main nuevo = seleccionarMenuPrincipal();
		nuevo.ejecutar();
	}

	public abstract void mostrarMenuPrincipal();

	public abstract boolean seleccionarOpcion(int opcion) throws Exception;
	
	public void ejecutar() throws Exception {
		boolean salir = false;
		int opcion;

		do {
			mostrarMenuPrincipal();
			opcion = leerOpcion();
			salir = seleccionarOpcion(opcion);
		} while (!salir);
	}

	public static void solicitarInicioSesion() throws IOException {
		boolean inicioSesion = false;

		while (!inicioSesion) {
			String nombre = solicitarDatoString("Digite su usuario.");
			String clave = solicitarDatoString("Digite su contrase\u00f1a");
			inicioSesion = verificarInicioSesion(nombre, clave);
		}
	}

	public static boolean verificarInicioSesion(String nombre, String clave) {
		usuario = controlador.iniciarSesion(nombre, clave);

		if (usuario[3] != null) {
			imprimir.println("Se inicio sesi\u00f3n exitosamente.");
			return true;
		} else {
			imprimir.println("Datos introducidos incorrectos. Vuelve a intentarlo.");
			return false;
		}
	}

	public static int leerOpcion() throws IOException {
		imprimir.println("Digite la opci\u00f3n.");
		return Integer.parseInt(leer.readLine());
	}

	public static Main seleccionarMenuPrincipal() throws Exception {
		int area = Integer.parseInt(usuario[3]);
		Main nuevo;

		switch (area) {
		case 1:
			nuevo = new MainAdmin();
			break;
		default:
			nuevo = new MainAreas();
			break;
		}

		return nuevo;
	}

	protected static boolean isValidarCodigoProceso(String codigo) {
		return controlador.validarCodigo(codigo, controlador.codidosTramites());
	}

	protected static boolean isValidarCodigoTarea(String codigoTarea) {
		return controlador.validarCodigo(codigoTarea, controlador.codidosTareas());
	}

	protected static boolean isValidarCodigoAreaFuncional(String codArea) {
		return controlador.validarCodigo(codArea, controlador.codidosAreas());
	}

	protected static boolean isValidarCodigoEmpleado(String cedula) {
		return controlador.validarCodigo(cedula, controlador.codidosEmpleados());
	}

	protected static String solicitarDatoString(String mensaje) throws IOException {
		imprimir.println(mensaje);
		return leer.readLine();
	}

}
