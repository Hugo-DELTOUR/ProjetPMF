package CAD;

import java.util.Observable;
import java.util.Observer;

import Controler.Controler;
import Model.Model;

public class main {

	public static void main(String[] args) throws Exception {
		Model model = new Model();
		
		
		Controler control = new Controler(model);
		Acq_Data CAD = new Acq_Data(model);
		
	}

}
