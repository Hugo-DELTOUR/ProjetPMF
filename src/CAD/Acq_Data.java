package CAD;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Model.Model;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class Acq_Data implements Runnable{
	Model m;
	private float aTempExt;
	private float aTempInt;
	private float aHumidite;
	private boolean aCporte = false;
	
    public Acq_Data(Model m) throws Exception { 
        this.m = m;  
        this.connect("COM5"); //Déclanchement de la lecture
    }


	//Se connecter au port série
    void connect ( String portName ) throws Exception 
    { 
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName); 
        if ( portIdentifier.isCurrentlyOwned() ) 
        { 
            System.out.println("Erreur: Le port est actuelement en utilisation"); 
        } 
        else 
        { 
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000); 
             
            if ( commPort instanceof SerialPort ) 
            { 
                SerialPort serialPort = (SerialPort) commPort; 
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE); 
                 
                InputStream in = serialPort.getInputStream(); 
                OutputStream out = serialPort.getOutputStream(); 
                 
                RecolterDonnees(in);  //Lecture sur Arduino
                EcrireDonnees(out);	//Ecriture sur Arduino
 
            } 
            else 
            { 
                System.out.println("Error: Only serial ports are handled by this example."); 
            } 
        }
    }
        
    //Ceci est notre thread ;) 
        public void RecolterDonnees (InputStream in) 
        { 
            byte[] buffer = new byte[1024]; 
            int len = -1; 
            try 
            { 
                while ( ( len = in.read(buffer)) > -1 ) 
                { 
                  //Le traitement doit se faire ici
                  String chaine = new String(buffer,0,len);
                  String[] elements = chaine.split(" "); //split avec espace
                  
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
                  //this.aCporte = Integer.parseInt(elements[2]);
                  System.out.println("Envois données vers le Modèle");
                  this.m.setMesures(this.aHumidite,this.aTempInt,this.aTempExt); 

                  }
                } 
            } 
            catch ( IOException e ) 
            { 
                e.printStackTrace(); 
            } catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}             
        }


        //Dans ce code, toute les trente secondes, on va lancer un thread pour récupérer la valeur de la valeur souhaité dans "Modele" et l'injecter dans l'Arduino
		public void EcrireDonnees(OutputStream out) throws InterruptedException {
			
			//if this.m.getTempConsigne() {...} Décider si on envoi ou pas
		            try 
		            {                 
		                int vConsigne = 1; 
		                while ( ( vConsigne = System.in.read()) > -1 ) 
		                { 
		                    out.write(vConsigne); 
		                    //Thread.sleep(5000);
		                }                 
		            } 
		            catch ( IOException e ) 
		            { 
		                e.printStackTrace(); 
		            }             
		        } 
        


		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		} 

}
