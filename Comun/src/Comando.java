import java.io.Serializable;

public class Comando implements Serializable{
	private String mensaje;
	private boolean display;
	private boolean reset;

	public Comando(String mensaje,boolean display){
		this.mensaje = mensaje;
		this.display = display;
		
	}
	public Comando(boolean reset){
		this.reset = reset;
	
	
	}

	public String getMensaje() {
		return mensaje;
	}

	public boolean isDisplay() {
		return display;
	}
	public boolean isReset() {
		return reset;
	}
	
	
}
