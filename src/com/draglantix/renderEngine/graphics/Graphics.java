package com.draglantix.renderEngine.graphics;

import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.draglantix.renderEngine.engine.ICamera;
import com.draglantix.renderEngine.font.Font;
import com.draglantix.renderEngine.font.Glyph;
import com.draglantix.renderEngine.font.Message;
import com.draglantix.renderEngine.models.Loader;
import com.draglantix.renderEngine.models.RawModel;
import com.draglantix.renderEngine.objects.ObjectShader;
import com.draglantix.renderEngine.rectangle.Rectangle;
import com.draglantix.renderEngine.rectangle.RectangleRenderer;
import com.draglantix.renderEngine.rectangle.RectangleShader;

public class Graphics {

	private ICamera camera;

	private Loader loader = new Loader();

	private RawModel quad = loader.loadToVAO(new float[] { -1, 1, -1, -1, 1, 1, 1, -1 });

	private Font currentFont;

	private RectangleRenderer rectRender;

	public Graphics() {
		rectRender = new RectangleRenderer(new RectangleShader("rectangle"), this);
	}

	public void setCurrentCamera(ICamera cam) {
		camera = cam;
	}

	public ICamera getCurrentCamera() {
		return camera;
	}

	public Loader getLoader() {
		return loader;
	}

	public RawModel getQuad() {
		return quad;
	}

	public void drawRect(Rectangle rectangle) {
		rectRender.render(rectangle);
	}

	public void drawFont(String msg, Vector2f pos, Vector2f rot, Vector2f scale, Vector4f color, boolean useWorldPos) {
		Message message = new Message(msg, pos, rot, scale, color, useWorldPos, this);
		message.setFont(getFont());
		for(Glyph glyph : message.getGlyphs()) {
			drawRect(new Rectangle(glyph));
		}
	}

	public void drawFont(Message message) {
		message.setFont(getFont());
		for(Glyph glyph : message.getGlyphs()) {
			drawRect(new Rectangle(glyph));
		}
	}

	public void setFont(Font font) {
		this.currentFont = font;
	}

	public Font getFont() {
		return currentFont;
	}

	public void prepare(ObjectShader shader) {
		shader.start();
		GL30.glBindVertexArray(getQuad().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}

	public void finish(ObjectShader shader) {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}

	public ObjectShader getRectShader() {
		return rectRender.getShader();
	}

	public void cleanUp() {
		rectRender.cleanUp();
	}
}
