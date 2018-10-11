package com.draglantix.entities;

import java.util.List;

import com.draglantix.engine.Engine;
import com.draglantix.objects.ObjectRenderer;
import com.draglantix.objects.ObjectShader;
import com.draglantix.objects.Objects;

public class EntityRenderer extends ObjectRenderer {

	public EntityRenderer(ObjectShader shader) {
		super(shader);
		Engine.initObjects(Entity.class);
		Engine.renderers.put(Entity.class, this);
	}

	public void render(List<Objects> objs) {
		prepare();
		renderMaster(objs, true);
		finish();
	}

}