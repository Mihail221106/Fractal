import java.awt.*;
import java.awt.geom.Line2D;

public class Line {
	private String currentFacing = up, facing1, facing2;
	public static final String
		left = "Left", right = "Right", up = "Up", down = "Down",
		leftDown = left + down, leftUp = left + up, rightDown = right + down, rightUp = right + up;
	private double x, y, x1, y1, x2, y2;
	
	
	public Line(double x, double y, String facing) {
		this.currentFacing = facing;
		this.x = x;
		this.y = y;
		
	}
	
	public void drawLine (double length, Graphics2D graphics) {
		doMath(length, graphics);
		
	}
	private void doMath(double length, Graphics2D graphics) {
		int i = 1;
		for(String branch : branches()) {
			if(branch.equals(up)){
				graphics.draw(new Line2D.Double(x, y, x, y - length));
				if(i == 1) {
					x1 = x;
					y1 = y-length;
					facing1 = branch;
				}
				if(i == 2){
					x2 = x;
					y2 = y-length;
					facing2 = branch;
				}
			}
			if(branch.equals(right)){
				graphics.draw(new Line2D.Double(x, y, x + length, y));
				if(i == 1) {
					x1 = x+length;
					y1 = y;
					facing1 = branch;
				}
				if(i == 2){
					x2 = x+length;
					y2 = y;
					facing2 = branch;
				}
			}
			if(branch.equals(left)){
				graphics.draw(new Line2D.Double(x, y, x-length, y));
				if(i == 1) {
					x1 = x-length;
					y1 = y;
					facing1 = branch;
				}
				if(i == 2) {
					x2 = x-length;
					y2 = y;
					facing2 = branch;
				}
			}
			if(branch.equals(down)){
				graphics.draw(new Line2D.Double(x, y, x, y + length));
				if(i == 1) {
					x1 = x;
					y1 = y+length;
					facing1 = branch;
				}
				if(i == 2) {
					x2 = x;
					y2 = y+length;
					facing2 = branch;
				}
			}
			if(branch.equals(rightUp)) {
				double delta = length / Math.sqrt(2);
				graphics.draw(new Line2D.Double(x, y, x + delta, y - delta));
				if(i == 1) {
					x1 = x+delta;
					y1 = y-delta;
					facing1 = branch;
				}
				if(i == 2) {
					x2 = x+delta;
					y2 = y-delta;
					facing2 = branch;
				}
			}
			if(branch.equals(rightDown)) {
				double delta = length / Math.sqrt(2);
				graphics.draw(new Line2D.Double(x, y, x+delta, y+delta));
				if(i == 1) {
					x1 = x+delta;
					y1 = y+delta;
					facing1 = branch;
				}
				if(i == 2) {
					x2 = x+delta;
					y2 = y+delta;
					facing2 = branch;
				}
			}
			if(branch.equals(leftDown)) {
				double delta = length / Math.sqrt(2);
				graphics.draw(new Line2D.Double(x, y, x-delta, y+delta));
				if(i == 1) {
					x1 = x-delta;
					y1 = y+delta;
					facing1 = branch;
				}
				if(i == 2) {
					x2 = x-delta;
					y2 = y+delta;
					facing2 = branch;
				}
			}
			if(branch.equals(leftUp)) {
				double delta = length / Math.sqrt(2);
				graphics.draw(new Line2D.Double(x, y, x-delta, y-delta));
				if(i == 1) {
					x1 = x-delta;
					y1 = y-delta;
					facing1 = branch;
				}
				if(i == 2) {
					x2 = x-delta;
					y2 = y-delta;
					facing2 = branch;
				}
			}
			i++;
		}//for
	}//doMath()
	
	private String[] branches() {
		String[] branches = new String[2];
		if(currentFacing.equals(up)) {
			branches[0] = leftUp;
			branches[1] = rightUp;
		}
		if(currentFacing.equals(left)) {
			branches[0] = leftDown;
			branches[1] = leftUp;
		}
		if(currentFacing.equals(right)) {
			branches[0] = rightUp;
			branches[1] = rightDown;
		}
		if(currentFacing.equals(down)) {
			branches[0] = leftDown;
			branches[1] = rightDown;
		}
		if(currentFacing.equals(rightUp)) {
			branches[0] = up;
			branches[1] = right;
		}
		if(currentFacing.equals(rightDown)) {
			branches[0] = right;
			branches[1] = down;
		}
		if(currentFacing.equals(leftDown)) {
			branches[0] = left;
			branches[1] = down;
		}
		if(currentFacing.equals(leftUp)) {
			branches[0] = up;
			branches[1] = left;
		}
		
		return branches;
	}//branches()
	
	public Line line1() {
		return new Line(x1, y1, facing1);
	}
	public Line line2() {
		return new Line(x2, y2, facing2);
	}
	
}
