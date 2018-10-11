package com.draglantix.foregrounds;

import java.util.List;

import com.draglantix.engine.Engine;
import com.draglantix.objects.ObjectRenderer;
import com.draglantix.objects.ObjectShader;
import com.draglantix.objects.Objects;

public class FGRenderer extends ObjectRenderer{
	
	public FGRenderer(ObjectShader shader) {
		super(shader);
		Engine.initObjects(Foreground.class);
		Engine.renderers.put(Foreground.class, this);
	}

	public void render(List<Objects> maps) {
		prepare();
		renderMaster(maps, true);
		finish();
	}

}
