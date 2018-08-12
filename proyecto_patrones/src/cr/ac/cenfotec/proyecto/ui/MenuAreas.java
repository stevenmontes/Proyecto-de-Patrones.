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
		imprimir.println(tareaAsignada.getNumeroOrden() + "- Tarea: " + tareaAsignada.getNombre());
		ArrayList<Paso> listaPasos = tareaAsignada.getPasos();

		for (int indPaso = 0; indPaso < listaPasos.size(); indPaso++) {
			imprimir.println("	-" + listaPasos.get(indPaso).getNumeroOrden() + ". "
					+ listaPasos.get(indPaso).getNombre() + " (" + listaPasos.get(indPaso).getEstado() + ")");
		}
		
		imprimir.println("	-0. Salir");
		siTareaEstaCompleta();
	}

	private void siTareaEstaCompleta() {
		if (!controlador.validarEstadoPaso(tareaAsignada.getEstado())) {
			imprimir.println("--------------------------------");
			imprimir.println("Los pasos de esta tarea estan completos. \n"
					+ " Ya no queda ningun paso pendiente.");
			imprimir.println("--------------------------------");
		}
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

	private void mostrarPregunta(int opcion) throws IOException {
		Paso nuevoPaso = buscarPasoActual(opcion);
		if (validarPaso(nuevoPaso.getNumeroOrden())) {
			imprimir.println(nuevoPaso.getDescripcion() + " (Y/N)");
			ingresarCambiosPasos(leer.readLine().charAt(0), nuevoPaso);
			imprimir.println(controlador.modificarPaso(nuevoPaso));

			if (buscarEstadoPaso(opcion + 1) == null) {
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
		String estado = buscarEstadoPaso(numeroOrden);
		return controlador.validarEstadoPaso(estado);
	}

	private Paso buscarPasoActual(int opcion) {
		ArrayList<Paso> listaPasos = tareaAsignada.getPasos();

		for (Paso pasoActual : listaPasos) {
			if (pasoActual.getNumeroOrden() == opcion) {
				return pasoActual;
			}
		}

		return null;
	}

	private String buscarEstadoPaso(int numOrden) {
		ArrayList<Paso> listaPasos = tareaAsignada.getPasos();

		for (int i = 0; i < listaPasos.size(); i++) {
			if (listaPasos.get(i).getNumeroOrden() == numOrden) {
				return listaPasos.get(i).getEstado();
			}
		}

		return null;
	}

	private void inicializarTareas() {
		try {
			imprimir.println("----------------------");
			imprimir.println("Obteniendo tarea...");
			imprimir.println("----------------------");
			tareaAsignada = controlador.obtenerTareasPorArea(areaEncargada);
		} catch (Exception error) {
			imprimir.println("Error en obtener las tareas");
			error.printStackTrace();
		}
	}
}
