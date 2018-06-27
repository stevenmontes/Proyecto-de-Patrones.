package cr.ac.cenfotec.proyecto.ui;

import java.io.*;
import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.controlador.Controlador;

public class Main {

    static PrintStream imprimir = System.out;
    static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
    static String[] usuario;
    static Controlador controlador = new Controlador();
	
	public static void main(String[] args) throws IOException {
		iniciarSesion();
		mostrarMenuPrincipal();
	}
	
	public static void iniciarSesion() throws IOException {
		boolean iniciar = false;
		
		while (!iniciar) {
			iniciar = obtenerInicioSesion();
		}
	}
	
	public static boolean obtenerInicioSesion() throws IOException {
		boolean a;
		String nombre, clave;
		
		imprimir.println("Digite su usuario.");
		nombre = leer.readLine();
		imprimir.println("Digite su contraseña");
		clave = leer.readLine();
		
		a = verificarInicioSesion(nombre, clave);
		imprimir.println(usuario[4]);
		
		return a;
	}
	
	public static boolean verificarInicioSesion(String nombre, String clave) {
		boolean e;
		try {
			usuario = controlador.iniciarSesion(nombre, clave);
			e = true;
		} catch (Exception ex) {
			e = false;
		}
		
		return e;
	}
	
	public static int leerOpcion() throws IOException {
		imprimir.println("Digite la opcion.");
		return Integer.parseInt(leer.readLine());
	}
	
	public static boolean mostrarMenuPrincipal() throws IOException {
		int area = Integer.parseInt(usuario[3]);
		boolean a = false;
		
		switch(area) {
		case -1:
			a = true;
			break;
		case 1: 
			a = mainAdmin();
			break;
		default:
			a = mainAreas();
			break;
		}
		
		return a;
	}
	
	public static boolean mainAreas() throws IOException {
		boolean salir = false;
		int opcion;
		
		do {
			mostrarMenu();
			opcion = leerOpcion();
			//salir = mainOpciones(opcion);
		} while (!salir);
		
		return salir;
	}	
	
