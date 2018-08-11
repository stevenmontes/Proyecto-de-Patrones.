package cr.ac.cenfotec.proyecto.ui;

public class MenuAdministrador extends MenuPrincipal {

	@Override
	public void menu() {
		imprimir.println("1.REGISTRAR");
		imprimir.println("2.MODIFICAR");
		imprimir.println("3.LISTAR");
		imprimir.println("4.SALIR");
	}
	
	@Override
	public boolean seleccionarOpcion(int opcion) throws Exception {
		MenuPrincipal nuevoMenu;

		switch (opcion) {
		case 1:
			nuevoMenu = new MenuRegistrar();
			nuevoMenu.ejecutar();
			break;
		case 2:
			nuevoMenu = new MenuModificar();
			nuevoMenu.ejecutar();
			break;
		case 3:
			nuevoMenu = new MenuListar();
			nuevoMenu.ejecutar();
			break;
		case 4:
			return true;
		default:
			imprimir.println("Comando inv\u00e1lido");
			break;
		}

		return false;
	}
}
