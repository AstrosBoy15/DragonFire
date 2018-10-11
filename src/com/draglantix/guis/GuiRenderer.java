package com.draglantix.guis;

import java.util.List;

import com.draglantix.engine.Engine;
import com.draglantix.objects.ObjectRenderer;
import com.draglantix.objects.ObjectShader;
import com.draglantix.objects.Objects;

public class GuiRenderer extends ObjectRenderer {

	public GuiRenderer(ObjectShader shader) {
		super(shader);
		Engine.initObjects(Gui.class);
		Engine.renderers.put(Gui.class, this);
	}

	public void render(List<Objects> objs) {
		prepare();
		renderMaster(objs, false);
		finish();
	}

}
