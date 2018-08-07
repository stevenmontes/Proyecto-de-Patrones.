package cr.ac.cenfotec.proyecto.ui;

import java.io.IOException;

public class MenuRegistrar extends MenuPrincipal {

	@Override
	public void menu() {
		imprimir.println("1. Registrar proceso");
		imprimir.println("2. Registrar tarea");
		imprimir.println("3. Registrar pasos");
		imprimir.println("4. Registrar empleados");
		imprimir.println("5. Registrar \u00e1reas funcionales");
		imprimir.println("6. Salir");
	}

	@Override
	public boolean seleccionarOpcion(int opcion) throws Exception {
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

	public void obtenerInfoTramite() throws IOException {
		String codigo = solicitarDatoString("Digite el c\u00f3digo");
		String nombre = solicitarDatoString("Digite el nombre");
		String descripcion = solicitarDatoString("Digite el descripci\u00f3n");

		if (isValidarCodigoProceso(codigo)) {
			imprimir.println(controlador.registrarTramite(codigo, nombre, descripcion));
		} else {
			imprimir.println("C\u00f3digo del proceso esta repetido. Vuelve a intentarlo.");
		}
	}

	public void obtenerInfoTarea() throws IOException {
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

	public void obtenerInfoPaso() throws IOException {
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

	public void obtenerInfoEmpleado() throws IOException {
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

	public void obtenerInfoArea() throws IOException {
		String codigo = solicitarDatoString("Digite el c\u00f3digo.");
		String nombre = solicitarDatoString("Digite el nombre.");
		String descripcion = solicitarDatoString("Digite la descripci\u00f3n.");

		if (!isValidarCodigoAreaFuncional(codigo)) {
			imprimir.println(controlador.registrarArea(codigo, nombre, descripcion));
		} else {
			imprimir.println("El c\u00f3digo del \u00e1rea funcional ya existe en el sistema.");
		}
	}

}
