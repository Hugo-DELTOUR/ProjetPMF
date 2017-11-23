package Controler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
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
	
	private LinkedList tabTempInt; //Ce tableau va parcourir les 10 dernières valeurs pour porte + Graph
	
	public Controller(Observable observable,Vue vue, Model m){
			observable.addObserver(this);
			this.vue = vue;
			this.m = m;
			this.vue.getbGo().addActionListener(this);
			this.tabTempInt = new LinkedList();
		}

	//Va mettre à jour les valeurs à chaque fois que le modèle subit une modification
	public void update(Observable observable, Object arg) {
		if (observable instanceof Model) {
			Model model = (Model) observable;
			this.derniereTempExt = model.getTempExt();
			this.derniereTempInt = model.getTempInter();
			this.derniereHumidite = model.getHumidite();
			this.tabTempInt.add(this.derniereTempInt);
			majTableau(); //Décale toutes les valeurs du tableau de 1
			Afficher();
			MAJLabel();
		}
	}
	
	private void majTableau() {
		if (this.tabTempInt.size() > 10) {
			this.tabTempInt.removeFirst();
			System.out.println("ElementRetiré");
		}
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Ma classe interne numéro 3 écoute bien !");
		int tempConsigne = Integer.parseInt(this.vue.getChoixTemp().getText());
		System.out.println("Envoi valeur : " + tempConsigne);
		this.m.setTempConsigne(tempConsigne);
	}
	
	private void Afficher() {
		System.out.println("Humidité :" + this.derniereHumidite);
		System.out.println("Température Intérieure :" + this.derniereTempInt);
		System.out.println("Température Extérieure :" + this.derniereTempExt);
	}
	
	public void MAJLabel(){
		String derHum = Float.toString(this.derniereHumidite);
		String derTempExt = Float.toString(this.derniereTempExt);
		String derTempInt = Float.toString(this.derniereTempInt);
		this.vue.getLabelDerHum().setText(derHum);
		this.vue.getLabelDerTempExt().setText(derTempExt);
		this.vue.getLabelDerTempInt().setText(derTempInt);
		this.vue.setHumidité(this.derniereHumidite);
		this.vue.setTempExt(this.derniereTempExt);
		this.vue.setTempInt(this.derniereTempInt);
	}
	
	public void MAJGraph(){
		
	}
	
	public void MAJPointRosée(){
		
	}
//Pour les tests
	public float getDerniereTempExt() {
		return derniereTempExt;
	}

	public float getDerniereTempInt() {
		return derniereTempInt;
	}

	public float getDerniereHumidite() {
		return derniereHumidite;
	}

	
}