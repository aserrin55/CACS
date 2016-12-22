package tests;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Test;

import frogger.Frogger;
import frogger.FroggerCollisionDetection;
import frogger.LongLog;
import frogger.Main;
import frogger.ShortLog;
import frogger.Turtles;

public class TestFrog {

	private Robot r;
	private Main m;
	
	public TestFrog() throws AWTException{
		r=new Robot();
		m = new Main();
	}
	
	private void press(int ke, int num) throws InterruptedException{
		while(num>0){
			r.keyPress(ke);
			Thread.sleep(20);
			r.keyRelease(ke);
			Thread.sleep(100);
			num--;
		}
	}
	

	@Test
	public void testSpritePositionInGodMode() {

		// Hilo de ejecución del test
		Thread x = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Thread.sleep(1000);
					
					Frogger frog = m.frog;
					FroggerCollisionDetection frogcd = m.frogCol;
					
					press(KeyEvent.VK_SPACE,1);
					press(KeyEvent.VK_C,1);
					
					/**Tests para out of bounds del juego**/
					
					//test 1 down
					press(KeyEvent.VK_DOWN,1);
					assertEquals(frog.getPosition().getY(), 416.0, 0);
					assertFalse(frogcd.isOutOfBounds());
					
					//test 7 left
					press(KeyEvent.VK_LEFT,7);
					assertEquals(frog.getPosition().getX(), 0.0, 0);
					assertFalse(frogcd.isOutOfBounds());
					
					//test 13 right
					press(KeyEvent.VK_RIGHT,13);
					assertEquals(frog.getPosition().getX(), 384.0, 0);
					assertFalse(frogcd.isOutOfBounds());
					
					//test 13 up
					press(KeyEvent.VK_UP,13);
					assertEquals(frog.getPosition().getY(), 32.0, 0);
					//assertFalse(frogcd.isOutOfBounds());
					
					//Bajamos1 y cuando esté en un arbol esperamos a que salga de pantalla
					press(KeyEvent.VK_DOWN,1);
					boolean sobreTronco = false;
					while(!sobreTronco){
						if(frog.followObject instanceof LongLog || frog.followObject instanceof ShortLog || frog.followObject instanceof Turtles){
							sobreTronco = true;
						}else{
							Thread.sleep(500);
						}
					}
					Thread.sleep(5000);
					assertTrue(frogcd.isOutOfBounds());
					
					press(KeyEvent.VK_V,1);
					System.out.println("End of test 1");
					m.initializeLevel(1);
					Thread.sleep(1000);
					
					/**Tests para la muerte en el agua**/
					
					Thread.sleep(4000);
					System.out.println("Starts test 2");
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
					}
					
					System.out.println("End of test 2");
					m.initializeLevel(1);
					Thread.sleep(1000);
					
					/**Tests para verificar si está en el río o en la carretera**/
					
					Thread.sleep(4000);
					System.out.println("Starts test 2");
					r.keyPress(KeyEvent.VK_C);
					Thread.sleep(50);
					r.keyRelease(KeyEvent.VK_C);
					assertFalse(frogcd.isInRiver());
					System.out.println("Is not in the river");
					for(int i=0;i<7;i++){
						if(i<5 && i>0){
							assertTrue(frogcd.isOnRoad());
							System.out.println("Is on road");
						}
						Thread.sleep(300);
						r.keyPress(KeyEvent.VK_UP);
						Thread.sleep(50);
						r.keyRelease(KeyEvent.VK_UP);
					}
					Thread.sleep(500);
					assertTrue(frogcd.isInRiver());
					System.out.println("Is in the river");
					assertFalse(frogcd.isOnRoad());
					System.out.println("Is not on road");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		x.start();

		// Ejecutamos el juego
		m.run();

		// minY = 416
		// minX = 0
		// maxX = 384
		// maxY = 32

		// pasoX = 32
		// pasoY = 32

		// posIni = < X:192.0, Y:416.0>
	}

}
