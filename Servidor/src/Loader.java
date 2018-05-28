import java.util.ArrayList;

import javax.swing.JFrame;

public class Loader extends ArrayList<String>{
	String[]mensajes;
	public Loader(){
		
	}
	public String[] getMessages(){
		mensajes= new String[this.size()];
		for(int i=0;i<this.size();i++){
			mensajes[i]=this.get(i);
			System.out.println(this.get(i));
		}

		return mensajes;
	} 
	public void addMessage(String message){
		this.add(message);
		System.out.println(this.size());
	}
}
