package Model;

import java.util.Observable;

public class Model extends Observable {
	
	//Les valeurs sont mises à jour automatiquement
	private float humidite;
	private float tempInter;
	private float tempExt;
	private float tempConsigne;
	
		
	public Model() {
		//Pas besoin d'une structure de données
	}	

	
	/* Principe du Design Pattern OBSERVABLE/OBSERVER */
	public void actualiserMesures() {
		setChanged(); //L'état à changé = Notification ! C'est lui le bijoux pour indiquer qu'on autorise
		notifyObservers(); //Va MAJ si setChanged == true
	}

	public void setMesures(float humidite, float tempInter, float tempExt) {
		this.humidite = humidite;
		this.tempInter = tempInter;
		this.tempExt = tempExt;
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

}
