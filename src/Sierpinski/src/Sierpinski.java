package Sierpinski.src;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;

public class Sierpinski extends JPanel {
	Point2D.Float topPoint;
	Point2D.Float bottomLeft;
	Point2D.Float bottomRight;
	public int size;
	
	public Point2D.Float getMidpoint(Point2D.Float first, Point2D.Float second){
		float x = (first.x + second.x)/2;
		float y = (first.y + second.y)/2;
		return new Point2D.Float(x, y);
	}
	
	public double getLength(Point2D.Float first, Point2D.Float second){
		double a = Math.abs(first.y-second.y);
		double b = Math.abs(first.x-second.x);
		double length = Math.sqrt(a*a+b*b);
		return length;
	}
	
	private void drawPointLine(Graphics2D g2d, Point2D.Float first, Point2D.Float second){
		g2d.draw(new Line2D.Float(first, second));
	}
	
	private void drawSierpinski(Graphics2D g2d, Point2D.Float first, Point2D.Float second, Point2D.Float third){
		g2d.setBackground(Color.GRAY);
		if(first == null || second == null || third == null) return;
		if(getLength(first, second)<size)	return;
		Point2D.Float midFirstSecond = getMidpoint(first, second);
		Point2D.Float midSecondThird = getMidpoint(second, third);
		Point2D.Float midFirstThird  = getMidpoint(first, third);
		drawPointLine(g2d, first, second);
		drawPointLine(g2d, second, third);
		drawPointLine(g2d, first, third);
		drawSierpinski(g2d, first, midFirstSecond, midFirstThird);
		drawSierpinski(g2d, midFirstThird, midSecondThird, third);
		drawSierpinski(g2d, second, midSecondThird, midFirstSecond);
	}
	
	public void refresh(Point2D.Float first, Point2D.Float second, Point2D.Float third){
		bottomLeft = first;
		topPoint = second;
		bottomRight = third;
		repaint();
	}
	
	public Sierpinski(){ 
		super();
		topPoint = new Point2D.Float(50, 0);
		bottomRight = new Point2D.Float(100, 100);
		bottomLeft = new Point2D.Float(0, 100);
		this.size = 2;
	}
	
	public Sierpinski(Point2D.Float topPoint, Point2D.Float bottomLeft, Point2D.Float bottomRight, int size){
		super();
		this.topPoint = topPoint;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
		this.size = size;
	}
	
	@Override public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		drawSierpinski(g2d, bottomLeft, topPoint, bottomRight);
	}
}
