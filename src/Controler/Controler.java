package Controler;

import java.util.Observable;
import java.util.Observer;

import Model.Model;

public class Controler implements Observer {
	
	Model m;
	Observable observable;
	
	public Controler(Observable observable, Model m){
		observable.addObserver(this);		
	}

	private float derniereTempExt;
	private float derniereTempInt;
	private float derniereHumidite;
	private boolean dernierPorte;
	
	public void update(Observable observable, Object arg) {
		if (observable instanceof Model) {
			Model model = (Model) observable;
			this.derniereTempExt = model.getTempExt();
			this.derniereTempInt = model.getTempInter();
			this.derniereHumidite = model.getHumidite();
			this.dernierPorte = model.isPorte();
			System.out.println(this.derniereTempExt);
			System.out.println(this.derniereTempInt);
			System.out.println(this.derniereHumidite);
			System.out.println(this.dernierPorte);
			

			//Mettre les méthodes d'actualisation de la vue ici
		}

		//Mettre les méthodes de calculs ici
	}
}