	public static boolean mainAdmin() throws IOException {
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
	
	public static void mostrarMenu () {
		ArrayList<String> pasos = controlador.obtenerDescripcionPaso(usuario[3]);
		
		for(int i = 0; i < pasos.size(); i++) {
			int cont = i;
			imprimir.println((++cont) + ". " + pasos.get(i));
		}
	}
	
	public static boolean mainAdminOpciones(int opcion) throws IOException {
		boolean salir = false;
		
		switch(opcion) {
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
			imprimir.println("Comando invalido");
			break;
		}
		
		return salir;
	}
	
	public static void menuAdminRegistrar() {
		imprimir.println("1. Registrar proceso");
		imprimir.println("2. Registrar tarea");
		imprimir.println("3. Registrar pasos");
		imprimir.println("4. Registrar empleados");
		imprimir.println("5. Registrar areas funcionales");
		imprimir.println("6. Salir");
	}
	
	public static void mainAdminRegistrar() throws IOException {
		boolean salir = false;
		int opcion;
		
		while(!salir) {
			menuAdminRegistrar();
			opcion = leerOpcion();
			salir = seleccionarOpcionRegistrar(opcion);
		}
	}
	
	public static boolean seleccionarOpcionRegistrar(int opcion) throws IOException {
		boolean salir = false;
		
		switch(opcion) {
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
		String codigo, nombre, descripcion;
		boolean EE;
		imprimir.println("Digite el código del proceso");
		codigo = leer.readLine();
		imprimir.println("Digite el nombre del proceso");
		nombre = leer.readLine();
		imprimir.println("Digite la descripción del proceso");
		descripcion = leer.readLine();
		
		EE = controlador.validarCodigo(codigo, controlador.codidosTramites());
		
		if(!EE) {
			imprimir.println(controlador.registrarTramite(codigo, nombre, descripcion));
		} else {
			imprimir.println("Codigo del tramite esta repetido. Vuelve a intentarlo.");
		}
	}
	
	public static void obtenerInfoTarea () throws IOException {
		String codigoTarea, nombre, descripcion, resultado, codigoDep, codigoPro;
		boolean CT, CP, CA;
		imprimir.println("Digite el código de la tarea");
		codigoTarea = leer.readLine();
		imprimir.println("Digite el nombre de la tarea");
		nombre = leer.readLine();
		imprimir.println("Digite la descripción de la tarea");
		descripcion = leer.readLine();
		imprimir.println("Digite el codigo del departamento encargado");
		codigoDep = leer.readLine();
		imprimir.println("Digite el codigo del proceso que pertenece esta tarea");
		codigoPro = leer.readLine();
		
		CT = controlador.validarCodigo(codigoTarea, controlador.codidosTareas());
		CP = controlador.validarCodigo(codigoPro, controlador.codidosTramites());
		CA = controlador.validarCodigo(codigoDep, controlador.codidosAreas());
		
		if(!CT && CP && CA) {
			imprimir.println(controlador.registrarTarea(codigoTarea, nombre, descripcion, codigoDep, codigoPro));
		} else {
			imprimir.println("Algun codigo introducidos, estan repetidos o no existen en el sistema. "
					+ "Vuelve a intentarlo.");
		}
		
	}
	
	public static void obtenerInfoPaso() throws IOException {
		String descripcion, codigoTarea, nombre, codigo;
		boolean CT;
		imprimir.println("Digite el codigo de la tarea que pertenece este paso");
		codigoTarea = leer.readLine();
		imprimir.println("Digite el codigo de este paso.");
		codigo = leer.readLine();
		imprimir.println("Digite el nombre de este paso.");
		nombre = leer.readLine();
		imprimir.println("Digite la descripcion de este paso.");
		descripcion = leer.readLine();
		
		CT = controlador.validarCodigo(codigoTarea, controlador.codidosTareas());
		
		if(CT) {
			imprimir.println(controlador.registrarPaso(codigo, nombre, descripcion, codigoTarea));
		} else {
			imprimir.println("El codigo de la tarea no se encuentra en el sistema.");
			
		}
		
	}
	
	public static void obtenerInfoEmpleado() throws IOException {
		String cedula, nom1, nom2, ape1, ape2, correo, nomU, clave, rol, codArea;
		boolean CE, CA;
		
		imprimir.println("Digite la cedula del empleado");
		cedula = leer.readLine();
		imprimir.println("Digite el primer nombre del empleado");
		nom1 = leer.readLine();
		imprimir.println("Digite el segundo nombre del empleado (Opcional)");
		nom2 = leer.readLine();
		imprimir.println("Digite el primer apellido del empleado");
		ape1 = leer.readLine();
		imprimir.println("Digite el segundo apelldio del empleado (Opcional)");
		ape2 = leer.readLine();
		imprimir.println("Digite el correo del empleado");
		correo = leer.readLine();
		imprimir.println("Digite el nombre del usuario que va tener el empleado");
		nomU = leer.readLine();
		imprimir.println("Digite la clave que va utilizar el empleado");
		clave = leer.readLine();
		imprimir.println("Digite el rol del empleado");
		rol = leer.readLine();
		imprimir.println("Digite el codigo de la area funcional la cual pertenece el empleado");
		codArea = leer.readLine();
		
		CE = controlador.validarCodigo(cedula, controlador.codidosEmpleados());
		CA = controlador.validarCodigo(codArea, controlador.codidosAreas());
		
		if(!CE && CA) {
			imprimir.println(controlador.registrarEmpleado(cedula, nom1, nom2, ape1, ape2, correo, nomU, clave, rol, codArea));
		} else {
			imprimir.println("La cedula del empleado ya existe en el sistema.");
		}
	}
	
	public static void obtenerInfoArea() throws IOException {
		String codigo, nombre, descripcion, resultado;
		boolean CA;
		imprimir.println("Digite el código de la area funcional");
		codigo = leer.readLine();
		imprimir.println("Digite el nombre de la area funcional");
		nombre = leer.readLine();
		imprimir.println("Digite la descripción de la area funcional");
		descripcion = leer.readLine();
		
		CA = controlador.validarCodigo(codigo, controlador.codidosAreas());
		
		if(!CA) {
			imprimir.println(controlador.registrarArea(codigo, nombre, descripcion));
		} else {
			imprimir.println("El codigo del area funcional ya existe en el sistema.");
		}
	}
	
	public static void menuAdminModificar() {
		imprimir.println("1. Modificar proceso");
		imprimir.println("2. Modificar tarea");
		imprimir.println("3. Modificar pasos");
		imprimir.println("4. Modificar empleados");
		imprimir.println("5. Modificar areas funcionales");
		imprimir.println("6. Salir");
	}	
	
	public static void mainAdminModificar() throws IOException {
		boolean salir = false;
		int opcion;
		
		while(!salir) {
			menuAdminModificar();
			opcion = leerOpcion();
			salir = seleccionarOpcionModificar(opcion);
		}
	}
	
	public static boolean seleccionarOpcionModificar(int opcion) throws IOException {
		boolean salir = false;
		
		switch(opcion) {
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
			salir = true;
			break;
		}
		
		return salir;
	}
	
	public static void modificarProceso() throws IOException {
		String codigo, nombre, descripcion;
		boolean EE;
		imprimir.println("Digite el código del proceso a modificar");
		codigo = leer.readLine();
		imprimir.println("Digite el nuevo nombre del proceso");
		nombre = leer.readLine();
		imprimir.println("Digite la nueva descripción del proceso");
		descripcion = leer.readLine();
		
		EE = controlador.validarCodigo(codigo, controlador.codidosTramites());
		
		if(EE) {
			imprimir.println(controlador.modificarTramite(codigo, nombre, descripcion)); 
		} else {
			imprimir.println("No existe el codigo del proceso. vuelve a intentarlo");
		}
	}
	
	public static void modificarTarea() throws IOException {
		String codigoTarea, nombre, descripcion, codigoDep;
		boolean CT, CA;
		imprimir.println("Digite el código de la tarea a modificar");
		codigoTarea = leer.readLine();
		imprimir.println("Digite el nuevo nombre de la tarea");
		nombre = leer.readLine();
		imprimir.println("Digite la nueva descripción de la tarea");
		descripcion = leer.readLine();
		imprimir.println("Digite el codigo del nuevo departamento encargado de la tarea");
		codigoDep = leer.readLine();
		
		CT = controlador.validarCodigo(codigoTarea, controlador.codidosTareas());
		CA = controlador.validarCodigo(codigoDep, controlador.codidosAreas());
		
		if(CT && CA) {
			imprimir.println(controlador.modificarTarea(codigoTarea, nombre, descripcion, codigoDep)); 
		} else {
			imprimir.println("No existe el codigo de la tarea. vuelve a intentarlo.");
		}
	}
	
	public static void modificarPaso() throws IOException {
		String descripcion, nombre, codigo;
		boolean CT;
		imprimir.println("Digite el codigo del paso a modificar.");
		codigo = leer.readLine();
		imprimir.println("Digite el nuevo nombre de este paso.");
		nombre = leer.readLine();
		imprimir.println("Digite la nueva descripcion de este paso.");
		descripcion = leer.readLine();
		
		CT = controlador.validarCodigo(codigo, controlador.codidosPasos());
		
		if(CT) {
			imprimir.println(controlador.modificarPaso(codigo, nombre, descripcion));
		} else {
			imprimir.println("No existe el codigo del paso, vuelve a intentarlo.");
			
		}
	}
	
	public static void modificarEmpleado() throws IOException {
		String cedula, nom1, nom2, ape1, ape2, correo, nomU, clave, rol, codArea;
		boolean CE, CA;
		
		imprimir.println("Digite la cedula del empleado a modificar");
		cedula = leer.readLine();
		imprimir.println("Digite el nuevo primer nombre del empleado");
		nom1 = leer.readLine();
		imprimir.println("Digite el nuevo segundo nombre del empleado (Opcional)");
		nom2 = leer.readLine();
		imprimir.println("Digite el nuevo primer apellido del empleado");
		ape1 = leer.readLine();
		imprimir.println("Digite el nuevo segundo apelldio del empleado (Opcional)");
		ape2 = leer.readLine();
		imprimir.println("Digite el nuevo correo del empleado");
		correo = leer.readLine();
		imprimir.println("Digite el nuevo nombre del usuario que va tener el empleado");
		nomU = leer.readLine();
		imprimir.println("Digite la nuevo clave que va utilizar el empleado");
		clave = leer.readLine();
		imprimir.println("Digite el nuevo rol del empleado");
		rol = leer.readLine();
		imprimir.println("Digite el codigo de la area funcional la cual pertenece el empleado");
		codArea = leer.readLine();
		
		CE = controlador.validarCodigo(cedula, controlador.codidosEmpleados());
		CA = controlador.validarCodigo(codArea, controlador.codidosAreas());
		
		if(CE && CA) {
			imprimir.println(controlador.modificarEmpleado(cedula, nom1, nom2, ape1, ape2, correo, nomU, clave, rol, codArea));
		} else {
			imprimir.println("La cedula del empleado ya existe en el sistema.");
		}
	}
	
	public static void modificarArea() throws IOException {
		String codigo, nombre, descripcion;
		boolean CA;
		imprimir.println("Digite el código de la area funcional a modificar ");
		codigo = leer.readLine();
		imprimir.println("Digite el nuevo nombre de la area funcional");
		nombre = leer.readLine();
		imprimir.println("Digite la nueva descripción de la area funcional");
		descripcion = leer.readLine();
		
		CA = controlador.validarCodigo(codigo, controlador.codidosAreas());
		
		if(CA) {
			imprimir.println(controlador.modificarArea(codigo, nombre, descripcion));
		} else {
			imprimir.println("El codigo del area funcional ya existe en el sistema.");
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
	
	public static void mainAdminListar() throws IOException {
		boolean salir = false;
		int opcion;
		
		while(!salir) {
			menuAdminListar();
			opcion = leerOpcion();
			salir = seleccionarOpcionListar(opcion);
		}
	}
	
	public static boolean seleccionarOpcionListar(int opcion) throws IOException {
		boolean salir = false;
		
		switch(opcion) {
		case 0:
			listarProcesos();
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			listarTareas();
			break;
		case 4:
			break;
		case 5: 
			break;
		case 6:
			break;
		case 7: 
			salir = true;
			break;
		}
		
		return salir;
	}
	
	public static void listarProcesos() {
		String [][] mt = controlador.listarTramite();
		
		for(int i = 0; i < mt.length; i++) {
			imprimir.println("-----------------------------------------");
			imprimir.println("Codigo: " + mt[i][0]);
			imprimir.println("Nombre: " + mt[i][1]);
			imprimir.println("Descripcion: " + mt[i][2]);
			imprimir.println("Fecha de inicio: " + mt[i][3]);
			imprimir.println("Fecha de cierre: " + mt[i][4]);
			imprimir.println("Estado: " + mt[i][5]);
			imprimir.println("-----------------------------------------");
		}
	}
	
	public static void listarTareas() throws IOException {
		String ct = obtenerCodigoProceso();
		String [][] lista = controlador.listarTareas(ct);
		
		for(int i = 0; i < lista.length; i++) {
			imprimir.println("-----------------------------------------");
			imprimir.println("Codigo: " + lista[i][0]);
			imprimir.println("Nombre: " + lista[i][1]);
			imprimir.println("Descripcion: " + lista[i][2]);
			imprimir.println("Estado: " + lista[i][3]);
			imprimir.println("-----------------------------------------");
		}
	}
	
	public static String obtenerCodigoProceso() throws IOException {
		String sct = "";
		boolean bct = false;
		
		while(!bct) {
			imprimir.println("Digite el codigo del proceso");
			sct = leer.readLine();
			bct = controlador.validarCodigo(sct, controlador.codidosTramites());
		}
		
		return sct;
	}

}
