package Model;

import java.util.Observable;

public class Model extends Observable {
	
	private float tempExt;
	private float tempInter;
	private float tempConsigne;
	private float humidite;
	private boolean pointRosee;
	private boolean porte;
	
	
	public Model() {
		super();
	}	

	
	/* Principe du Design Pattern OBSERVABLE/OBSERVER */
	public void actualiserMesures() {
		setChanged(); //L'état à changé = Notification ! C'est lui le bijoux pour indiquer qu'on autorise
		notifyObservers(); //Va MAJ si setChanged == true
	}

	public void setMesures(float tempExt, float tempInter, float tempConsigne, float humidite, boolean pointRosee, boolean porte) {
		this.tempExt = tempExt;
		this.tempInter = tempInter;
		this.tempConsigne = tempConsigne;
		this.humidite = humidite;
		this.pointRosee = pointRosee;
		this.porte = porte;
		actualiserMesures();
	}

	

	public float getTempExt() {
		return tempExt;
	}


	public void setTempExt(float tempExt) {
		this.tempExt = tempExt;
	}


	public float getTempInter() {
		return tempInter;
	}


	public void setTempInter(float tempInter) {
		this.tempInter = tempInter;
	}


	public float getTempConsigne() {
		return tempConsigne;
	}


	public void setTempConsigne(float tempConsigne) {
		this.tempConsigne = tempConsigne;
	}


	public float getHumidite() {
		return humidite;
	}


	public void setHumidite(float humidite) {
		this.humidite = humidite;
	}


	public boolean isPointRosee() {
		return pointRosee;
	}


	public void setPointRosee(boolean pointRosee) {
		this.pointRosee = pointRosee;
	}


	public boolean isPorte() {
		return porte;
	}


	public void setPorte(boolean porte) {
		this.porte = porte;
	}
	
	
	

}
