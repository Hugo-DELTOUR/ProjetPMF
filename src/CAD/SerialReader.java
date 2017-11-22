package CAD;

import java.io.IOException;
import java.io.InputStream;

/** */ 
public class SerialReader implements Runnable  
{ 
    InputStream in;
	private float atempExt;
	private float atempInt;
	private float atempConsigne;
	private float ahumidite;
	private boolean apointRosee;
	private boolean aporte;
    private String[] decoupe;
	
    public SerialReader ( InputStream in ) 
    { 
        this.in = in; 
    } 
     
    public void run () 
    { 
        byte[] buffer = new byte[1024]; 
        int len = -1; 
        try 
        { 
            while ( ( len = this.in.read(buffer)) > -1 ) 
            { 
              //Le traitement doit se faire ici
              String chaine = new String(buffer,0,len);
              //System.out.print(chaine);
              String[] elements = chaine.split(" "); //split avec espace
              // contient ["H Ti Te"]
              int i = 0;
              if (elements.length > 1) {
              for (i = 0; i < elements.length; i++) {
            	  
            		switch (i) {
            			case 0 :
            				this.ahumidite = Float.parseFloat(elements[0]);
            				System.out.println(this.ahumidite);
            			break;
            			case 1 :
            				this.atempInt = Float.parseFloat(elements[1]);
            				System.out.println(this.atempInt);
            			break;
            			case 2 :
            				this.atempExt = Float.parseFloat(elements[2]);
            				System.out.println(this.atempExt);
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
