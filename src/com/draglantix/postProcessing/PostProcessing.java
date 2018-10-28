package com.draglantix.postProcessing;

import com.draglantix.renderEngine.graphics.Graphics;
import com.draglantix.renderEngine.utils.FBO;
import com.draglantix.renderEngine.window.Window;

public class PostProcessing {

	private BlurHorizontal hBlur;
	public BlurVertical vBlur;
	
	private Graphics graphics;

	public PostProcessing(Graphics graphics) {
		hBlur = new BlurHorizontal(Window.getWidth() / 2);
		vBlur = new BlurVertical(Window.getHeight() / 2);
		this.graphics = graphics;
	}

	public void doBlurHorizontal(FBO src, FBO dest) {
		dest.bindFrameBuffer();
		graphics.prepare(hBlur.getShader());
		hBlur.render(src.getColorTexture());
		dest.unbindFrameBuffer();
	}

	public void doBlurVertical(FBO src, FBO dest) {
		dest.bindFrameBuffer();
		graphics.prepare(vBlur.getShader());
		vBlur.render(src.getColorTexture());
		dest.unbindFrameBuffer();
	}

	public void cleanUp() {
		hBlur.cleanUp();
		vBlur.cleanUp();
	}

}
