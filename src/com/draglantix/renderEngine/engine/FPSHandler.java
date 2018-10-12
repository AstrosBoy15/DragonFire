package com.draglantix.renderEngine.engine;

import com.draglantix.renderEngine.utils.Timer;

public class FPSHandler {

	private long variableYieldTime, lastTime, lastFPS;
	private int fps;
	private Timer timer = new Timer();
	private boolean printFPS;

	public FPSHandler(boolean printFPS) {
		this.printFPS = printFPS;
	}
	
	public void sync(double fps) {
		if(fps <= 0)
			return;

		long sleepTime = 1000000000 / (int)fps;
		long yieldTime = Math.min(sleepTime, variableYieldTime + sleepTime % (1000 * 1000));
		long overSleep = 0;

		try {
			while(true) {
				long t = (long) (timer.getTime() * 1000000000 - lastTime);

				if(t < sleepTime - yieldTime) {
					Thread.sleep(1);
				} else if(t < sleepTime) {
					Thread.yield();
				} else {
					overSleep = t - sleepTime;
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lastTime = (long) (timer.getTime() * 1000000000 - Math.min(overSleep, sleepTime));
			if(overSleep > variableYieldTime) {
				variableYieldTime = Math.min(variableYieldTime + 200 * 1000, sleepTime);
			} else if(overSleep < variableYieldTime - 200 * 1000) {
				variableYieldTime = Math.max(variableYieldTime - 2 * 1000, 0);
			}
		}

		if(printFPS) {
			updateFPS();
		}
	}

	public void updateFPS() {
		if(timer.getTime() * 1000000000 - lastFPS > 1000000000) {
			System.out.println("FPS: " + fps);
			fps = 0;
			lastFPS += 1000000000;
		}
		fps++;
	}

}