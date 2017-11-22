package CAD;
import java.io.InputStream; 
import java.io.OutputStream; 
 
import gnu.io.CommPort; 
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort; 
 
public class CAD 
{ 
	
	private float actempExt;
	private float actempInt;
	private float achumidite;
	private boolean acporte;
	
    public CAD() 
    { 
        super();
        
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
        try 
        { 
            (new CAD()).connect("COM5"); //On sélectionne le COM
        } 
        catch ( Exception e ) 
        { 
            e.printStackTrace(); 
        } 
    } 
}