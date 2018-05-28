import java.io.Serializable;

public class Usuario implements Serializable{
	private String nombre;
	private String ip;

	public Usuario(String nombre,String ip ){
		this.ip = ip;
		this.nombre = nombre;
		
	}

	public String getIp() {
		return ip;
	}

	public String getNombre() {
		return nombre;
	}


	
}
