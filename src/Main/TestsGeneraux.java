package Main;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import CAD.CAD;
import Controler.Controller;
import Model.Model;
import View.Vue;

class TestsGeneraux {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void TestValeurRecuControleur() throws Exception {
		Model model = new Model();
		Vue vue = new Vue();
		Controller control = new Controller(model,vue,model);
		CAD CAD = new CAD(model);
		assertEquals(model.getHumidite(),control.getDerniereHumidite()); //Humidite
		assertEquals(model.getTempExt(),control.getDerniereTempExt());//TempExt
		assertEquals(model.getTempInter(),control.getDerniereTempInt());//TempInt
	}

}
