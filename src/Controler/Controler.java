package Controler;

import java.util.Observable;
import java.util.Observer;

import Model.Model;

public class Controler implements Observer {

	Observable observable;

	private float TempExtActuelle;
	private float derniereTempExt;

	private float TempIntActuelle;
	private float derniereTempInt;

	private float HumiditeActuelle;
	private float derniereHumidite;

	private boolean EtatPorteActuelle;
	private boolean derniereEtatPorte;

	public Controler(Observable observable) {
		observable.addObserver(this);
	}

	public void update(Observable observable, Object arg) {
		if (observable instanceof Model) {
			Model model = (Model) observable;
			TempExtActuelle = model.getTempExt();
			this.derniereTempExt = TempExtActuelle;
			TempIntActuelle = model.getTempInter();
			this.derniereTempInt = TempIntActuelle;
			HumiditeActuelle = model.getHumidite();
			this.derniereHumidite = HumiditeActuelle;
			//EtatPorteActuelle = model.isPorte();
			//this.derniereEtatPorte = EtatPorteActuelle;
			afficher();
			// Mettre les méthodes d'actualisation de la vue ici
		}
	}

	public void afficher() {
		System.out.println("MAJ reçu");
		System.out.println(this.derniereTempExt);
		System.out.println(this.derniereTempInt);
		System.out.println(this.derniereHumidite);
	}

}