package cr.ac.cenfotec.proyecto.ui;

import java.io.*;

public abstract class Main extends MenuPrincipal{
	static String[] usuario = {};
	
	public static void main(String[] args) throws Exception {
		solicitarInicioSesion();
		MenuPrincipal nuevo = seleccionarMenuPrincipal();
		nuevo.ejecutar();
		imprimir.println("Apagando sistema..");
		imprimir.println("Apagado.");
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

	public static MenuPrincipal seleccionarMenuPrincipal() throws Exception {
		int area = Integer.parseInt(usuario[3]);

		switch (area) {
		case 1:
			return new MenuAdministrador();
		default:
			return new MenuAreas();
		}
	}
}
