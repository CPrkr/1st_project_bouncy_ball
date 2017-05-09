package com.craigcode.BouncyBall;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class bouncyBall {
	
	public static float acc = 1.0f;
	public static float deacel = 1.00f;
	
	public static BallPanel bp = new BallPanel();
	//public static JFrame jf = new JFrame();
	
	static void oneloop() {
		for (int i=0; i<400; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			bp.doBall();
			bp.repaint();
		}
	}
	
	public static void main (String[] args) {
		JFrame jf = new JFrame();
		jf.addWindowListener(new WindowAdapter () {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		
		//BallPanel bp = new BallPanel();
		jf.add(bp);
		
		jf.setSize(1200,900);
		jf.setVisible(true);
		
		for (int i=0; i<1000; i++) {
			oneloop();
			/*acc = 0.1f;
			oneloop();
			acc = 0.5f;
			oneloop();
			acc += 2.0f;
			oneloop();
			acc += 10.0f;
			oneloop();*/
		}
		
		acc = 0.5f;
		oneloop();
		oneloop();
		oneloop();
		
		/*for (int i=0; i<100; i++) {
			try {
				Thread.sleep(40);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			bp.doBall();
			bp.repaint();
		}*/
	}
}

class BallPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Ball redBall = new Ball();
	
	void drawBall (Ball b, Graphics2D g2) {
		g2.fill(new Ellipse2D.Float(b.xPos,b.yPos,b.radius,b.radius));
	}
	
	void doBall() {
		if (ballMovement.testH(redBall))
			ballMovement.ballHReverse(redBall);
		if (ballMovement.testV(redBall))
			ballMovement.ballVReverse(redBall);
		ballMovement.moveBall(redBall);			
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.green);
		drawBall(redBall,g2);
	}
}

class Ball {
	public static Random rand = new Random();
	
	float radius = 50.0f;
	
	float vSpeed = 0.0f;
	float hSpeed = (rand.nextFloat() * 60) - 30;
	
	float xPos = (rand.nextFloat() * 450);
	float yPos = (rand.nextFloat() * 450);
	
	float getVSpeed () {
		return vSpeed;
	}
	
	float getHSpeed () {
		return hSpeed;
	}
	
	float getXPos () {
		return xPos;
	}
	
	float getYPos () {
		return yPos;
	}
	
}

class ballMovement {
	
	static boolean testH (Ball b) {
		if (b.xPos < 0.0f | b.xPos > 1200.0f)
			return true;
		else
			return false;
	}
	
	static boolean testV (Ball b) {
		if (b.yPos > 900.0f | b.yPos < 0.0f)
			return true;
		else
			return false;
	}
	
	//static float acel = bouncyBall.acc;
	
	static void accelerate(Ball b) {
		b.vSpeed = b.getVSpeed() + (bouncyBall.acc * bouncyBall.deacel);
	}
	
	static void ballVReverse(Ball b) {
		b.vSpeed = (b.getVSpeed() - bouncyBall.acc) * -1.0f;
	}
	
	static void ballHReverse (Ball b) {
		b.hSpeed = b.getHSpeed() * -1.0f;
	}
	
	static void moveBall (Ball b) {
		b.xPos = b.getXPos() + b.getHSpeed();
		b.yPos = b.getYPos() + b.getVSpeed();
		accelerate(b);
	}
}