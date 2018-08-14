package cr.ac.cenfotec.proyecto.encript;

import java.io.IOException;

public interface FactoryManager {

	void createKey(String name) throws Exception;

	Object encryptMessage(String messageName, String message, String keyName) throws Exception;

	String decryptMessage(String messageName, String keyName) throws Exception;

	void writeBytesFile(String name, byte[] content, String type) throws IOException;

	byte[] readMessageFile(String messageName) throws Exception;

}
