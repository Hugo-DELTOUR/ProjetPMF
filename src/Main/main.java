package Main;

import CAD.Acq_Data;
import Controler.Controller;
import Model.Model;
import View.Vue;

public class main {

	public static void main(String[] args) throws Exception {
		Model model = new Model();
		Vue vue = new Vue();
		Controller control = new Controller(model,vue);
		Acq_Data CAD = new Acq_Data(model);
		
	}

}
