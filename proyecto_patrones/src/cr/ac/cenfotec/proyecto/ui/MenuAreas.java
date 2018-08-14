package cr.ac.cenfotec.proyecto.ui;

import java.io.IOException;
import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.objetos.Empleado;
import cr.ac.cenfotec.proyecto.objetos.Paso;
import cr.ac.cenfotec.proyecto.objetos.Tarea;

public class MenuAreas extends Main {
	private final String areaEncargada = usuario[3];
	private Tarea tareaAsignada;

	@Override
	public void menu() {
		inicializarTareas();
		if (!esTareaAnteriorValida()) {
			if (controlador.validarEstado(tareaAsignada.getEstado())) {
				imprimir.println(tareaAsignada.getNumeroOrden() + "- Tarea: " + tareaAsignada.getNombre());
				ArrayList<Paso> listaPasos = tareaAsignada.getPasos();

				for (int indPaso = 0; indPaso < listaPasos.size(); indPaso++) {
					imprimir.println("	-" + listaPasos.get(indPaso).getNumeroOrden() + ". "
							+ listaPasos.get(indPaso).getNombre() + " (" + listaPasos.get(indPaso).getEstado() + ")");
				}

			} else {
				imprimir.println("--------------------------------");
				imprimir.println("Los pasos de esta tarea estan completos. \n" + " Ya no queda ningun paso pendiente.");
				imprimir.println("--------------------------------");
			}

		} else {
			imprimir.println("Se debe completar la tarea anterior para completar esta tarea");
		}

		imprimir.println("	-0. Salir");
	}

	@Override
	public boolean seleccionarOpcion(int opcion) throws Exception {
		switch (opcion) {
		case 0:
			return true;
		case 1:
			mostrarPregunta(opcion);
			break;
		default:
			if (!validarPaso(opcion - 1)) {
				mostrarPregunta(opcion);
			} else {
				imprimir.println("Primero se debe completar el pase anterior," + " para completar este paso");
			}
			break;
		}

		return false;
	}

	private boolean esTareaAnteriorValida() {
		if (tareaAsignada.getNumeroOrden() == 1) {
			return false;
		}

		int idProceso;
		try {
			idProceso = controlador.obtenerProcesoDeTarea(tareaAsignada.getCodigo());
			ArrayList<Tarea> lista = controlador.obtenerTareasProceso(idProceso);

			for (Tarea tareaActual : lista) {
				if (tareaActual.getNumeroOrden() == tareaAsignada.getNumeroOrden() - 1) {
					return controlador.validarEstado(tareaActual.getEstado());
				}
			}

			return false;

		} catch (Exception error) {
			imprimir.println("ERROR al validar la tarea anterior.");
			error.printStackTrace();
		}

		return false;
	}

	private void mostrarPregunta(int opcion) throws IOException {
		Paso nuevoPaso = buscarPasoActual(opcion);
		if (controlador.validarEstado(nuevoPaso.getEstado())) {
			imprimir.println(nuevoPaso.getDescripcion() + " (Y/N)");
			ingresarCambiosPasos(leer.readLine().charAt(0), nuevoPaso);
			imprimir.println(controlador.modificarPaso(nuevoPaso));

			if (buscarPasoActual(opcion + 1) == null) {
				tareaAsignada.completar();
				imprimir.println(
						controlador.registrarEstadoTarea(tareaAsignada.getCodigo(), tareaAsignada.getEstado()));
			}
		} else {
			imprimir.println("El paso esta completado.");
		}

	}

	private void ingresarCambiosPasos(char respuesta, Paso nuevoPaso) {
		Empleado encargado = new Empleado();
		encargado.setCedula(usuario[0]);
		nuevoPaso.setEncargado(encargado);
		nuevoPaso.iniciarFecha();
		nuevoPaso.setRespuesta(respuesta);
		nuevoPaso.completar();
		nuevoPaso.finalizarFecha();

	}

	private boolean validarPaso(int numeroOrden) {
		return controlador.validarEstado(buscarPasoActual(numeroOrden).getEstado());
	}

	private Paso buscarPasoActual(int numOrden) {
		for (Paso pasoActual : tareaAsignada.getPasos()) {
			if (pasoActual.getNumeroOrden() == numOrden) {
				return pasoActual;
			}
		}

		return null;
	}

	private void inicializarTareas() {
		try {
			imprimir.println("Obteniendo tarea...\n----------------------");
			tareaAsignada = controlador.obtenerTareasPorArea(areaEncargada);
		} catch (Exception error) {
			imprimir.println("Error en obtener las tareas");
			error.printStackTrace();
		}
	}
}
