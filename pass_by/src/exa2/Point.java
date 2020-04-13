package exa2;

/**
 * https://trello.com/1/cards/5e7f1b635934db0562c0dd53/attachments/5e9466dc6261de299b5b9837/download?backingUrl=https%3A%2F%2Ftrello-attachments.s3.amazonaws.com%2F59632732a7e7c2260f94c06b%2F5e7f1b635934db0562c0dd53%2F9479f2768327f1f4bf88d87a51ab7628%2Fimage.png
 * @author jamie
 *
 */
public class Point {
	private int x;
	
	public Point(int x) {
		super();
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public static void change(Point p1, Point p2) {
		Point tmp = p1;
		p1 = p2;
		p2 = tmp;
		p1.setX(5);
		p2 = new Point(5);
	}
	
	public static void main(String[] args) {
		Point p1 = new Point(0);
		Point p2 = new Point(0);
		change(p1, p2);
		System.out.println(p1.x +"  "+ p2.x);
	}
	

}

