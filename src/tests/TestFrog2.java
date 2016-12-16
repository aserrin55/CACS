package tests;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Test;

import frogger.Frogger;
import frogger.FroggerCollisionDetection;
import frogger.Main;

public class TestFrog2 {

	@Test
	public void testIsAliveInWater() {
		Main m = new Main();

		// Hilo de ejecución del test
		Thread x = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Frogger frog = m.frog;
					Thread.sleep(1000);
					Robot r = new Robot();
					r.keyPress(KeyEvent.VK_SPACE);
					r.keyPress(KeyEvent.VK_C);
					Thread.sleep(50);
					r.keyRelease(KeyEvent.VK_C);
					r.keyPress(KeyEvent.VK_UP);
					Thread.sleep(50);
					r.keyRelease(KeyEvent.VK_UP);
					for(int i=0;i<5;i++){
						Thread.sleep(300);
						r.keyPress(KeyEvent.VK_UP);
						Thread.sleep(50);
						r.keyRelease(KeyEvent.VK_UP);
						assertTrue(frog.isAlive);
					}
					Thread.sleep(180);
					r.keyPress(KeyEvent.VK_V);
					Thread.sleep(50);
					r.keyRelease(KeyEvent.VK_V);
					Thread.sleep(300);
					r.keyPress(KeyEvent.VK_UP);
					Thread.sleep(50);
					r.keyRelease(KeyEvent.VK_UP);
					Thread.sleep(80);
					if(frog.isAlive){
						System.out.println("FrogStillAlive");
						Thread.sleep(5400);
						System.out.println("Is going to die");
						assertFalse(frog.isAlive);
					}else{
						System.out.println("FrogDead");
						Thread.sleep(50);
						r.keyPress(KeyEvent.VK_ESCAPE);
						r.keyRelease(KeyEvent.VK_ESCAPE);
					}
					

				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		x.start();
		m.run();
	}
	
	@Test
	public void testIsInTheRiver() {
		Main m = new Main();

		// Hilo de ejecución del test
		Thread x = new Thread(new Runnable() {
			@Override
			public void run() {
				FroggerCollisionDetection f = m.frogCol;
				Frogger frog = m.frog;
				try {
					Thread.sleep(1000);
					Robot r = new Robot();
					r.keyPress(KeyEvent.VK_SPACE);
					r.keyPress(KeyEvent.VK_C);
					Thread.sleep(50);
					r.keyRelease(KeyEvent.VK_C);
					assertFalse(f.isInRiver());
					System.out.println("Is not in the river");
					for(int i=0;i<7;i++){
						if(i<5 && i>0){
							assertTrue(f.isOnRoad());
							System.out.println("Is on road");
						}
						Thread.sleep(300);
						r.keyPress(KeyEvent.VK_UP);
						Thread.sleep(50);
						r.keyRelease(KeyEvent.VK_UP);
					}
					assertTrue(f.isInRiver());
					System.out.println("Is in the river");
					assertFalse(f.isOnRoad());
					System.out.println("Is not on road");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		x.start();
		m.run();
	}
}
