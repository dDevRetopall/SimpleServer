import java.io.Serializable;
import java.util.ArrayList;

public class Usuarios implements Serializable{
	private static Usuario u;
	public static ArrayList<Usuario> usuarios= new ArrayList<>();
	public static void añadirUsuario(Usuario u){
		usuarios.add(u);
		System.out.println("Usuario agregado. Total users:"+usuarios.size());
	}
	public static void eliminarUsuario(Usuario u){
		usuarios.remove(u);
		System.out.println("Usuario eliminado. Remaining:"+usuarios.size());
		
	}
	public static ArrayList<Usuario> getUsuarios(){
		System.out.println("Devolviendo usuarios. Total users:"+usuarios.size());
		return usuarios;
		
	}
}
