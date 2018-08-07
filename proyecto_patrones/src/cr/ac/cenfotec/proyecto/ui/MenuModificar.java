package cr.ac.cenfotec.proyecto.ui;

import java.io.IOException;

public class MenuModificar extends MenuPrincipal{

	@Override
	public void menu() {
		imprimir.println("1. Modificar proceso");
		imprimir.println("2. Modificar tarea");
		imprimir.println("3. Modificar pasos");
		imprimir.println("4. Modificar empleados");
		imprimir.println("5. Modificar \u00e1reas funcionales");
		imprimir.println("6. Modificar estado \u00e1rea funcional");
		imprimir.println("7. Salir");
	}

	@Override
	public boolean seleccionarOpcion(int opcion) throws Exception {
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
	
	private void modificarEstadoArea() throws IOException {
		imprimir.println("Digite el código del departamento al que desea cambiarle el estado");
		String codigo = leer.readLine();

		imprimir.println(controlador.modificarEstadoDepartamento(codigo));
	}

	public void modificarProceso() throws IOException {
		String codigo = solicitarDatoString("Digite el nuevo c\u00f3digo");
		String nombre = solicitarDatoString("Digite el nuevo nombre");
		String descripcion = solicitarDatoString("Digite la nueva descripci\u00f3n");

		if (isValidarCodigoProceso(codigo)) {
			imprimir.println(controlador.modificarTramite(codigo, nombre, descripcion));
		} else {
			imprimir.println("No existe el c\u00f3digo del proceso. vuelve a intentarlo");
		}
	}

	public void modificarTarea() throws IOException {
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

	public void modificarPaso() throws IOException {
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

	public void modificarEmpleado() throws IOException {
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

	public void modificarArea() throws IOException {
		String codigo = solicitarDatoString("Digite el nuevo c\u00f3digo.");
		String nombre = solicitarDatoString("Digite el nuevo nombre.");
		String descripcion = solicitarDatoString("Digite la nueva descripci\u00f3n.");

		if (isValidarCodigoAreaFuncional(codigo)) {
			imprimir.println(controlador.modificarArea(codigo, nombre, descripcion));
		} else {
			imprimir.println("El c\\u00f3digo de la area funcional ya existe en el sistema.");
		}
	}

}
