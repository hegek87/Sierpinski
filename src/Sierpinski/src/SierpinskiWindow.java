package Sierpinski.src;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SierpinskiWindow {
	private JFrame frame;
	private JPanel panel;
	private Sierpinski view;
	
	public SierpinskiWindow(){
		frame = new JFrame();
		panel = createSelector();
		view = new Sierpinski();
		frame.setLayout(new BorderLayout());
		frame.add(BorderLayout.CENTER, view);
		frame.add(BorderLayout.SOUTH, panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
	
	private JPanel createSelector(){
		final JPanel panel = new JPanel();
		JTextField top = new JTextField(6);
		JTextField left = new JTextField(6);
		JTextField right = new JTextField(6);
		final JTextField[] textFields = { top, left, right };
		panel.add(new JLabel("Top:"));
		panel.add(textFields[0]);
		panel.add(new JLabel("Left:"));
		panel.add(textFields[1]);
		panel.add(new JLabel("Right:"));
		panel.add(textFields[2]);
		JButton select = new JButton("Draw");
		select.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				final Point2D.Float[] points = new Point2D.Float[3];
				for(int i = 0; i < 3; ++i){
					String[] vals = textFields[i].getText().split(",");
					points[i] = new Point2D.Float(Float.parseFloat(vals[0]), Float.parseFloat(vals[1]));
				}
				System.out.println(points[1] + " " + points[0] + " " + points[2]);
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						view.refresh(points[1], points[0], points[2]);
					}
				});
			}
		});
		panel.add(select);
		return panel;
	}
	public static void main(String[] args){
		SierpinskiWindow sw = new SierpinskiWindow();
	}
}
