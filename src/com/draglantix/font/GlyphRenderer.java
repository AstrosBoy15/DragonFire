package com.draglantix.font;

import java.util.List;

import com.draglantix.engine.Engine;
import com.draglantix.objects.ObjectRenderer;
import com.draglantix.objects.ObjectShader;
import com.draglantix.objects.Objects;

public class GlyphRenderer extends ObjectRenderer {

	public GlyphRenderer(ObjectShader shader) {
		super(shader);
		Engine.initObjects(Glyph.class);
		Engine.renderers.put(Glyph.class, this);
	}

	public void render(List<Objects> objs) {
		prepare();
		renderMaster(objs, false);
		finish();
	}

}