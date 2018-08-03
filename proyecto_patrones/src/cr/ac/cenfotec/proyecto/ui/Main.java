package cr.ac.cenfotec.proyecto.ui;

import java.io.*;
import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.controlador.Controlador;
import cr.ac.cenfotec.proyecto.objetos.Departamento;
import cr.ac.cenfotec.proyecto.objetos.Tarea;

public class Main {

	static PrintStream imprimir = System.out;
	static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
	static String[] usuario;
	static Controlador controlador = new Controlador();

	public static void main(String[] args) throws Exception {
		solicitarInicioSesion();
		mostrarMenuPrincipal();
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

	public static boolean mostrarMenuPrincipal() throws Exception {
		boolean salir = false;
		int area = Integer.parseInt(usuario[3]);

		switch (area) {
		case -1:
			salir = true;
			break;
		case 1:
			salir = mainAdmin();
			break;
		default:
			salir = mainAreas();
			break;
		}

		return salir;
	}

	public static boolean mainAreas() throws IOException {
		boolean salir = false;
		int opcion;

		do {
			mostrarMenu();
			opcion = leerOpcion();

		} while (!salir);

		return salir;
	}

	public static boolean mainAdmin() throws Exception {
		boolean salir = false;
		int opcion;

		do {
			mostrarMenuAdministrador();
			opcion = leerOpcion();
			salir = mainAdminOpciones(opcion);
		} while (!salir);

		return salir;
	}

	public static void mostrarMenuAdministrador() {
		imprimir.println("1.REGISTRAR");
		imprimir.println("2.MODIFICAR");
		imprimir.println("3.LISTAR");
		imprimir.println("4.SALIR");
	}

	public static void mostrarMenu() {
		ArrayList<String> pasos = controlador.obtenerDescripcionPaso(usuario[3]);

		for (int i = 0; i < pasos.size(); i++) {
			int cont = i;
			imprimir.println((++cont) + ". " + pasos.get(i));
		}
	}

	public static boolean mainAdminOpciones(int opcion) throws Exception {
		boolean salir = false;

		switch (opcion) {
		case 1:
			mainAdminRegistrar();
			break;
		case 2:
			mainAdminModificar();
			break;
		case 3:
			mainAdminListar();
			break;
		case 4:
			salir = true;
			break;
		default:
			imprimir.println("Comando inv\u00e1lido");
			break;
		}

		return salir;
	}

	public static void menuAdminRegistrar() {
		imprimir.println("1. Registrar proceso");
		imprimir.println("2. Registrar tarea");
		imprimir.println("3. Registrar pasos");
		imprimir.println("4. Registrar empleados");
		imprimir.println("5. Registrar \u00e1reas funcionales");
		imprimir.println("6. Salir");
	}

	public static void mainAdminRegistrar() throws IOException {
		boolean salir = false;
		int opcion;

		while (!salir) {
			menuAdminRegistrar();
			opcion = leerOpcion();
			salir = seleccionarOpcionRegistrar(opcion);
		}
	}

	public static boolean seleccionarOpcionRegistrar(int opcion) throws IOException {
		boolean salir = false;

		switch (opcion) {
		case 1:
			obtenerInfoTramite();
			break;
		case 2:
			obtenerInfoTarea();
			break;
		case 3:
			obtenerInfoPaso();
			break;
		case 4:
			obtenerInfoEmpleado();
			break;
		case 5:
			obtenerInfoArea();
			break;
		case 6:
			salir = true;
			break;
		}

		return salir;
	}

	public static void obtenerInfoTramite() throws IOException {
		String codigo = solicitarDatoString("Digite el c\u00f3digo");
		String nombre = solicitarDatoString("Digite el nombre");
		String descripcion = solicitarDatoString("Digite el descripci\u00f3n");

		if (isValidarCodigoProceso(codigo)) {
			imprimir.println(controlador.registrarTramite(codigo, nombre, descripcion));
		} else {
			imprimir.println("C\u00f3digo del proceso esta repetido. Vuelve a intentarlo.");
		}
	}

	public static void obtenerInfoTarea() throws IOException {
		String codigoTarea = solicitarDatoString("Digite el c\u00f3digo.");
		String nombre = solicitarDatoString("Digite el nombre.");
		String descripcion = solicitarDatoString("Digite el descripci\u00f3n.");
		String codigoDep = solicitarDatoString("Digite el c\u00f3digo del departamento encargado");
		String codigoPro = solicitarDatoString("Digite el c\u00f3digo del proceso que pertenece esta tarea");

		if (!isValidarCodigoTarea(codigoTarea) && isValidarCodigoProceso(codigoPro)
				&& isValidarCodigoAreaFuncional(codigoDep)) {
			imprimir.println(controlador.registrarTarea(codigoTarea, nombre, descripcion, codigoDep, codigoPro));
		} else {
			imprimir.println("Algun c\u00f3digo introducido, no existe en el sistema. " + "Vuelve a intentarlo.");
		}

	}

	public static void obtenerInfoPaso() throws IOException {
		String codigoTarea = solicitarDatoString("Digite el c\u00f3digo de la tarea que pertenece este paso");
		String codigo = solicitarDatoString("Digite el c\u00f3digo.");
		String nombre = solicitarDatoString("Digite el nombre.");
		String descripcion = solicitarDatoString("Digite la descripci\u00f3n.");

		if (isValidarCodigoTarea(codigoTarea)) {
			imprimir.println(controlador.registrarPaso(codigo, nombre, descripcion, codigoTarea));
		} else {
			imprimir.println("El c\u00f3digo de la tarea no se encuentra en el sistema.");

		}

	}

	public static void obtenerInfoEmpleado() throws IOException {
		String cedula = solicitarDatoString("Digite la c\u00e9dula.");
		String nom1 = solicitarDatoString("Digite el primer nombre.");
		String nom2 = solicitarDatoString("Digite el segundo nombre.(Opcional)");
		String ape1 = solicitarDatoString("Digite el primer apellido.");
		String ape2 = solicitarDatoString("Digite el segundo apellido.(Opcional)");
		String correo = solicitarDatoString("Digite el correo.");
		String nomU = solicitarDatoString("Digite el nombre del usuario.");
		String clave = solicitarDatoString("Digite la clave.");
		String rol = solicitarDatoString("Digite el rol.");
		String codArea = solicitarDatoString(
				"Digite el c\u00f3digo de la \u00e1rea funcional la cual pertenece el empleado");

		if (!isValidarCodigoEmpleado(cedula) && isValidarCodigoAreaFuncional(codArea)) {
			imprimir.println(
					controlador.registrarEmpleado(cedula, nom1, nom2, ape1, ape2, correo, nomU, clave, rol, codArea));
		} else {
			imprimir.println("La c\u00e9dula del empleado ya existe en el sistema.");
		}
	}

	public static void obtenerInfoArea() throws IOException {
		String codigo = solicitarDatoString("Digite el c\u00f3digo.");
		String nombre = solicitarDatoString("Digite el nombre.");
		String descripcion = solicitarDatoString("Digite la descripci\u00f3n.");

		if (!isValidarCodigoAreaFuncional(codigo)) {
			imprimir.println(controlador.registrarArea(codigo, nombre, descripcion));
		} else {
			imprimir.println("El c\u00f3digo del \u00e1rea funcional ya existe en el sistema.");
		}
	}

	public static void menuAdminModificar() {
		imprimir.println("1. Modificar proceso");
		imprimir.println("2. Modificar tarea");
		imprimir.println("3. Modificar pasos");
		imprimir.println("4. Modificar empleados");
		imprimir.println("5. Modificar \u00e1reas funcionales");
		imprimir.println("6. Modificar estado \u00e1rea funcional");
		imprimir.println("7. Salir");
	}

	public static void mainAdminModificar() throws IOException {
		boolean salir = false;
		int opcion;

		while (!salir) {
			menuAdminModificar();
			opcion = leerOpcion();
			salir = seleccionarOpcionModificar(opcion);
		}
	}

	public static boolean seleccionarOpcionModificar(int opcion) throws IOException {
		boolean salir = false;

		switch (opcion) {
		case 1:
			modificarProceso();
			break;
		case 2:
			modificarTarea();
			break;
		case 3:
			modificarPaso();
			break;
		case 4:
			modificarEmpleado();
			break;
		case 5:
			modificarArea();
			break;
		case 6:
			modificarEstadoArea();
		case 7:
			salir = true;
			break;
		}

		return salir;
	}

	private static void modificarEstadoArea() throws IOException {
		imprimir.println("Digite el código del departamento al que desea cambiarle el estado");
		String codigo = leer.readLine();

		imprimir.println(controlador.modificarEstadoDepartamento(codigo));
	}

	public static void modificarProceso() throws IOException {
		String codigo = solicitarDatoString("Digite el nuevo c\u00f3digo");
		String nombre = solicitarDatoString("Digite el nuevo nombre");
		String descripcion = solicitarDatoString("Digite la nueva descripci\u00f3n");

		if (isValidarCodigoProceso(codigo)) {
			imprimir.println(controlador.modificarTramite(codigo, nombre, descripcion));
		} else {
			imprimir.println("No existe el c\u00f3digo del proceso. vuelve a intentarlo");
		}
	}

	public static void modificarTarea() throws IOException {
		String codigoTarea, nombre, descripcion, codigoDep;

		codigoTarea = solicitarDatoString("Digite el c\u00f3digo de la tarea a modificar");

		if (!isValidarCodigoTarea(codigoTarea)) {
			imprimir.println("No existe el c\u00f3digo de la tarea, vuelva a intentarlo.");
		} else {
			codigoDep = solicitarDatoString("Digite el c\u00f3digo del nuevo departamento encargado de la tarea");

			if (!isValidarCodigoAreaFuncional(codigoDep)) {
				imprimir.println("No existe el c\u00f3digo del departamento, vuelva a intentarlo.");
			} else {
				nombre = solicitarDatoString("Digite el nuevo nombre de la tarea");
				descripcion = solicitarDatoString("Digite la nueva descripci\u00f3n de la tarea");

				imprimir.println(controlador.modificarTarea(codigoTarea, nombre, descripcion, codigoDep));
			}

		}

	}

	public static void modificarPaso() throws IOException {
		String descripcion, nombre, codigo;
		codigo = solicitarDatoString("Digite el  c\u00f3digo del paso a modificar.");
		nombre = solicitarDatoString("Digite el nuevo nombre de este paso.");
		descripcion = solicitarDatoString("Digite la nueva descripci\u00f3n de este paso.");

		if (controlador.validarCodigo(codigo, controlador.codidosPasos())) {
			imprimir.println(controlador.modificarPaso(codigo, nombre, descripcion));
		} else {
			imprimir.println("No existe el  c\u00f3digo del paso, vuelve a intentarlo.");

		}
	}

	public static void modificarEmpleado() throws IOException {
		String cedula = solicitarDatoString("Digite la nueva c\u00e9dula.");
		String nom1 = solicitarDatoString("Digite el nuevo primer nombre.");
		String nom2 = solicitarDatoString("Digite el nuevo segundo nombre.(Opcional)");
		String ape1 = solicitarDatoString("Digite el nuevo primer apellido.");
		String ape2 = solicitarDatoString("Digite el nuevo segundo apellido.(Opcional)");
		String correo = solicitarDatoString("Digite el nuevo correo.");
		String nomU = solicitarDatoString("Digite el nuevo nombre del usuario.");
		String clave = solicitarDatoString("Digite la nueva clave.");
		String rol = solicitarDatoString("Digite el nuevo rol.");
		String codArea = solicitarDatoString("Digite el c\u00f3digo de la \u00e1rea funcional");

		if (isValidarCodigoEmpleado(cedula) && isValidarCodigoAreaFuncional(codArea)) {
			imprimir.println(
					controlador.modificarEmpleado(cedula, nom1, nom2, ape1, ape2, correo, nomU, clave, rol, codArea));
		} else {
			imprimir.println("La c\u00e9dula del empleado ya existe en el sistema.");
		}
	}

	public static void modificarArea() throws IOException {
		String codigo = solicitarDatoString("Digite el nuevo c\u00f3digo.");
		String nombre = solicitarDatoString("Digite el nuevo nombre.");
		String descripcion = solicitarDatoString("Digite la nueva descripci\u00f3n.");

		if (isValidarCodigoAreaFuncional(codigo)) {
			imprimir.println(controlador.modificarArea(codigo, nombre, descripcion));
		} else {
			imprimir.println("El c\\u00f3digo de la area funcional ya existe en el sistema.");
		}
	}

	public static void menuAdminListar() {
		imprimir.println("0. Listar procesos");
		imprimir.println("1. Listar proceso activos");
		imprimir.println("2. Listar proceso completadas");
		imprimir.println("3. Listar tarea de un proceso");
		imprimir.println("4. Listar pasos de una tarea");
		imprimir.println("5. Listar empleados");
		imprimir.println("6. Listar areas funcionales");
		imprimir.println("7. Salir");
	}

	public static void mainAdminListar() throws Exception {
		boolean salir = false;
		int opcion;

		while (!salir) {
			menuAdminListar();
			opcion = leerOpcion();
			salir = seleccionarOpcionListar(opcion);
		}
	}

	public static String[] obtenerLista(int opcion, String codigo) throws Exception {
		boolean salir = false;
		String[] listaObjeto = null;
		switch (opcion) {
		case 0:
			listaObjeto = controlador.listarTramite();
			break;
		case 1:
			listaObjeto = controlador.listarProcesosActivos();
			break;
		case 2:
			listaObjeto = controlador.listarProcesosCompletos();
			break;
		case 3:
			listaObjeto = controlador.listarTareas(codigo);
			break;
		case 4:
			listaObjeto = controlador.listarPaso(codigo);
			break;
		case 5:
			listaObjeto = controlador.listarEmpleado();
			break;
		case 6:
			listaObjeto = controlador.listarAreas();
			break;
		default:
			imprimir.println("Lo sentimos esa opci\u00f3n no est&aacute dentro del  men&uacute");
			;
			break;
		}

		return listaObjeto;
	}

	public static boolean seleccionarOpcionListar(int opcion) throws Exception {
		String[] info = null;
		String codigo = null;
		info = obtenerProcesoOTarea(opcion);
		boolean salir = false;
		for (int i = 0; i < info.length; i++) {
			imprimir.println(info[i]);
		}
		salir = true;
		return salir;

	}

	private static String[] obtenerProcesoOTarea(int opcion) throws IOException, Exception {
		String[] info;
		String codigo;
		if (opcion == 3) {
			codigo = obtenerCodigoProceso();
			info = obtenerLista(opcion, codigo);
		}
		if (opcion == 4) {
			codigo = obtenerCodigoTarea();
			info = obtenerLista(opcion, codigo);
		} else {
			info = obtenerLista(opcion, "");
		}

		return info;
	}

	public static String obtenerCodigoProceso() throws IOException {
		String codProceso = "";
		boolean esCodigoValido = false;

		while (!esCodigoValido) {
			imprimir.println("Digite el c\u00f3digo del proceso");
			codProceso = leer.readLine();
			esCodigoValido = isValidarCodigoProceso(codProceso);
		}

		return codProceso;
	}

	private static String obtenerCodigoTarea() throws IOException {
		String codTarea = "";
		boolean validar = false;

		while (!validar) {
			imprimir.println("Digite el c\u00f3digo de la tarea");
			codTarea = leer.readLine();
			validar = isValidarCodigoTarea(codTarea);
		}

		return codTarea;
	}

	private static boolean isValidarCodigoProceso(String codigo) {
		return controlador.validarCodigo(codigo, controlador.codidosTramites());
	}

	private static boolean isValidarCodigoTarea(String codigoTarea) {
		return controlador.validarCodigo(codigoTarea, controlador.codidosTareas());
	}

	private static boolean isValidarCodigoAreaFuncional(String codArea) {
		return controlador.validarCodigo(codArea, controlador.codidosAreas());
	}

	private static boolean isValidarCodigoEmpleado(String cedula) {
		return controlador.validarCodigo(cedula, controlador.codidosEmpleados());
	}

	private static String solicitarDatoString(String mensaje) throws IOException {
		imprimir.println(mensaje);
		return leer.readLine();
	}

}
