package com.draglantix.renderEngine.objects;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;

import com.draglantix.renderEngine.models.SpriteSheet;
import com.draglantix.renderEngine.models.Texture;
import com.draglantix.renderEngine.utils.Color;

public class Objects {

	protected Texture texture;
	protected SpriteSheet sheet;

	protected Vector2f position, rotation, scale;
	protected Matrix4f transformation;

	protected Color color;
	protected Color[] colors;

	private boolean blendColor;
	private boolean usesTex = true;

	protected boolean usesWorldPos;

	public Objects(Texture texture, Vector2f position, Vector2f rotation, Vector2f scale, Color color,
			boolean usesWorldPos) {
		this.texture = texture;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.color = color;
		this.usesWorldPos = usesWorldPos;
		updateTransformationMatrix(position, rotation, scale);
		blendColor = true;
	}

	public Objects(Vector2f position, Vector2f rotation, Vector2f scale, Color color, boolean usesWorldPos) {
		this.texture = null;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.color = color;
		this.usesWorldPos = usesWorldPos;
		updateTransformationMatrix(position, rotation, scale);
		blendColor = true;
		usesTex = false;
	}

	public Objects(SpriteSheet sheet, Vector2f position, Vector2f rotation, Vector2f scale, Color color,
			boolean usesWorldPos) {
		this.sheet = sheet;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.color = color;
		this.usesWorldPos = usesWorldPos;
		updateTransformationMatrix(position, rotation, scale);
		blendColor = true;
	}

	public Objects(Texture texture, Vector2f position, Vector2f rotation, Vector2f scale, Color[] colors,
			boolean usesWorldPos) {
		this.texture = texture;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.colors = colors;
		this.usesWorldPos = usesWorldPos;
		updateTransformationMatrix(position, rotation, scale);
		blendColor = false;
	}

	public Objects(SpriteSheet sheet, Vector2f position, Vector2f rotation, Vector2f scale, Color[] colors,
			boolean usesWorldPos) {
		this.sheet = sheet;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.colors = colors;
		this.usesWorldPos = usesWorldPos;
		updateTransformationMatrix(position, rotation, scale);
		blendColor = false;
	}

	public Objects(Objects obj) {
		this.texture = obj.texture;
		this.position = obj.position;
		this.rotation = obj.rotation;
		this.scale = obj.scale;
		this.color = obj.color;
		this.colors = obj.getColors();
		this.usesWorldPos = obj.usesWorldPos;
		updateTransformationMatrix(position, rotation, scale);
		blendColor = obj.blendColor;
		usesTex = obj.usesTex;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public SpriteSheet getSheet() {
		return sheet;
	}

	public void setSheet(SpriteSheet sheet) {
		this.sheet = sheet;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getRotation() {
		return rotation;
	}

	public void setRotation(Vector2f rotation) {
		this.rotation = rotation;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}

	public Color getColor() {
		return color;
	}

	public boolean usesTex() {
		return usesTex;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setR(float red) {
		this.color.setR(red);
	}

	public void setG(float green) {
		this.color.setG(green);
	}

	public void setB(float blue) {
		this.color.setB(blue);
	}

	public void setA(float alpha) {
		this.color.setAlpha(alpha);
	}

	public Color[] getColors() {
		return colors;
	}
	
	public Vector4f[] getColorsVectors() {
		Vector4f[] colorsV = new Vector4f[colors.length];
		for(int i = 0; i  < colors.length; i++) {
			colorsV[i] = colors[i].getColorVector();
		}
		return colorsV;
	}

	public void setColors(Color[] colors) {
		this.colors = colors;
	}

	public void setColorI(int i, Color color) {
		colors[i] = color;
	}

	public boolean getColorType() {
		return blendColor;
	}

	public Matrix4f updateTransformationMatrix(Vector2f translation, Vector2f rot, Vector2f scale) {
		Matrix4f transformation = new Matrix4f();
		transformation.translate(translation.x, translation.y, 0);
		transformation.rotate((float) Math.toRadians(rot.x), 0.0f, 0.0f, 1.0f);
		transformation.rotate((float) Math.toRadians(rot.y), 0.0f, 1.0f, 0.0f);
		transformation.scale(scale.x, scale.y, 0);

		this.transformation = transformation;

		return transformation;
	}

	public Matrix4f getTransformationMatrix() {
		return transformation;
	}

	public boolean doesUseWorldPos() {
		return usesWorldPos;
	}

}
