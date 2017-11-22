package CAD;
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 
 
import gnu.io.CommPort; 
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort; 
 
public class CAD 
{ 
	
	private float atempExt;
	private float atempInt;
	private float atempConsigne;
	private float ahumidite;
	private boolean apointRosee;
	private boolean aporte;
	
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
                 
                (new Thread(new SerialReader(in))).start();  //Lecture
                (new Thread(new SerialWriter(out))).start(); 
 
            } 
            else 
            { 
                System.out.println("Error: Only serial ports are handled by this example."); 
            } 
        }      
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