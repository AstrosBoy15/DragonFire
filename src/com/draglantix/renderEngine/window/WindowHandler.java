package com.draglantix.renderEngine.window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import com.draglantix.renderEngine.engine.ICamera;

public class WindowHandler {

	public static void handle(Window window, ICamera camera){
		
		window.update();

		if(Window.hasResized()) {
			GL11.glViewport(0, 0, Window.getWidth(), Window.getHeight());
		}

		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			GLFW.glfwSetWindowShouldClose(Window.getWindow(), true);
		}
		
		if(camera != null) {
			camera.move();
		}

	}

}
