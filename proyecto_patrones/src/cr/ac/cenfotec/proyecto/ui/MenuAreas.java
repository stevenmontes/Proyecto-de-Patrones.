package cr.ac.cenfotec.proyecto.ui;

import java.io.IOException;
import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.objetos.Empleado;
import cr.ac.cenfotec.proyecto.objetos.Paso;
import cr.ac.cenfotec.proyecto.objetos.Tarea;

public class MenuAreas extends Main {
	private final String areaEncargada = usuario[3];
	private ArrayList<Tarea> tareas;

	@Override
	public void menu() {
		inicializarTareas();

		for (int indTarea = 0; indTarea < tareas.size(); indTarea++) {
			imprimir.println(tareas.get(indTarea).getNumeroOrden() + "- Tarea: " + tareas.get(indTarea).getNombre());
			ArrayList<Paso> listaPasos = tareas.get(indTarea).getPasos();

			for (int indPaso = 0; indPaso < listaPasos.size(); indPaso++) {
				imprimir.println("	-" + listaPasos.get(indPaso).getNumeroOrden() + ". "
						+ listaPasos.get(indPaso).getNombre() + " (" + listaPasos.get(indPaso).getEstado() + ")");
			}
		}

		imprimir.println("	-0. Salir");
	}

	@Override
	public boolean seleccionarOpcion(int opcion) throws Exception {
		switch (opcion) {
		case 0:
			return true;
		default:
			mostrarPregunta(opcion);
			break;
		}

		return false;
	}

	private void mostrarPregunta(int opcion) throws IOException {
		Paso nuevoPaso = obtenerPasoActual(opcion);
		imprimir.println(nuevoPaso.getDescripcion() + "Y/N");
		nuevoPaso.setRespuesta(leer.readLine().charAt(0));
		nuevoPaso.completar();
		nuevoPaso.finalizarFecha();
		imprimir.println(controlador.modificarPaso(nuevoPaso));
	}

	private Paso obtenerPasoActual(int opcion) {
		for (Tarea tareaActual : tareas) {
			ArrayList<Paso> listaPasos = tareaActual.getPasos();
			
			for(Paso pasoActual: listaPasos) {
				if(pasoActual.getNumeroOrden() == opcion) {
					Empleado encargado = new Empleado();
					encargado.setCedula(usuario[0]);
					pasoActual.setEncargado(encargado);
					pasoActual.iniciarFecha();
					return pasoActual;
				}
			}
		}
		return null;
	}

	private void inicializarTareas() {
		try {
			imprimir.println("----------------------");
			imprimir.println("Obteniendo tareas...");
			imprimir.println("----------------------");
			tareas = controlador.obtenerTareasPorArea(areaEncargada);
		} catch (Exception error) {
			imprimir.println("Error en obtener las tareas");
			error.printStackTrace();
		}
	}

}
