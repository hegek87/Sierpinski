package Sierpinski.src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestSierpinski extends JPanel {
	SierpinskiTriangle sp;
	public TestSierpinski(SierpinskiTriangle sp){
		super();
		this.sp = sp;
	}
	@Override public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		sp.drawSierpinski(g2D);
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		SierpinskiTriangle sp = new SierpinskiTriangle(new Point2D.Float(200, 0),
				new Point2D.Float(400, 400), new Point2D.Float(0, 400), 0);
		sp.nextGeneration(6);
		TestSierpinski tsp = new TestSierpinski(sp);
		frame.add(tsp);
		frame.setVisible(true);
		frame.setSize(420, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
