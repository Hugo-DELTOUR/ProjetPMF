package CAD;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Model.Model;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class Acq_Data{
	Model m;
	private float aTempExt;
	private float aTempInt;
	private float aHumidite;
	private boolean aCporte = false;
	
    public Acq_Data(Model m) throws Exception { 
        this.m = m;  
        this.connect("COM5");
        
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
                 
                RecolterDonnees(in);  //Lecture
 
            } 
            else 
            { 
                System.out.println("Error: Only serial ports are handled by this example."); 
            } 
        }
    }
        

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
                  System.out.print(chaine);
                  String[] elements = chaine.split(" "); //split avec espace
                  // contient ["H Ti Te"]
                  int i = 0;
                  if (elements.length > 1) {
                  for (i = 0; i < elements.length; i++) {
                	  
                		switch (i) {
                			case 0 :
                				//this.ahumidite = Float.parseFloat(elements[0]);
                				//System.out.println(this.ahumidite);
                				//System.out.println(cad.getAhumidite());	
                			break;
                			case 1 :
                				//this.atempInt = Float.parseFloat(elements[1]);
                			break;
                			case 2 :
                				//this.atempExt = Float.parseFloat(elements[2]);
                			break;
                		}
                	  }
                  }
                  Thread.sleep(5000);
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

}
