package cr.ac.cenfotec.proyecto.encript;

public class Firma {
private String nombre;
private String llave;
private String mensaje;




public Firma(String nombre, String llave, String mensaje) {
	this.nombre = nombre;
	this.llave = llave;
	this.mensaje = mensaje;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getLlave() {
	return llave;
}
public void setLlave(String llave) {
	this.llave = llave;
}
public String getMensaje() {
	return mensaje;
}
public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
}

}
