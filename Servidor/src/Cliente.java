import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
	InputStream is ;
	OutputStream os;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	String username="anonymous";
	boolean connected=true;
	Usuario este ;
	String ip;
	
	public Cliente(Socket s){
		System.out.println("Cliente conectado");
		try {
			is = s.getInputStream();
			os = s.getOutputStream();
			oos = new ObjectOutputStream(os);
			ois = new ObjectInputStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(connected){
			
					try {
						Object o = ois.readObject();
						if(o instanceof Mensaje){
							System.out.println("Mensaje recibido: "+((Mensaje) o).getMensaje());
							MainServidor.enviarMensajeATodos(username+" >> "+((Mensaje) o).getMensaje());
							MainServidor.l.addMessage(username+" >> "+((Mensaje) o).getMensaje());
						}
						if(o instanceof Usuario){
							System.out.println("Usuario recibido: "+((Usuario) o).getNombre());
							username=((Usuario) o).getNombre();
							este= (Usuario)o;
							ip = este.getIp();
							
							enviarComando("El servidor ha aceptado tu peticion de conectar", true);
							
							Usuarios.añadirUsuario((Usuario)o);
							enviarInformacion();
							MainServidor.enviarUsuarioATodos();
							
							
							String[]s=MainServidor.l.getMessages();
							String mensaje = "";
							enviarMensaje("Descargando mensajes ("+s.length+")");
							for(int  i =0;i<s.length;i++){
								if(i==s.length-1){
								mensaje=mensaje+s[i];
								}else{
								mensaje=mensaje+s[i]+"\n";
								}
							}
							enviarMensaje("Mensajes descargados");
							if(!mensaje.equals("")){
							enviarMensaje(mensaje);
							}
							MainServidor.enviarMensajeATodos(username+" se ha conectado");
							MainServidor.l.addMessage(username+" se ha conectado");
						}
					} catch (ClassNotFoundException e) {
						connected=false;
						Usuarios.eliminarUsuario(este);
						MainServidor.enviarUsuarioATodos();
						MainServidor.enviarMensajeATodos(username+" se ha desconectado");
						
					} catch (IOException e) {
						connected=false;
						Usuarios.eliminarUsuario(este);
						MainServidor.enviarUsuarioATodos();
						MainServidor.enviarMensajeATodos(username+" se ha desconectado");
						
					}
				}
				
			}
		});
		t.start();
	}
	public void enviarMensaje(String mensaje){
		
			try {
				oos.writeObject(new Mensaje(mensaje));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	public void enviarComando(String mensaje,boolean display){
		
		try {
			oos.writeObject(new Comando(mensaje,display));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
public void enviarReset(boolean reset){
		
		try {
			oos.writeObject(new Comando(reset));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	public void enviarUsuario(Usuario u){
		
		try {
			oos.writeObject(u);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
public void enviarInformacion(){
		
		try {
			oos.writeObject(InformacionServer.getDatos());
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}		
		
	
	
}
