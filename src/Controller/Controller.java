package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import Model.Model;
import View.Vue;

public class Controller implements Observer, ActionListener {
	
	Vue vue;
	Model m;
	Observable observable;

	private float derniereTempExt;
	private float derniereTempInt;
	private float derniereHumidite;
	private int i = 0;
	private double pointRos�e;
	private double simpliPointRos�e;
	private float porte;
	
	private LinkedList<Float> tabTempInt; //Ce tableau va parcourir les 10 derni�res valeurs pour porte + Graph
	
	public Controller(Observable observable,Vue vue, Model m){
			observable.addObserver(this);
			this.vue = vue;
			this.m = m;
			this.vue.getbGo().addActionListener(this);
			this.setTabTempInt(new LinkedList<Float>());
		}

	//Va mettre � jour les valeurs � chaque fois que le mod�le subit une modification
	public void update(Observable observable, Object arg) {
		if (observable instanceof Model) {
			Model model = (Model) observable;
			this.derniereTempExt = model.getTempExt();
			this.derniereTempInt = model.getTempInter();
			this.derniereHumidite = model.getHumidite();
			this.getTabTempInt().add(this.derniereTempInt);
			majTableau(); //D�cale toutes les valeurs du tableau de 1
			Afficher();
			MAJLabel();
			MAJGraph(i);
			i++;
			MAJPointRos�e();
			MAJPorte();
		}
	}
	/**
	 * Met � jour dans un tableau les derni�res valeurs re�us
	 */
	private void majTableau() {
		if (this.getTabTempInt().size() > 10) {
			this.getTabTempInt().removeFirst();
			//System.out.println("ElementRetir�");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Ma classe interne num�ro 3 �coute bien !");
		int tempConsigne = Integer.parseInt(this.vue.getChoixTemp().getText());
		System.out.println("Envoi valeur : " + tempConsigne);
		this.m.setTempConsigne(tempConsigne);
	}
	/**
	 * Permet de suivre l'affichage sous console
	 */
	private void Afficher() {
		System.out.println("Humidit� :" + this.derniereHumidite);
		System.out.println("Temp�rature Int�rieure :" + this.derniereTempInt);
		System.out.println("Temp�rature Ext�rieure :" + this.derniereTempExt);
	}
	/**
	 * Permet de mettre � jour l'affichage des label en fonction de la r�ception
	 */
	public void MAJLabel(){
		String derHum = Float.toString(this.derniereHumidite);
		String derTempExt = Float.toString(this.derniereTempExt);
		String derTempInt = Float.toString(this.derniereTempInt);
		this.vue.getLabelDerHum().setText(derHum);
		this.vue.getLabelDerTempExt().setText(derTempExt);
		this.vue.getLabelDerTempInt().setText(derTempInt);
		this.vue.setHumidit�(this.derniereHumidite);
		this.vue.setTempExt(this.derniereTempExt);
		this.vue.setTempInt(this.derniereTempInt);
	}
	/**
	 * Met � jour les donn�es graphiques, en donnant en param�tre la derni�re valeur re�ue
	 * @param i
	 */
	public void MAJGraph(int i){
		this.vue.getDataset().addValue((float) tabTempInt.getLast(), "Temp�rature", ""+i);
	}
	/**
	 * Met � jour la valeur du point de ros�e, si le point de ros�e est sup�rieur � la temp�rature interne, l'alerte se d�clanche sur la vue
	 */
	public void MAJPointRos�e(){
		simpliPointRos�e=((17.27*this.derniereTempInt)/(237.7+this.derniereTempInt))+Math.log(this.derniereHumidite/100);
		pointRos�e=(237.7*simpliPointRos�e)/(17.27+simpliPointRos�e);
		System.out.println("Point de Ros�e: "+pointRos�e);
		if(derniereTempInt<pointRos�e){
			this.vue.setPointRos�e(true);
		}
		else{
			this.vue.setPointRos�e(false);
		}
	}
	/**
	 * Fait la diff�rence entre la premi�re et derni�re valeur du tableau, si le delta est sup�rieur � quatre, l'�quart est trop important
	 * Une icone de porte est donc mise � jour
	 */
	public void MAJPorte(){
		porte = this.tabTempInt.getLast() - this.tabTempInt.getFirst(); //Ecart
		System.out.println(" Last Value : " + this.tabTempInt.getLast());
		System.out.println(" First Value : " + this.tabTempInt.getFirst());
		System.out.println("Delta = " + porte);
		if(porte>4){
			this.vue.setPorte(true);
		}
		else{
			this.vue.setPorte(false);
		}
	}
/**
 * Retourne une liste cha�n�e de float
 * @return
 */
	public LinkedList<Float> getTabTempInt() {
		return tabTempInt;
	}

	public void setTabTempInt(LinkedList<Float> tabTempInt) {
		this.tabTempInt = tabTempInt;
	}

	public float getDerniereTempExt() {
		return derniereTempExt;
	}

	public void setDerniereTempExt(float derniereTempExt) {
		this.derniereTempExt = derniereTempExt;
	}

	public float getDerniereTempInt() {
		return derniereTempInt;
	}

	public void setDerniereTempInt(float derniereTempInt) {
		this.derniereTempInt = derniereTempInt;
	}

	public float getDerniereHumidite() {
		return derniereHumidite;
	}

	public void setDerniereHumidite(float derniereHumidite) {
		this.derniereHumidite = derniereHumidite;
	}
	
	

}