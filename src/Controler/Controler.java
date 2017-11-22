package Controler;

import java.util.Observable;
import java.util.Observer;

import Model.Model;

public class Controler implements Observer {
	
	Observable observable;
	
	public Controler(Observable observable){
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

			//Mettre les méthodes d'actualisation de la vue ici
		}

		//Mettre les méthodes de calculs ici
	}
}