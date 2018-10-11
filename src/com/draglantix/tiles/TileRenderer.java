package com.draglantix.tiles;

import java.util.List;

import com.draglantix.engine.Engine;
import com.draglantix.objects.ObjectRenderer;
import com.draglantix.objects.ObjectShader;
import com.draglantix.objects.Objects;

public class TileRenderer extends ObjectRenderer{
	
	public TileRenderer(ObjectShader shader) {
		super(shader);
		Engine.initObjects(Tile.class);
		Engine.renderers.put(Tile.class, this);
	}
	
	public void render(List<Objects> tiles) {
		prepare();
		renderMaster(tiles, true);
		finish();
	}

}
