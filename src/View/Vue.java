package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class Vue extends ApplicationFrame implements IVue{

	
	private float tempInt;
	private float tempExt;
	private float humidité;
	private JLabel imgPorteOuverte = new JLabel(new ImageIcon("porte ouverte.jpg"));
	private JLabel imgPorteFermée = new JLabel(new ImageIcon("porte fermée.jpg"));
	private JLabel imgAlerte = new JLabel(new ImageIcon("gif alerte.gif"));
	private JLabel labelTempInt = new JLabel("Température intérieure(en °):");
	private JLabel labelTempExt = new JLabel("Température extérieure(en °):");
	private JLabel labelHumidité = new JLabel("Humidité (en %):");
	private JLabel labelDerTempExt = new JLabel();	
	private JLabel labelDerTempInt = new JLabel();
	private JLabel labelDerHum = new JLabel();
	private JLabel condensation = new JLabel("Risque de condensation");
	private JLabel température = new JLabel("Choix de la température:");
	private JTextField choixTemp = new JTextField("", 20);
	private JButton bGo = new JButton("Go");
	private Boolean pointRosée = true;
	private Boolean porte = false;
	private DefaultCategoryDataset dataset;

	public Vue(String title){
		
		super(title);
		dataset = new DefaultCategoryDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 270));
				
		JFrame fenetre = new JFrame();
		JPanel pan = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();
		JPanel pan4 = new JPanel();
		
		fenetre.setTitle("Projet PMF");
		fenetre.setSize(700, 500);
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLayout(new BorderLayout());
		fenetre.setResizable(false);
		
		pan.setLayout(new FlowLayout());
		pan.add(température, FlowLayout.LEFT);
		pan.add(choixTemp,FlowLayout.CENTER );
		pan.add(bGo, FlowLayout.RIGHT);
		
		pan2.setLayout(new BoxLayout(pan2,BoxLayout.PAGE_AXIS));
		
		if(porte == true){
			pan2.add(imgPorteOuverte);
		}
		else{
			pan2.add(imgPorteFermée);
		}
		
		pan2.add(labelHumidité);
		pan2.add(labelDerHum);
		pan2.add(labelTempInt);
		pan2.add(labelDerTempInt);
		pan2.add(labelTempExt);
		pan2.add(labelDerTempExt);
				
		if(pointRosée==true){
			pan3.add(imgAlerte);
			pan3.add(condensation);
		}
		
		pan4.add(chartPanel);
		
		fenetre.add(pan,BorderLayout.PAGE_START);
		fenetre.add(pan2,BorderLayout.EAST);
		fenetre.add(pan3, BorderLayout.SOUTH);
		fenetre.add(pan4, BorderLayout.CENTER);
	}
	
	private static JFreeChart createChart(CategoryDataset dataset) {
		
		JFreeChart chart = ChartFactory.createLineChart("", "", "", dataset, PlotOrientation.VERTICAL, false, true, false);
		chart.addSubtitle(new TextTitle("Température"));
		TextTitle source = new TextTitle("");
		chart.addSubtitle(source);
		
		chart.setBackgroundPaint(Color.LIGHT_GRAY);
		
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinePaint(Color.black);
		
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(false);
		renderer.setFillPaint(Color.white);
		
		return chart;
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

	public float getHumidité() {
		return humidité;
	}

	public void setHumidité(float humidité) {
		this.humidité = humidité;
	}

	public Boolean getPointRosée() {
		return pointRosée;
	}

	public void setPointRosée(Boolean pointRosée) {
		this.pointRosée = pointRosée;
	}

	public Boolean getPorte() {
		return porte;
	}

	public void setPorte(Boolean porte) {
		this.porte = porte;
	}

	public JTextField getChoixTemp() {
		return choixTemp;
	}

	public void setChoixTemp(JTextField choixTemp) {
		this.choixTemp = choixTemp;
	}
	
	public DefaultCategoryDataset getDataset() {
		return dataset;
	}

	public void setDataset(DefaultCategoryDataset dataset) {
		this.dataset = dataset;
	}
}