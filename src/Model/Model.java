package Model;

public class Model {
	
	private float tempExt;
	private float tempInter;
	private float tempConsigne;
	private float humidite;
	private boolean pointRosee;
	private boolean porte;
	
	
	public Model() {
		super();
		this.tempExt = tempExt;
		this.tempInter = tempInter;
		this.tempConsigne = tempConsigne;
		this.humidite = humidite;
		this.pointRosee = pointRosee;
		this.porte = porte;
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
