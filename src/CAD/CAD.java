package CAD;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Model.Model;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class CAD implements Runnable {
	Model m;
	private float aTempExt;
	private float aTempInt;
	private float aHumidite;
	private boolean aCporte = false;
	private CommPortIdentifier portIdentifier;
	private CommPort commPort;

	public CAD(Model m) throws Exception {
		this.m = m;
		this.connect(); // D�clanchement de la lecture
		this.portIdentifier = portIdentifier;
		this.commPort = commPort;
	}

	// Se connecter au port s�rie
	void connect() throws Exception {
			portIdentifier = CommPortIdentifier.getPortIdentifier("COM5");
			
			if (this.portIdentifier.isCurrentlyOwned()) {
				System.out.println("Erreur: Le port est actuelement en utilisation");
			} else {
				InitialiserConnexion();
			}
	}
	
	//Initialise la premi�re connexion
	private void InitialiserConnexion() throws Exception {
		commPort = portIdentifier.open(this.getClass().getName(), 2000);
		if (commPort instanceof SerialPort) {
			SerialPort serialPort = (SerialPort) commPort;
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			InputStream in = serialPort.getInputStream();
			OutputStream out = serialPort.getOutputStream();
			RecolterDonnees(in); //On r�colte les donn�es la premi�re fois
		}	
	}

	/** D�cision si l'on souhaite �crire ou lire, �crire �tant � True*/
	private void choixAction(Boolean choixAction) throws Exception {
		if (commPort instanceof SerialPort) {
			SerialPort serialPort = (SerialPort) commPort;
			//serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE); --> On retire l'initialisation
			InputStream in = serialPort.getInputStream();
			OutputStream out = serialPort.getOutputStream();
			/*ECRITURE OU LECTURE*/
			if (choixAction.equals(false)) { // Premier passage dans la lecture
				RecolterDonnees(in);		 //Lecture	
			}else {
				EcrireDonnees(out); 		// Ecriture
			}
		}
	}

	// Ceci est notre thread ;)
	public void RecolterDonnees(InputStream in) throws Exception {
		byte[] buffer = new byte[1024];
		int len = -1;
		try {
			while ((len = in.read(buffer)) > -1) {
				// Le traitement doit se faire ici
				String chaine = new String(buffer, 0, len);
				String[] elements = chaine.split(" "); // split avec espace

				// contient ["H Ti Te"]
				Thread.sleep(2000);
				if (elements.length == 3) {
					System.out.println(chaine);
					this.aHumidite = Float.parseFloat(elements[0]);
					Thread.sleep(1000);
					this.aTempInt = Float.parseFloat(elements[1]);
					Thread.sleep(1000);
					this.aTempExt = Float.parseFloat(elements[2]);
					Thread.sleep(1000);
					// this.aCporte = Integer.parseInt(elements[2]);
					System.out.println("Envois donn�es vers le Mod�le");
					this.m.setMesures(this.aHumidite, this.aTempInt, this.aTempExt);
					choixAction(true); // Declanche une �criture
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void EcrireDonnees(OutputStream out) throws Exception {
		if (this.m.getTempConsigne() != 0) {
			int vConsigne = (int) this.m.getTempConsigne();
			try {
				System.out.println("J'�cris dans la carte : " + this.m.getTempConsigne());
				
				out.write(vConsigne);	
				choixAction(false); // Declanche une �criture
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}