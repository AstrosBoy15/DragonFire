package com.draglantix.renderEngine.postProcessing;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.draglantix.renderEngine.shaders.ShaderProgram;

public class BlurVertical {

	private BlurVerticalShader shader;

	private int targetFboHeight;
	
	public BlurVertical(int targetFboHeight) {
		shader = new BlurVerticalShader("vBlur");
		this.targetFboHeight = targetFboHeight;
	}

	public void render(int texture) {
		shader.start();
		shader.loadTargetHeight(targetFboHeight);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		shader.stop();
	}

	public void cleanUp() {
		shader.cleanUp();
	}
	
	public ShaderProgram getShader() {
		return shader;
	}
}
