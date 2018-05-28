import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainLauncher extends JFrame{
	JPanel container = new JPanel();
	JPanel information = new JPanel();
	JTextField ip = new JTextField(20);
	JTextField port = new JTextField(20);
	JLabel ipL = new JLabel("Ip:    ");
	JLabel portL = new JLabel("Port:");
	JPanel portP = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel ipP = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel infoP = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JLabel serverInformation = new JLabel("Server information");
	JPanel buttonP = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JButton b = new JButton("Enter");
	public MainLauncher() {
		serverInformation.setFont(new Font(Font.SANS_SERIF,  Font.BOLD,22));
		portL.setFont(new Font(Font.SANS_SERIF, Font.BOLD,12));
		ipL.setFont(new Font(Font.SANS_SERIF, Font.BOLD,12));
		this.setTitle("Launcher");
		this.setSize(400, 250);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(container);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(information);
		information.setSize(100,200);
		information.add(infoP);
		information.add(ipP);
		information.add(portP);
		infoP.add(serverInformation);
		ipP.add(ipL);
		ipP.add(ip);
		portP.add(portL);
		portP.add(port);
		port.setText(Integer.toString(Constantes.port));
		ip.setText(Constantes.host);
		container.add(buttonP);
		buttonP.add(b);
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClientBackground.startBackground(Integer.parseInt(port.getText()), ip.getText());
				MainLauncher.this.dispose();
				
				
			}
		});
	
		
		
	}
}
