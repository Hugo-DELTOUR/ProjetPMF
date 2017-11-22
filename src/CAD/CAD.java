package CAD;
import java.io.InputStream; 
import java.io.OutputStream;

import Model.Model;
import gnu.io.CommPort; 
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort; 
 
public class CAD 
{ 
	private float actempExt = 0;
	private float actempInt = 0;
	private float achumidite = 0;
	private boolean acporte = false;
	
    public CAD() 
    { 
        super();
        //Première initialisation
        /*
        m.setTempExt(actempExt);
        m.setTempInter(actempInt);
        m.setHumidite(achumidite);
        m.setPorte(acporte);
        */
        
    } 
    
    public void recuperationArduino() {
    	while (1 == 1) {
    		/*
    	 m.setTempExt(actempExt);
    	 m.setTempInter(actempInt);
    	 m.setHumidite(achumidite);
    	 m.setPorte(acporte);
    	 */
    	}
    }
     
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
                 
                (new Thread(new SerialReader(in,this))).start();  //Lecture
                (new Thread(new SerialWriter(out))).start(); 
 
            } 
            else 
            { 
                System.out.println("Error: Only serial ports are handled by this example."); 
            } 
        }
        
    } 
     

    public float getAtempExt() {
		return actempExt;
	}

	public void setAtempExt(float atempExt) {
		this.actempExt = atempExt;
	}

	public float getAtempInt() {
		return actempInt;
	}

	public void setAtempInt(float atempInt) {
		this.actempInt = atempInt;
	}

	public float getAhumidite() {
		return achumidite;
	}

	public void setAhumidite(float ahumidite) {
		this.achumidite = ahumidite;
	}

	public boolean isAporte() {
		return acporte;
	}

	public void setAporte(boolean aporte) {
		this.acporte = aporte;
	}

	public static void main ( String[] args ) 
    { 
		//Model m;
		CAD cad = new CAD();
        try 
        { 
        	(cad).connect("COM5"); //On sélectionne le COM
        	
        	//while (1 == 1) {
            //System.out.println(cad.getAtempExt());
            //System.out.println(cad.getAhumidite());
            //System.out.println(cad.getAtempInt());
        	//}
        } 
        catch ( Exception e ) 
        { 
            e.printStackTrace(); 
        } 
    } 
}