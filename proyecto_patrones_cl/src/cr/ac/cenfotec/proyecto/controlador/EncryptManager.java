package cr.ac.cenfotec.proyecto.controlador;

import java.util.ArrayList;

import cr.ac.cenfotec.proyecto.encript.Firma;
import cr.ac.cenfotec.proyecto.encript.ManagerEncrypttoRC2;

public class EncryptManager {
	
	ManagerEncrypttoRC2 fm=new ManagerEncrypttoRC2();

	
public void createKey(String name) throws Exception {	
	fm.createKey(name);
}

public Object encryptMessage(String messageName, String message, String name) throws Exception {
	
	return fm.encryptMessage(messageName, message, name);
	}

public String decryptMessage(String messageName, String keyName) throws Exception {
	return fm.decryptMessage(messageName, keyName);
	}
	
public String obtenerFirma(String messageName, String message, String name) throws Exception {
	
	Firma firma=(Firma) encryptMessage(messageName,messageName,name);
	
	
	return fm.registrarFirma(firma);
}

public boolean isvalidarFirmaDigital(String firma, String usuario) {
	return validarFirma(firma,usuario);
			
}

private boolean validarFirma(String firma,String usuario) {
	boolean re=false;
ArrayList<Firma>lista=fm.listarfirmas();
	for(Firma var:lista) {
		if(var.getNombre().equals(usuario)&&var.getLlave().equals(firma)) {
			re=true;
		}
	}
	return re;
}
}






