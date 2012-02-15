package Sierpinski.src;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SierpinskiTriangle {
	private Point2D.Float top;
	private Point2D.Float right;
	private Point2D.Float left;
	
	private List<SierpinskiTriangle> children;
	private int generation;
	
	public SierpinskiTriangle(Point2D.Float[] points, int generation){
		top = points[0];
		right = points[1];
		left = points[2];
		children = new ArrayList<SierpinskiTriangle>();
		this.generation = generation;
	}
	
	public SierpinskiTriangle(Point2D.Float top, Point2D.Float right, Point2D.Float left, int generation){
		this(new Point2D.Float[]{top, right, left}, generation);
	}
	
	private Point2D.Float getMidpoint(Point2D.Float first, Point2D.Float second){
		float a = (first.x+second.x)/2;
		float b = (first.y+second.y)/2;
		return new Point2D.Float(a, b);
	}
	private double getLength(Point2D.Float first, Point2D.Float second){
		double x = Math.abs(first.x-second.x);
		double y = Math.abs(first.y-second.y);
		return Math.sqrt(x*x+y*y);
	}
	public void nextGeneration(int maxGeneration){
		Point2D.Float midTopLeft = getMidpoint(top, left);
		Point2D.Float midTopRight = getMidpoint(top, right);
		Point2D.Float midLeftRight = getMidpoint(left, right);
		while(generation<=maxGeneration){
			SierpinskiTriangle sp1 = new SierpinskiTriangle(midTopLeft, midLeftRight, left, ++generation);
			SierpinskiTriangle sp2 = new SierpinskiTriangle(top, midTopRight, midTopLeft, generation);
			SierpinskiTriangle sp3 = new SierpinskiTriangle(midTopRight, right, midLeftRight, generation);
			children.add(sp1);
			children.add(sp2);
			children.add(sp3);
			for(SierpinskiTriangle child : children){
				child.nextGeneration(maxGeneration);
			}
		}
	}
	
	public void drawSierpinski(Graphics2D g2D){
		g2D.draw(new Line2D.Float(top, left));
		g2D.draw(new Line2D.Float(top, right));
		g2D.draw(new Line2D.Float(left, right));
		for(SierpinskiTriangle child : children){
			child.drawSierpinski(g2D);
		}
	}
	@Override public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(children.size());
		sb.append("\n" + generation);
		sb.append("\n");
		for(SierpinskiTriangle child : children){
			sb.append(child.toString());
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		SierpinskiTriangle sp = new SierpinskiTriangle(new Point2D.Float(50, 0),
				new Point2D.Float(100, 100), new Point2D.Float(0, 100), 0);
		sp.nextGeneration(4);
		System.out.println(sp);
	}
}
