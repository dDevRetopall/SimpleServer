import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaCliente extends JFrame {
	JPanel p = new JPanel(new BorderLayout());
	JPanel pNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JTextArea ta = new JTextArea(20, 35);
	JTextArea ta2 = new JTextArea(10, 35);
	JButton b = new JButton("Enviar mensaje");
	JButton connect = new JButton("Connect");
	JScrollPane sp = new JScrollPane(ta);
	JScrollPane sp2 = new JScrollPane(ta2);
	String usuarios="";
	String informacionServer="";
	
	public VentanaCliente() {
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Cliente - Ventana");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		sp. setVerticalScrollBarPolicy( JScrollPane. VERTICAL_SCROLLBAR_ALWAYS ); 
		sp2. setVerticalScrollBarPolicy( JScrollPane. VERTICAL_SCROLLBAR_ALWAYS ); 
		ta2.setText("Informacion \nUsuarios:\n\n \nInformacion del Servidor:\n\n");
		ta.setFont(new Font("Action Man Extended", Font.PLAIN, 14));
		ta2.setFont(new Font("Action Man Extended", Font.PLAIN, 14));
		pNorth.add(b);
		pNorth.add(connect);
		p.add(pNorth, BorderLayout.NORTH);
		p.add(sp,BorderLayout.CENTER);
		p.add(sp2,BorderLayout.EAST);
		b.setEnabled(false);
		this.setSize(800, 400);
		ta.setEditable(false);	
		this.setContentPane(p);
		
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ClientBackground.enviar();

			}
		});
		connect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(connect.getText().equals("Connect")){
					connect.setText("Disconnect");
					ClientBackground.connect();

				}else{
					connect.setText("Connect");
					ClientBackground.disconnect();
					ClientBackground.nuevaAplicacion();
					
				}
				
			}
		});
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
			//	MainCliente.disconnect();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				 
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		

	}
	public void añadirUsuario(Usuario u){
		usuarios=usuarios+u.getNombre()+" :  "+u.getIp()+"\n";
		update();
	}
	public void añadirDato(String s){
		informacionServer= informacionServer+s+"\n";
		update();
	}
	public void update(){
		ta2.setText("Informacion \nUsuarios:\n" +usuarios+"\nInformacion del Servidor:\n"+informacionServer+"\n");
	}
	public void reset() {
		usuarios="";
		informacionServer="";
		
	}
}
