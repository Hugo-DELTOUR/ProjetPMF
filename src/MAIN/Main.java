package MAIN;

import CAD.CAD;
import Controller.Controller;
import Model.Model;
import View.Vue;

public class Main {

	public static void main(String[] args) throws Exception {
		Model model = new Model();
		Vue vue = new Vue("");
		Controller control = new Controller(model,vue,model);
		CAD CAD = new CAD(model);		
	}
}
