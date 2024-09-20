import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Square_GUI_Test {
	private Square square = null; // a square
	private JFrame frame; // main frame of the App

	public static void main(String[] args) {
		Square_GUI_Test assign1 = new Square_GUI_Test();
		assign1.start();
	}

	public Square_GUI_Test() {
		// create a square
		square = new Square();
		square.color = new Color(250, 0, 0);
		square.filled = true;
		square.theta = 0;
		square.xc = 0;
		square.yc = 0;
		square.translate(100, 100);
		square.setVertices(50);
	}

	public void start() {
		frame = new JFrame();
		frame.setTitle("COMP2396 Assignment 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create the drawing canvas
		MyCanvas canvas = new MyCanvas();
		canvas.setPreferredSize(new Dimension(500, 200));

		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	private class MyCanvas extends JPanel implements MouseListener {
		private static final long serialVersionUID = 3434052834963106098L;

		public MyCanvas() {
			this.addMouseListener(this);
		}

		// draws the shapes
		public void paintComponent(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			// draws the square
			g.setColor(square.color);
			int[] x = square.getX();
			int[] y = square.getY();
			if (square.filled) {
				g.fillPolygon(x, y, x.length);
			} else {
				g.drawPolygon(x, y, x.length);
			}
		}

		// implements the MouseListener
		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();

			// checks if the user clicks on the square
			if (mx >= (square.xc - 50) && mx <= (square.xc + 50) && my >= (square.yc - 50) && my <= (square.yc + 50)
					&& square.theta == 0) {
				Thread squareThread = new Thread(new SquareThread());
				squareThread.start();
				return;
			}
		}
	}

	private class SquareThread implements Runnable {
		public void run() {
			for (int i = 0; i < 360; i++) {
				square.rotate(Math.PI / 180.0);
				System.out.println(square.theta);
				frame.repaint();

				try {
					Thread.sleep(5);
				} catch (Exception ex) {
				}
			}
			square.theta = 0;
			frame.repaint();
		}
	}
}
