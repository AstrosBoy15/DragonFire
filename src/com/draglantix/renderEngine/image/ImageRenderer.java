package com.draglantix.renderEngine.image;

import com.draglantix.renderEngine.graphics.Graphics;
import com.draglantix.renderEngine.objects.ObjectRenderer;
import com.draglantix.renderEngine.objects.ObjectShader;
import com.draglantix.renderEngine.objects.Objects;

public class ImageRenderer extends ObjectRenderer {

	public ImageRenderer(ObjectShader shader, Graphics graphics) {
		super(shader, graphics);
	}

	public void render(Objects obj) {
		renderMaster(obj, obj.doesUseWorldPos());
	}

}