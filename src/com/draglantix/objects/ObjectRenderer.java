package com.draglantix.objects;

import java.util.List;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.draglantix.assets.Assets;
import com.draglantix.util.Maths;
import com.draglantix.window.Window;

public abstract class ObjectRenderer {

	protected ObjectShader shader;
	
	public ObjectRenderer(ObjectShader shader) {
		this.shader = shader;
	}

	protected void prepare() {
		shader.start();
		GL30.glBindVertexArray(Assets.quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}

	public void renderMaster(List<Objects> objs, boolean useWorldPos) {
		for(Objects obj : objs) {
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, obj.getTexture());
			loadUniforms(obj, useWorldPos);
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, Assets.quad.getVertexCount());
		}
	}
	
	public abstract void render(List<Objects> obj);

	protected void loadUniforms(Objects obj, boolean useWorldPos) {
		Maths.createProjectionMatrix(Window.getWidth(), Window.getHeight());
		Matrix4f transformation = obj.updateTransformationMatrix(obj.getPosition(), obj.getRotation(), obj.getScale());
		shader.loadFinalMatrix(Maths.getFinalMatrix(transformation, useWorldPos));
		shader.loadColor(obj.getColor());
	}

	protected void finish() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}

	public void cleanUp() {
		shader.cleanUp();
	}

}
