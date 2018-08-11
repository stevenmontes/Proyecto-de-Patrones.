package cr.ac.cenfotec.proyecto.ui;

import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.objetos.Paso;
import cr.ac.cenfotec.proyecto.objetos.Tarea;

public class MenuAreas extends Main {
	private final String areaEncargada = usuario[3];
	private ArrayList<Tarea> tareas;

	@Override
	public void menu() {
		inicializarTareas();

		for (int indTarea = 0; indTarea < tareas.size(); indTarea++) {
			imprimir.println("Tarea: " + tareas.get(indTarea).getNombre());
			ArrayList<Paso> listaPasos = tareas.get(indTarea).getPasos();

			for (int i = 0; i < listaPasos.size(); i++) {
				int cont = i;
				imprimir.println("	" + (++cont) + ". " + listaPasos.get(i).getNombre() + " ("
						+ listaPasos.get(i).getEstado() + ")");
			}
		}
		
		imprimir.println("	0. Salir");
	}

	@Override
	public boolean seleccionarOpcion(int opcion) throws Exception {
		switch (opcion) {
		case 0:
			return true;
		default:
			break;
		}
		
		return false;
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
