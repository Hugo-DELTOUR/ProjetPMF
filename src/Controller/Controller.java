package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import org.jfree.ui.RefineryUtilities;

import Model.Model;
import View.Vue;
import demo.LineChartDemo1;

public class Controller implements Observer, ActionListener {
	
	Vue vue;
	Model m;
	Observable observable;

	private float derniereTempExt;
	private float derniereTempInt;
	private float derniereHumidite;
	private int i = 0;
	private double pointRosée;
	private double simpliPointRosée;
	private float porte;
	
	private LinkedList tabTempInt; //Ce tableau va parcourir les 10 dernières valeurs pour porte + Graph
	
	public Controller(Observable observable,Vue vue, Model m){
			observable.addObserver(this);
			this.vue = vue;
			this.m = m;
			this.vue.getbGo().addActionListener(this);
			this.setTabTempInt(new LinkedList());
		}

	//Va mettre à jour les valeurs à chaque fois que le modèle subit une modification
	public void update(Observable observable, Object arg) {
		if (observable instanceof Model) {
			Model model = (Model) observable;
			this.derniereTempExt = model.getTempExt();
			this.derniereTempInt = model.getTempInter();
			this.derniereHumidite = model.getHumidite();
			this.getTabTempInt().add(this.derniereTempInt);
			majTableau(); //Décale toutes les valeurs du tableau de 1
			//Afficher();
			MAJLabel();
			MAJGraph(i);
			i++;
			MAJPointRosée();
			MAJPorte();
		}
	}
	
	private void majTableau() {
		if (this.getTabTempInt().size() > 10) {
			this.getTabTempInt().removeFirst();
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
	
	public void MAJGraph(int i){
		this.vue.getDataset().addValue((float) tabTempInt.getLast(), "Température", ""+i);
	}
	
	public void MAJPointRosée(){
		simpliPointRosée=((17.27*this.derniereTempInt)/(237.7+this.derniereTempInt))+Math.log(this.derniereHumidite/100);
		pointRosée=(237.7*simpliPointRosée)/(17.27+simpliPointRosée);
		System.out.println("Point de Rosée:"+pointRosée);
		if(derniereTempInt<pointRosée){
			this.vue.setPointRosée(true);
		}
		else{
			this.vue.setPointRosée(false);
		}
	}
	
	public void MAJPorte(){
		porte = (float) this.tabTempInt.getLast() - (float) this.tabTempInt.getLast();
		System.out.println(porte);
		
		if(porte>4){
			this.vue.setPorte(true);
		}
		else{
			this.vue.setPorte(false);
		}
	}

	public LinkedList getTabTempInt() {
		return tabTempInt;
	}

	public void setTabTempInt(LinkedList tabTempInt) {
		this.tabTempInt = tabTempInt;
	}

}