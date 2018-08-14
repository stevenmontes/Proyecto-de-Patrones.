package cr.ac.cenfotec.proyecto.encript;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import cr.ac.cenfotec.proyecto.conexion.Conector;

public class ManagerEncrypttoRC2 implements FactoryManager {
	private final int KEYSIZE = 8;
	private final String KEY_EXTENSION = ".key";
	private final String MESSAGE_ENCRYPT_EXTENSION = ".encript";
	private final String PATH = "C:/encrypt/RC2/";

	@Override
	public void createKey(String name) throws Exception {
		byte[] key = generatedSequenceOfBytes();
		writeBytesFile(name, key, KEY_EXTENSION);
	}

	@Override
	public Object encryptMessage(String messageName, String message, String keyName) throws Exception {
		byte[] key = readKeyFile(keyName);
		Cipher cipher = Cipher.getInstance("RC2");
		SecretKeySpec k = new SecretKeySpec(key,"RC2");
		cipher.init(Cipher.ENCRYPT_MODE,k);
		byte[] encryptedData = cipher.doFinal(message.getBytes());
		Encoder oneEncoder = Base64.getEncoder();
		encryptedData = oneEncoder.encode(encryptedData);
		writeBytesFile(messageName, encryptedData, MESSAGE_ENCRYPT_EXTENSION);
		String data=new String(encryptedData);
		return new Firma (keyName,messageName,data);
	}

	@Override
	public String decryptMessage(String messageName, String keyName) throws Exception {
		byte[] key = readKeyFile(keyName);
		Cipher cipher = Cipher.getInstance("RC2");
		SecretKeySpec k = new SecretKeySpec(key,"RC2");
		byte[] encryptedMessage = readMessageFile(messageName);
		System.out.println(encryptedMessage.length);
		cipher.init(Cipher.DECRYPT_MODE, k);
		byte[] DecryptedData = cipher.doFinal(encryptedMessage);
		String message = new String(DecryptedData);

		return "The message was: "+message;
	

	}

	@Override
	public void writeBytesFile(String name, byte[] content, String type) throws IOException {
		FileOutputStream fos = new FileOutputStream(PATH + name + type);
		fos.write(content);
		fos.close();
	}

	@Override
	public byte[] readMessageFile(String messageName) throws Exception {
		File file = new File(PATH + messageName + MESSAGE_ENCRYPT_EXTENSION);
		int length = (int) file.length();
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
		byte[] bytes = new byte[length];
		reader.read(bytes, 0, length);
		Decoder oneDecoder = Base64.getDecoder();
		reader.close();
		return oneDecoder.decode(bytes);
	}

	private byte[] readKeyFile(String keyName) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(PATH + keyName + KEY_EXTENSION));
		String everything = "";
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			everything = sb.toString();
		} finally {
			br.close();
		}
		return everything.getBytes(StandardCharsets.UTF_8);
	}

	private byte[] generatedSequenceOfBytes() throws Exception {
		StringBuilder randomkey = new StringBuilder();
		for (int i = 0; i < KEYSIZE; i++) {
			randomkey.append(Integer.parseInt(Double.toString((Math.random() + 0.1) * 1000).substring(0, 2)));
		}
		return randomkey.toString().getBytes("UTF-8");
	}

	public String registrarFirma(Firma f) {
		String mensaje;
		String consulta = "{Call dbo.pa_registrar_firma ('"+f.getLlave()+"','"+f.getMensaje()+"','"+f.getNombre()+"')}";
		 try {
             Conector.getConector().ejecutarSQL(consulta);
             mensaje = "La firma se registró correctamente en el sistema.";
     } catch (Exception ex) {
    	 	 mensaje = "No se pudo registrar la firma, intentelo de nuevo " + ex.getMessage();
     }
	   return mensaje;
	}

	public ArrayList<Firma> listarfirmas() {
	ArrayList<Firma>lista=new ArrayList<>();;
		   String consulta = "{Call dbo.listarFirmas }";

	        try {
	        	ResultSet rs = Conector.getConector().ejecutarSQL(consulta, true);
	        	while(rs.next()) {
	         Firma f=new Firma(rs.getString("nombre"),
	        		 		   rs.getString("llave"),rs.getString("firma"));
	        		lista.add(f);
	        	}
	        } catch (Exception ex) {
	        }
		return lista;
	}

}
