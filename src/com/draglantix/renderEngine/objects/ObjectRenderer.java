package com.draglantix.renderEngine.objects;

import java.util.List;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.draglantix.renderEngine.graphics.Graphics;
import com.draglantix.renderEngine.utils.Functions;
import com.draglantix.renderEngine.window.Window;

public abstract class ObjectRenderer {

	protected ObjectShader shader;

	protected Graphics graphics;

	public ObjectRenderer(ObjectShader shader, Graphics graphics) {
		this.shader = shader;
		this.graphics = graphics;
	}

	public void renderMaster(Objects obj, boolean useWorldPos) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		int texID = -1;
		if(obj.getTexture() != null)
			texID = obj.getTexture().getTextureID();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);
		loadUniforms(obj, useWorldPos);
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, graphics.getQuad().getVertexCount());
	}

	public abstract void render(Objects obj);

	public void render(List<Objects> objs) {
		for(Objects obj : objs) {
			render(obj);
		}
	}

	protected void loadUniforms(Objects obj, boolean useWorldPos) {
		Functions.createProjectionMatrix(Window.getWidth(), Window.getHeight());
		Matrix4f transformation = obj.updateTransformationMatrix(obj.getPosition(), obj.getRotation(), obj.getScale());
		if(useWorldPos) {
			shader.loadFinalMatrix(Functions.getFinalMatrixCamera(transformation, graphics));
		} else {
			shader.loadFinalMatrix(Functions.getFinalMatrix(transformation));
		}

		shader.loadUsesTex(obj.usesTex());
		
		if(obj.getColorType()) {
			shader.loadColor(obj.getColor().getColorVector());
		} else {
			shader.loadColors(obj.getColorsVectors());
		}
	}

	public ObjectShader getShader() {
		return shader;
	}

	public void cleanUp() {
		shader.cleanUp();
	}

}
