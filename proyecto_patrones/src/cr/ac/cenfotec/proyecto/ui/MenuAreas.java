package cr.ac.cenfotec.proyecto.ui;

import java.util.ArrayList;

public class MenuAreas extends Main {

	@Override
	public void menu() {
		ArrayList<String> pasos = controlador.obtenerNombresPasos(usuario[3]);

		for (int i = 0; i < pasos.size(); i++) {
			int cont = i;
			imprimir.println((++cont) + ". " + pasos.get(i));
		}
	}

	@Override
	public boolean seleccionarOpcion(int opcion) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
