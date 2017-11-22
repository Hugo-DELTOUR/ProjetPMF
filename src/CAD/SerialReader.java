package CAD;

import java.io.IOException;
import java.io.InputStream;


/** */ 
public class SerialReader implements Runnable  
{ 
    InputStream in;
    CAD cad;
	private float atempExt;
	private float atempInt;
	private float ahumidite;
	private boolean aporte;
	
    public SerialReader (InputStream in, CAD cad) 
    { 
    	this.cad = cad;
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
            				this.cad.setAhumidite(this.ahumidite);
            				
            			break;
            			case 1 :
            				this.atempInt = Float.parseFloat(elements[1]);
            				this.cad.setAtempInt(this.atempInt);
            			break;
            			case 2 :
            				this.atempExt = Float.parseFloat(elements[2]);
            				this.cad.setAtempExt(this.atempInt);
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

	public float getAtempExt() {
		return atempExt;
	}

	public float getAtempInt() {
		return atempInt;
	}

	public float getAhumidite() {
		return ahumidite;
	}

	public boolean isAporte() {
		return aporte;
	}

    
    
} 
