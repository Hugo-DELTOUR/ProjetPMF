package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Vue extends JFrame implements IVue{


	private float tempInt;
	private float tempExt;
	private float humidit�;
	private JLabel imgPorteOuverte = new JLabel(new ImageIcon("porte ouverte.jpg"));
	private JLabel imgPorteFerm�e = new JLabel(new ImageIcon("porte ferm�e.jpg"));
	private JLabel imgAlerte = new JLabel(new ImageIcon("gif alerte.gif"));
	private JLabel labelTempInt = new JLabel("Temp�rature int�rieure(en �):");
	private JLabel labelTempExt = new JLabel("Temp�rature ext�rieure(en �):");
	private JLabel labelHumidit� = new JLabel("Humidit� (en %):");
	private JLabel labelDerTempExt = new JLabel();	
	private JLabel labelDerTempInt = new JLabel();
	private JLabel labelDerHum = new JLabel();
	private JLabel condensation = new JLabel("Risque de condensation");
	private JLabel temp�rature = new JLabel("Choix de la temp�rature:");
	private JTextField choixTemp = new JTextField("", 20);
	private JButton bGo = new JButton("Go");
	private Graphics graph;
	private Observable observable;
	private Boolean pointRos�e = true;
	private Boolean porte = true ;
	
	public Vue(){
		
		JFrame fenetre = new JFrame();
		JPanel pan = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();
		
		fenetre.setTitle("Projet PMF");
		fenetre.setSize(700, 500);
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLayout(new BorderLayout());
		fenetre.setResizable(false);
		
		pan.setLayout(new FlowLayout());
		pan.add(temp�rature, FlowLayout.LEFT);
		pan.add(choixTemp,FlowLayout.CENTER );
		pan.add(bGo, FlowLayout.RIGHT);
		
		pan2.setLayout(new BoxLayout(pan2,BoxLayout.PAGE_AXIS));
		
		if(porte == true){
			pan2.add(imgPorteOuverte);
		}
		else{
			pan2.add(imgPorteFerm�e);
		}
		
		pan2.add(labelHumidit�);
		pan2.add(labelDerHum);
		pan2.add(labelTempInt);
		pan2.add(labelDerTempInt);
		pan2.add(labelTempExt);
		pan2.add(labelDerTempExt);
				
		if(pointRos�e==true){
			pan3.add(imgAlerte);
			pan3.add(condensation);
		}
		
		fenetre.add(pan,BorderLayout.PAGE_START);
		fenetre.add(pan2,BorderLayout.EAST);
		fenetre.add(pan3, BorderLayout.CENTER);
		
	}
		
	
	public JButton getbGo() {
		return bGo;
	}

	public void setbGo(JButton bGo) {
		this.bGo = bGo;
	}

	public JLabel getLabelDerTempExt() {
		return labelDerTempExt;
	}


	public void setLabelDerTempExt(JLabel labelDerTempExt) {
		this.labelDerTempExt = labelDerTempExt;
	}


	public JLabel getLabelDerTempInt() {
		return labelDerTempInt;
	}


	public void setLabelDerTempInt(JLabel labelDerTempInt) {
		this.labelDerTempInt = labelDerTempInt;
	}


	public JLabel getLabelDerHum() {
		return labelDerHum;
	}


	public void setLabelDerHum(JLabel labelDerHum) {
		this.labelDerHum = labelDerHum;
	}


	public float getTempInt() {
		return tempInt;
	}

	public void setTempInt(float tempInt) {
		this.tempInt = tempInt;
	}

	public float getTempExt() {
		return tempExt;
	}

	public void setTempExt(float tempExt) {
		this.tempExt = tempExt;
	}

	public float getHumidit�() {
		return humidit�;
	}

	public void setHumidit�(float humidit�) {
		this.humidit� = humidit�;
	}

	public Boolean getPointRos�e() {
		return pointRos�e;
	}

	public void setPointRos�e(Boolean pointRos�e) {
		this.pointRos�e = pointRos�e;
	}

	public Boolean getPorte() {
		return porte;
	}

	public void setPorte(Boolean porte) {
		this.porte = porte;
	}

	public JLabel getTemp�rature() {
		return temp�rature;
	}

	public void setTemp�rature(JLabel temp�rature) {
		this.temp�rature = temp�rature;
	}


	public JTextField getChoixTemp() {
		return choixTemp;
	}


	public void setChoixTemp(JTextField choixTemp) {
		this.choixTemp = choixTemp;
	}
	
}