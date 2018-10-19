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
import com.draglantix.renderEngine.image.Image;
import com.draglantix.renderEngine.image.ImageRenderer;
import com.draglantix.renderEngine.models.Loader;
import com.draglantix.renderEngine.models.RawModel;
import com.draglantix.renderEngine.models.Texture;
import com.draglantix.renderEngine.objects.ObjectShader;
import com.draglantix.renderEngine.rectangle.Rectangle;
import com.draglantix.renderEngine.rectangle.RectangleRenderer;
import com.draglantix.renderEngine.shaders.GenShader;

public class Graphics {

	private ICamera camera;

	private Loader loader = new Loader();

	private RawModel quad = loader.loadToVAO(new float[] { -1, 1, -1, -1, 1, 1, 1, -1 });

	private Font currentFont;

	private RectangleRenderer rectRender;
	private ImageRenderer imgRender;
	
	private GenShader shader;

	private ObjectShader currentShader;

	public Graphics() {
		shader = new GenShader("gen");
		rectRender = new RectangleRenderer(shader, this);
		imgRender = new ImageRenderer(shader, this);
		init();
	}

	private void init() {
		GL30.glBindVertexArray(getQuad().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
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
		prepare(getRectShader());
		rectRender.render(rectangle);
	}

	public void drawImage(Image image) {
		prepare(getImageShader());
		imgRender.render(image);
	}

	public void drawImage(Texture texture, Vector2f position, Vector2f rotation, Vector2f scale, Vector4f color,
			boolean usesWorldPos) {
		prepare(getImageShader());
		Image image = new Image(texture, position, rotation, scale, color, usesWorldPos);
		imgRender.render(image);
	}

	public void drawRect(Vector2f position, Vector2f rotation, Vector2f scale, Vector4f color, boolean useWorldPos) {
		prepare(getRectShader());
		Rectangle rectangle = new Rectangle(position, rotation, scale, color, useWorldPos);
		rectRender.render(rectangle);
	}

	public void drawFont(String msg, Vector2f pos, Vector2f rot, Vector2f scale, Vector4f color, boolean usesWorldPos) {
		prepare(getImageShader());
		Message message = new Message(msg, pos, rot, scale, color, usesWorldPos, this);
		message.setFont(getFont());
		for(Glyph glyph : message.getGlyphs()) {
			drawImage(new Image(glyph));
		}
	}

	public void drawFont(Message message) {
		prepare(getImageShader());
		message.setFont(getFont());
		for(Glyph glyph : message.getGlyphs()) {
			drawImage(new Image(glyph));
		}
	}

	public void setFont(Font font) {
		this.currentFont = font;
	}

	public Font getFont() {
		return currentFont;
	}

	private void prepare(ObjectShader shader) {
		if(currentShader != shader) {
			System.out.println("Works");
			if(currentShader != null) {
				finish(currentShader);
			}
			currentShader = shader;
			shader.start();
		}
	}

	private void finish(ObjectShader shader) {
		System.out.println("Works");
		shader.stop();
	}

	public ObjectShader getRectShader() {
		return rectRender.getShader();
	}

	public ObjectShader getImageShader() {
		return imgRender.getShader();
	}

	public void cleanUp() {
		rectRender.cleanUp();
		imgRender.cleanUp();
	}
}
