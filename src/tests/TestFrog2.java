package tests;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Test;

import frogger.Frogger;
import frogger.Main;

public class TestFrog2 {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testSpritePositionInGodMode() {
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
					r.keyPress(KeyEvent.VK_UP);
					Thread.sleep(500);

					assertEquals(frog.getPosition().getX(), 192.0, 0);
					assertEquals(frog.getPosition().getY(), 384.0, 0);

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
