package MAIN;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import CAD.CAD;
import Controller.Controller;
import Model.Model;
import View.Vue;

class TestsGeneraux {

	@Before
	void setUp() throws Exception {
		
	}

	@Test
	void TestValeurRecuControleur() throws Exception {
		Model model = new Model();
		Vue vue = new Vue(null);
		Controller control = new Controller(model,vue,model);
		CAD CAD = new CAD(model);
		/*
		assertEquals(model.getHumidite(),control.getDerniereHumidite()); //Humidite
		assertEquals(model.getTempExt(),control.getDerniereTempExt());//TempExt
		assertEquals(model.getTempInter(),control.getDerniereTempInt());//TempInt
		*/
	}

}
