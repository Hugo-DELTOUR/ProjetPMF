package CAD;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Model.Model;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class CAD implements Runnable {
	Model m;
	private float aTempExt;
	private float aTempInt;
	private float aHumidite;
	private CommPortIdentifier portIdentifier;
	private CommPort commPort;

	public CAD(Model m) throws Exception {
		this.m = m;
		this.connect(); // Déclanchement de la lecture
	}

	// Se connecter au port série
	/**
	 * Permet de définir le port de connexion, et de vérifier si le port est en cours d'utilisation
	 * @throws Exception
	 */
	void connect() throws Exception {
			portIdentifier = CommPortIdentifier.getPortIdentifier("COM5");
			
			if (this.portIdentifier.isCurrentlyOwned()) {
				System.out.println("Erreur: Le port est actuelement en utilisation");
			} else {
				InitialiserConnexion();
			}
	}
	
	/**
	 * Cette méthode est appellée par connect(), elle permet d'initialiser la connexion, et de la lancer en mode "Lecture"
	 * @throws Exception
	 */
	//Initialise la première connexion
	private void InitialiserConnexion() throws Exception {
		commPort = portIdentifier.open(this.getClass().getName(), 2000);
		if (commPort instanceof SerialPort) {
			SerialPort serialPort = (SerialPort) commPort;
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			InputStream in = serialPort.getInputStream();
			RecolterDonnees(in); //On récolte les données la première fois
		}	
	}

/**
 * Cette méthode permet de savoir si on souhaite écrire ou lire sur notre carte
 * @param choixAction
 * @throws Exception
 */
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
	/**
	 * RecolterDonnees permet de lire les données. Elle récupère la trame envoyé sous arduino, pour l'insérer dans un tableau, et l'instancie dans le modèle
	 * @param in
	 * @throws Exception
	 */
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
					this.aHumidite = Float.parseFloat(elements[0]);
					Thread.sleep(1000);
					this.aTempInt = Float.parseFloat(elements[1]);
					Thread.sleep(1000);
					this.aTempExt = Float.parseFloat(elements[2]);
					Thread.sleep(1000);
					// this.aCporte = Integer.parseInt(elements[2]);
					System.out.println("\n Envois données vers le Modèle");
					this.m.setMesures(this.aHumidite, this.aTempInt, this.aTempExt);
					choixAction(true); // Declanche une écriture
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Ecrire données permet d'écrire sur la carte
	 * @param out
	 * @throws Exception
	 */
	public void EcrireDonnees(OutputStream out) throws Exception {
		if (this.m.getTempConsigne() != 0) {
			int vConsigne = (int) this.m.getTempConsigne();
			try {
				System.out.println("T° Consigne vers carte: " + this.m.getTempConsigne());
				out.write(vConsigne);	
				choixAction(false); // Declanche une écriture
				
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
