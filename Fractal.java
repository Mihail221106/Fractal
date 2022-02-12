import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.ArrayList;

public class Fractal implements Runnable{
	private JFrame frame;
	private byte number;
	private BufferedImage image;
	private static Graphics2D graphics;
	private DrawFractal panel;
	private double branchLength = 250.0;
	private ArrayList<Line> lines, utilLines;
	
	@Override
	public void run() {
		for(byte i = 0; i < number; i++) {
			try {
				Thread.sleep(300);
			} catch (Exception e) {
				// TODO: handle exception
			}
			draw(branchLength);
			branchLength /= Const.ratio;
		}//for
	}
	
	public Fractal() {
		frame = new JFrame("Tree");
		panel = new DrawFractal();
		
		frame.setSize(1200, 810);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(panel);
		
		image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
		graphics = (Graphics2D) image.getGraphics();
		lines = new ArrayList<Line>();
		utilLines = new ArrayList<Line>();
		
		
	}

	public static void main(String[] args) {
		Fractal fractal = new Fractal();
		fractal.frame.setVisible(true);
		fractal.go();

	}
	
	private void go() {
		frame.add(panel);
		
		lines.add(new Line(frame.getWidth()/2, frame.getHeight()-40-(int)branchLength, Line.up));
		
		graphics.setStroke(new BasicStroke(3f));
		graphics.setColor(new Color(20, 20, 80));
		graphics.fillRect(0,0, image.getWidth(), image.getHeight());
		panel.repaint();
		inputNumber();
		graphics.setColor(Color.yellow);
		graphics.draw(new Line2D.Double(frame.getWidth()/2, frame.getHeight()-40, frame.getWidth()/2, frame.getHeight()-40-(int)branchLength));
		panel.repaint();
		Thread drawing = new Thread(this);
		drawing.start();
	}
	
	private void inputNumber() {
		while(true) {
			String numb = JOptionPane.showInputDialog(frame, "The number is: ", Const.input, JOptionPane.PLAIN_MESSAGE);
			try {
				number = parseByte(numb);
				return;
			} catch (Exception e){
				JOptionPane.showMessageDialog(frame, "Please, try again.", Const.error, JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	
	private byte parseByte(String value) throws Exception{
		byte num = 0;
		try {
			num = Byte.parseByte(value);
			if(num <= 0 || num > 13) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception();
		}
		return num;
	}
	
	private void draw(double length) {
		for(Line line: lines) {
			line.drawLine(length, graphics);
			utilLines.add(line.line1());
			utilLines.add(line.line2());
		}
		lines.clear();
		for(Line line : utilLines) {
			lines.add(line);
		}
		utilLines.clear();
		panel.repaint();
	}
	
	private class DrawFractal extends JPanel {
		@Override
		protected void paintComponent(Graphics gr) {
//			super.paintComponent(gr);
			Graphics2D g = (Graphics2D) gr;
			g.drawImage(image, 0, 0, null);
			
		}
	}

	private class Const{
		public static final double
			ratio = 1.7;
		public static final String
			input = "Input integer number between 1 and 13 of repeat",
			error = "This value isn't appropriate";
	}
}
