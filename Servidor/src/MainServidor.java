import java.awt.HeadlessException;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainServidor {
	static ServerSocket ss;
	static ArrayList<Cliente> clientes = new ArrayList<>();
	static Loader l = new Loader();

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			ss = new ServerSocket(Constantes.port);
			System.out.println("Servidor ejecutando");
		} catch (IOException e) {
			System.out.println("No se ha podido crear la conexion");
			System.exit(0);
		}

		InetAddress ip;
		System.out.println(ss.getLocalPort());
		try {
			JOptionPane.showMessageDialog(null, "Comparte este puerto para que se habilite la conexion:\n "
					+ ss.getLocalPort() + "\n" + "Server ip: " + InetAddress.getLocalHost().getHostAddress());
		} catch (HeadlessException e1) {

			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			
			e1.printStackTrace();
		}
		try {

			ip = InetAddress.getLocalHost();
			InformacionServer.añadirDato("IP del server: " + ip);
			System.out.println("Current IP address : " + ip.getHostAddress());

		} catch (UnknownHostException e) {

			e.printStackTrace();

		}
		String s = "Puerto: " + Integer.toString(ss.getLocalPort());
		InformacionServer.añadirDato(s);
		InformacionServer.añadirDato("Made all with Java");
		InformacionServer.añadirDato("High security systems");
		InformacionServer.añadirDato("Server running on " + System.getProperty("os.name"));
		InformacionServer.añadirDato("Java version: " + System.getProperty("java.version"));

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Socket s = ss.accept();
						clientes.add(new Cliente(s));
						System.out.println("Cliente aceptado");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	public static void enviarMensajeATodos(String mensaje) {
		System.out.println("Mensaje nuevo: " + mensaje);
		for (Cliente c : MainServidor.clientes) {
			System.out.println("Enviado a una persona el mensaje");
			c.enviarMensaje(mensaje);
		}
	}

	public static void enviarUsuarioATodos() {
		Iterator<Cliente> iterator = clientes.iterator();
		while (iterator.hasNext()) {
			Cliente c = iterator.next();
			if (c.connected) {
				System.out.println("Enviado a una persona el usuario (" + MainServidor.clientes.size() + ")");
				c.enviarReset(true);
				for (int i = 0; i < Usuarios.usuarios.size(); i++) {

					c.enviarUsuario(Usuarios.usuarios.get(i));

				}
				c.enviarInformacion();

			} else {

				iterator.remove();
				System.out
						.println("Cliente eliminado correctamente. Sockets actuales: " + MainServidor.clientes.size());

			}
		}

	}

}
