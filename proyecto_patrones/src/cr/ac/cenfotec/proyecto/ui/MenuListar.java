package cr.ac.cenfotec.proyecto.ui;

import java.io.IOException;

public class MenuListar extends MenuPrincipal{

	@Override
	public void menu() {
		imprimir.println("0. Listar procesos");
		imprimir.println("1. Listar proceso activos");
		imprimir.println("2. Listar proceso completadas");
		imprimir.println("3. Listar tarea de un proceso");
		imprimir.println("4. Listar pasos de una tarea");
		imprimir.println("5. Listar empleados");
		imprimir.println("6. Listar areas funcionales");
		imprimir.println("7. Salir");
	}

	@Override
	public boolean seleccionarOpcion(int opcion) throws Exception {
		if(opcion == 7) {
			return true;
		}
		
		String[] info = obtenerProcesoOTarea(opcion);
		
		for (int i = 0; i < info.length; i++) {
			imprimir.println(info[i]);
		}
		
		return false;
	}
	
	protected String[] obtenerProcesoOTarea(int opcion) throws IOException, Exception {
		String[] info;
		String codigo;
		if (opcion == 3) {
			codigo = obtenerCodigoProceso();
			info = obtenerLista(opcion, codigo);
		}else if (opcion == 4) {
			codigo = obtenerCodigoTarea();
			info = obtenerLista(opcion, codigo);
		} else {
			info = obtenerLista(opcion, "");
		}

		return info;
	}
	
	public String obtenerCodigoProceso() throws IOException {
		String codProceso = "";
		boolean esCodigoValido = false;

		while (!esCodigoValido) {
			imprimir.println("Digite el c\u00f3digo del proceso");
			codProceso = leer.readLine();
			esCodigoValido = isValidarCodigoProceso(codProceso);
		}

		return codProceso;
	}

	protected String obtenerCodigoTarea() throws IOException {
		String codTarea = "";
		boolean validar = false;

		while (!validar) {
			imprimir.println("Digite el c\u00f3digo de la tarea");
			codTarea = leer.readLine();
			validar = isValidarCodigoTarea(codTarea);
		}

		return codTarea;
	}
	
	public  String[] obtenerLista(int opcion, String codigo) throws Exception {
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

}
