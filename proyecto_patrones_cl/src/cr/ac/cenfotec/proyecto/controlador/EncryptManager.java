package cr.ac.cenfotec.proyecto.controlador;

import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.encript.Firma;
import cr.ac.cenfotec.proyecto.encript.ManagerEncrypttoRC2;

public class EncryptManager {

	ManagerEncrypttoRC2 encriptador = new ManagerEncrypttoRC2();

	public void createKey(String name) throws Exception {
		encriptador.createKey(name);
	}

	public Object encryptMessage(String messageName, String message, String name) throws Exception {

		return encriptador.encryptMessage(messageName, message, name);
	}

	public String decryptMessage(String messageName, String keyName) throws Exception {
		return encriptador.decryptMessage(messageName, keyName);
	}

	public String obtenerFirma(String messageName, String message, String name) throws Exception {

		Firma firma = (Firma) encryptMessage(messageName, messageName, name);

		return encriptador.registrarFirma(firma);
	}

	public boolean isvalidarFirmaDigital(String firma, String usuario) {
		return validarFirma(firma, usuario);

	}

	private boolean validarFirma(String firma, String usuario) {
		boolean re = false;
		ArrayList<Firma> lista = encriptador.listarfirmas();
		for (Firma var : lista) {
			if (var.getNombre().equals(usuario) && var.getLlave().equals(firma)) {
				re = true;
			}
		}
		return re;
	}
}
