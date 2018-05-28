import java.io.Serializable;
import java.util.ArrayList;

public class InformacionServer implements Serializable{
	private static ArrayList<String> datos = new ArrayList<>();
	public static void añadirDato(String dato){
		datos.add(dato);
	}
	public static void eliminarDato(String dato){
		datos.add(dato);
	}
	public static ArrayList<String> getDatos() {
		return datos;
	}
	
}
