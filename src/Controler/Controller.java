package Controler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import Model.Model;
import View.Vue;

public class Controller implements Observer, ActionListener {
	
	Vue vue;
	Observable observable;

	private float TempExtActuelle;
	private float derniereTempExt;

	private float TempIntActuelle;
	private float derniereTempInt;

	private float HumiditeActuelle;
	private float derniereHumidite;

	private boolean EtatPorteActuelle;
	private boolean derniereEtatPorte;
	
	public Controller(Observable observable, Vue vue){
			observable.addObserver(this);
			this.vue = vue;
		}

	public void update(Observable observable, Object arg) {
		if (observable instanceof Model) {
			Model model = (Model) observable;
			TempExtActuelle = model.getTempExt();
			this.derniereTempExt = (float) TempExtActuelle;
			TempIntActuelle = model.getTempInter();
			this.derniereTempInt = (float) TempIntActuelle;
			HumiditeActuelle = model.getHumidite();
			this.derniereHumidite = (float) HumiditeActuelle;
//			EtatPorteActuelle = model.isPorte();
//			this.derniereEtatPorte = EtatPorteActuelle;
//			 Mettre les méthodes d'actualisation de la vue ici
			this.vue.getbGo().addActionListener(vue);
//			Afficher();
//			MAJLabel();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int i = Integer.parseInt(this.vue.getTempérature().getText());
		System.out.println(i);
	}
	
	private void Afficher() {
		System.out.println(this.derniereHumidite);
		System.out.println(this.derniereTempExt);
		System.out.println(this.derniereTempInt);
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
	
	public void MAJPorte(){
		
	}
	
	public void MAJGraph(){
		
	}
	
	public void MAJPointRosée(){
		
	}

}