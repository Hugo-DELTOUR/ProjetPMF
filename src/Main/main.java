package Main;

import CAD.CAD;
import Controler.Controller;
import Model.Model;
import View.Vue;

public class main {

	public static void main(String[] args) throws Exception {
		Model model = new Model();
		Vue vue = new Vue();
		Controller control = new Controller(model,vue,model);
		CAD CAD = new CAD(model);
		
	}

}
